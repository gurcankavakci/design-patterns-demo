package com.designpatterns.behavioral.command;

import com.designpatterns.model.Cart;
import com.designpatterns.model.Product;

/**
 * SEPETTEN CIKAR KOMUTU - Remove from Cart Command
 *
 * <p>Command Pattern'ın ConcreteCommand rolü.
 * Sepetten ürün çıkarma işlemini kapsüller ve geri alınabilir hale getirir.</p>
 *
 * <p><b>execute():</b> Ürünü sepetten çıkarır. Önceki miktarı kaydeder (undo için).<br>
 * <b>undo():</b> Ürünü önceki miktarıyla sepete geri ekler.</p>
 *
 * <p><b>Önemli:</b> Undo işlemi için execute() çalışmadan önce
 * mevcut miktar kaydedilmelidir (previousQuantity).</p>
 */
public class RemoveFromCartCommand implements ShopCommand {

    /** Komutun çalışacağı sepet (Receiver). */
    private final Cart cart;

    /** Sepetten çıkarılacak ürün. */
    private final Product product;

    /**
     * Undo için önceki miktar.
     * execute() çalışınca set edilir.
     */
    private int previousQuantity;

    /**
     * RemoveFromCartCommand constructor'ı.
     *
     * @param cart    İşlemin yapılacağı sepet
     * @param product Çıkarılacak ürün
     */
    public RemoveFromCartCommand(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.previousQuantity = 0;
    }

    /**
     * Ürünü sepetten çıkarır.
     * Önce mevcut miktarı kaydeder (undo için), sonra çıkarır.
     */
    @Override
    public void execute() {
        // Undo için mevcut miktarı kaydet
        previousQuantity = cart.getItemQuantities().getOrDefault(product.getId(), 0);
        cart.removeProduct(product.getId());
        System.out.println("Komut Calistı [Sepetten Cikar]: "
                + product.getName() + " (önceki miktar: " + previousQuantity + ")");
    }

    /**
     * Ürünü önceki miktarıyla geri ekleyerek çıkarma işlemini iptal eder.
     */
    @Override
    public void undo() {
        if (previousQuantity > 0) {
            cart.addProduct(product, previousQuantity);
            System.out.println("Komut Geri Alındı [Sepete Geri Ekle]: "
                    + product.getName() + " x" + previousQuantity + " geri eklendi");
        } else {
            System.out.println("Komut Geri Alma Uyarısı: "
                    + product.getName() + " onceden sepette yoktu, geri ekleme yapılmadı");
        }
    }

    @Override
    public String getCommandName() {
        return "Sepetten Cikar";
    }

    @Override
    public String getDescription() {
        return product.getName() + " sepetten cıkarıldı (miktar: " + previousQuantity + ")";
    }

    // Getters
    public Product getProduct() { return product; }
    public int getPreviousQuantity() { return previousQuantity; }
}
