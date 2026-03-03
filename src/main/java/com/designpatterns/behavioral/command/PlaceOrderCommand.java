package com.designpatterns.behavioral.command;

import com.designpatterns.model.Cart;
import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;

/**
 * SİPARİŞ VER KOMUTU - Place Order Command
 *
 * <p>Command Pattern'ın ConcreteCommand rolü.
 * Mevcut sepet içeriğinden sipariş oluşturma işlemini kapsüller.</p>
 *
 * <p><b>execute():</b> Sepet içeriğinden yeni bir sipariş oluşturur, sepeti temizler.<br>
 * <b>undo():</b> Siparişi iptal eder ve sepeti geri yükler.</p>
 *
 * <p>Bu komut hem Cart (sepet temizleme) hem de Order (oluşturma) üzerinde
 * çalışır. Undo karmaşıktır çünkü sepet temizlenmesi geri alınmalıdır.</p>
 */
public class PlaceOrderCommand implements ShopCommand {

    /** Siparişin oluşturulacağı sepet (Receiver 1). */
    private final Cart cart;

    /** Siparişi veren müşteri. */
    private final Customer customer;

    /**
     * Oluşturulan sipariş nesnesi.
     * execute() sonrasında set edilir, undo() için gereklidir.
     */
    private Order createdOrder;

    /**
     * PlaceOrderCommand constructor'ı.
     *
     * @param cart     Siparişin oluşturulacağı sepet
     * @param customer Siparişi veren müşteri
     */
    public PlaceOrderCommand(Cart cart, Customer customer) {
        this.cart = cart;
        this.customer = customer;
    }

    /**
     * Mevcut sepet içeriğinden sipariş oluşturur.
     *
     * <p>Adımlar:</p>
     * <ol>
     *   <li>Yeni sipariş ID'si üretir</li>
     *   <li>Sepetteki her ürünü siparişe ekler</li>
     *   <li>Sipariş durumunu CONFIRMED yapar</li>
     *   <li>Sepeti temizler</li>
     * </ol>
     */
    @Override
    public void execute() {
        if (cart.getProducts().isEmpty()) {
            System.out.println("Komut Hatası [Sipariş Ver]: Sepet boş, sipariş verilemez!");
            return;
        }

        String orderId = "ORD-" + System.currentTimeMillis();
        createdOrder = new Order(orderId, customer);

        // Sepetteki tüm ürünleri siparişe ekle
        for (Product product : cart.getProducts()) {
            int qty = cart.getItemQuantities().get(product.getId());
            createdOrder.addItem(product, qty);
        }

        createdOrder.setStatus("CONFIRMED");

        double total = createdOrder.getTotalAmount();

        // Sepeti temizle
        cart.clear();

        System.out.println("Komut Calistı [Sipariş Ver]: Sipariş oluşturuldu #" + orderId
                + " - " + String.format("%.2f", total) + " TL"
                + " | Durum: CONFIRMED");
    }

    /**
     * Siparişi iptal ederek sipariş verme işlemini geri alır.
     * Oluşturulan siparişi CANCELLED yapar ve sepeti geri yükler.
     */
    @Override
    public void undo() {
        if (createdOrder == null) {
            System.out.println("Komut Geri Alma Uyarısı: Geri alınacak sipariş yok.");
            return;
        }

        createdOrder.setStatus("CANCELLED");

        // Sepeti geri yükle
        createdOrder.getItems().forEach((product, qty) -> cart.addProduct(product, qty));

        System.out.println("Komut Geri Alındı [Sipariş İptal]: #" + createdOrder.getId()
                + " iptal edildi, sepet geri yuklendi ("
                + createdOrder.getItems().size() + " urun)");
    }

    @Override
    public String getCommandName() {
        return "Siparis Ver";
    }

    @Override
    public String getDescription() {
        if (createdOrder != null) {
            return "Sipariş oluşturuldu: #" + createdOrder.getId()
                    + " (" + String.format("%.2f", createdOrder.getTotalAmount()) + " TL)";
        }
        return "Sipariş oluşturma komutu (henüz çalışmadı)";
    }

    /**
     * Oluşturulan siparişi döndürür.
     * execute() çalışmadan null olabilir.
     *
     * @return Oluşturulan sipariş veya null
     */
    public Order getCreatedOrder() {
        return createdOrder;
    }
}
