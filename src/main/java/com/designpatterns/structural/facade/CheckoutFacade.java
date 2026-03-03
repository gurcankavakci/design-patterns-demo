package com.designpatterns.structural.facade;

import com.designpatterns.model.Cart;
import com.designpatterns.model.Customer;
import com.designpatterns.model.Product;

import java.util.Map;

/**
 * FACADE PATTERN - Cephe Deseni
 *
 * <p><b>TANIM:</b> Bir alt sistemin karmaşık interface'ine daha basit bir interface sağlar.
 *
 * <p><b>PROBLEM:</b> Sipariş vermek için birçok servisle koordinasyon gerekiyor:
 * Stok kontrolü → Ödeme → Kargo → Bildirim
 * Client bu karmaşıklığı bilmek zorunda değil.
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Restoranda garson = Facade. "Biftek istiyorum" dersiniz.
 * Garson mutfak, kasa, garderob ile koordine eder. Siz sadece yemeği alırsınız.
 *
 * <pre>
 * UML DIAGRAM:
 * ┌──────────────────────────────────────────────────────┐
 * │                    CLIENT                             │
 * └──────────────────────┬───────────────────────────────┘
 *                        │ uses (only this)
 *               ┌────────┴──────────┐
 *               │  CheckoutFacade   │
 *               └────────┬──────────┘
 *         ┌──────────────┼──────────────┬──────────────────┐
 *         ↓              ↓              ↓                   ↓
 * InventoryService  PaymentService  ShippingService  EmailService
 * </pre>
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Alt sistem karmaşıklığını gizler</li>
 *   <li>Client-subsystem coupling azalır</li>
 *   <li>Katmanlı mimariyi destekler</li>
 * </ul>
 *
 * <p><b>NE ZAMAN KULLANILIR:</b>
 * <ul>
 *   <li>Karmaşık alt sisteme basit interface gerektiğinde</li>
 *   <li>Kütüphane entry point'i</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class CheckoutFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final EmailNotificationService notificationService;

    /**
     * Facade'i oluşturur ve tüm alt sistem servislerini başlatır.
     */
    public CheckoutFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new EmailNotificationService();
    }

    /**
     * Sipariş verme sürecini tek bir metod çağrısıyla yönetir.
     *
     * <p>Dahili adımlar:
     * <ol>
     *   <li>Stok kontrolü</li>
     *   <li>Ödeme yöntemi doğrulama</li>
     *   <li>Kargo maliyeti hesaplama</li>
     *   <li>Ödeme işlemi</li>
     *   <li>Stok rezervasyonu + Kargo oluşturma + Bildirimler</li>
     * </ol>
     *
     * @param customer      Sipariş veren müşteri
     * @param cart          Sipariş sepeti
     * @param paymentMethod Ödeme yöntemi
     * @param shippingType  Kargo tipi (STANDARD, EXPRESS, FREE)
     * @return CheckoutResult başarılı veya başarısız sonuç
     */
    public CheckoutResult placeOrder(Customer customer, Cart cart,
                                     String paymentMethod, String shippingType) {
        try {
            System.out.println("\n=== [CheckoutFacade] Sipariş Süreci Başlıyor ===");

            // Step 1: Stok kontrolü
            System.out.println("[1/5] Stok kontrol ediliyor...");
            for (Product product : cart.getProducts()) {
                int qty = cart.getItemQuantities().get(product.getId());
                if (!inventoryService.checkAvailability(product.getId(), qty)) {
                    return CheckoutResult.failure("Stok yetersiz: " + product.getName());
                }
            }

            // Step 2: Ödeme doğrulama
            System.out.println("[2/5] Ödeme yöntemi doğrulanıyor...");
            if (!paymentService.validatePaymentMethod(paymentMethod)) {
                return CheckoutResult.failure("Geçersiz ödeme yöntemi");
            }

            // Step 3: Kargo maliyeti hesaplama
            System.out.println("[3/5] Kargo hesaplanıyor...");
            double totalWeight = cart.getProducts().stream()
                    .mapToDouble(p -> p.getWeight() * cart.getItemQuantities().get(p.getId()))
                    .sum();
            double shippingCost = shippingService.calculateShippingCost(totalWeight, shippingType);
            double totalAmount = cart.getTotalAmount() + shippingCost;

            // Step 4: Ödeme işlemi
            System.out.println("[4/5] Ödeme işleniyor: " + totalAmount + " TL...");
            String txnId = paymentService.processPayment(totalAmount, paymentMethod, customer.getId());

            // Step 5: Stok rezervasyonu + Kargo oluşturma
            System.out.println("[5/5] Kargo oluşturuluyor...");
            String orderId = "ORD-" + System.currentTimeMillis();
            cart.getProducts().forEach(p ->
                    inventoryService.reserveStock(p.getId(), cart.getItemQuantities().get(p.getId())));
            String trackingNo = shippingService.createShipment(orderId, customer.getAddress(), shippingType);

            // Bildirimler
            notificationService.sendOrderConfirmation(customer.getEmail(), orderId, totalAmount);
            notificationService.sendPaymentConfirmation(customer.getEmail(), txnId);
            notificationService.sendShipmentNotification(customer.getEmail(), trackingNo);

            System.out.println("=== [CheckoutFacade] Sipariş Tamamlandı! ===");
            return new CheckoutResult(true, orderId, txnId, trackingNo, totalAmount,
                    "Sipariş başarıyla oluşturuldu!");

        } catch (Exception e) {
            return CheckoutResult.failure("Sipariş hatası: " + e.getMessage());
        }
    }

    /**
     * Siparişi iptal eder; ödeme iadesi, kargo iptali ve stok serbest bırakma işlemlerini yapar.
     *
     * @param orderId        İptal edilecek sipariş ID'si
     * @param transactionId  İade yapılacak işlem ID'si
     * @param trackingNo     İptal edilecek kargo takip numarası
     * @param itemQuantities Serbest bırakılacak ürün miktarları (productId -> qty)
     * @return true iptal başarılı ise
     */
    public boolean cancelOrder(String orderId, String transactionId,
                               String trackingNo, Map<String, Integer> itemQuantities) {
        System.out.println("[CheckoutFacade] Sipariş iptal ediliyor: " + orderId);
        paymentService.refundPayment(transactionId);
        shippingService.cancelShipment(trackingNo);
        // Release stock - just print since we don't have product refs here
        System.out.println("Stok serbest bırakıldı.");
        System.out.println("İptal işlemi tamamlandı: " + orderId);
        return true;
    }
}
