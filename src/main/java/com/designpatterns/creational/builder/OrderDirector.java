package com.designpatterns.creational.builder;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;

import java.util.List;

/**
 * ORDER DIRECTOR - Sipariş Yöneticisi
 *
 * BUILDER PATTERN'DE DIRECTOR'IN ROLÜ:
 * Director, Builder'ı belirli sırayla kullanarak önceden tanımlanmış
 * konfigürasyonlar oluşturur. Client'ın her seferinde tüm adımları
 * bilmesine gerek kalmaz.
 *
 * Director olmadan:
 * // Her yerde tekrar eden kod:
 * new OrderBuilder().forCustomer(c).withProduct(p,1).withPayment("KK").withExpressShipping().build()
 *
 * Director ile:
 * director.buildExpressOrder(customer, product) // tek satır!
 *
 * DIRECTOR'I KULLANMANIN AVANTAJLARI:
 * 1. Kod tekrarını önler - yaygın sipariş konfigürasyonları merkezi olarak tanımlanır
 * 2. Konfigürasyon hatalarını önler - adımlar her zaman doğru sırada çalışır
 * 3. Business logic builder'dan ayrılır - director iş kurallarını bilir
 * 4. Bakım kolaylığı - express sipariş mantığı değişirse sadece director güncellenir
 *
 * DIRECTOR ZORUNLU DEGİL:
 * Builder'ı doğrudan da kullanabilirsiniz (Director opsiyoneldir).
 * Director sadece yaygın konfigürasyonlar için kolaylık sağlar.
 *
 * ---
 *
 * ORDER DIRECTOR - Order Manager
 *
 * ROLE IN BUILDER PATTERN:
 * The Director uses the Builder in a specific sequence to build
 * predefined configurations. Client code doesn't need to know each step.
 *
 * The Director is optional - clients can use the Builder directly,
 * but Directors provide convenience for common configurations.
 */
public class OrderDirector {

    private OrderBuilder builder = new OrderBuilder();

    /**
     * Standart sipariş oluşturur.
     * Tek ürün, kredi kartı ödemesi, standart kargo.
     *
     * @param customer Sipariş müşterisi
     * @param product  Sipariş ürünü
     * @return Oluşturulan standart sipariş
     */
    public Order buildStandardOrder(Customer customer, Product product) {
        System.out.println("  [DIRECTOR] Standart sipariş oluşturuluyor...");
        return builder
                .forCustomer(customer)
                .withProduct(product, 1)
                .withPayment("Kredi Kartı")
                .withStandardShipping()
                .build();
    }

    /**
     * Premium ekspres sipariş oluşturur.
     * Birden fazla ürün, kredi kartı ödemesi, ekspres kargo.
     * Premium müşteriler için ekspres teslimat varsayılandır.
     *
     * @param customer Sipariş müşterisi (premium olmalı)
     * @param products Sipariş ürünleri listesi
     * @return Oluşturulan premium ekspres sipariş
     */
    public Order buildPremiumExpressOrder(Customer customer, List<Product> products) {
        System.out.println("  [DIRECTOR] Premium ekspres sipariş oluşturuluyor...");
        OrderBuilder b = new OrderBuilder()
                .forCustomer(customer)
                .withPayment("Kredi Kartı")
                .withExpressShipping();

        for (Product product : products) {
            b.withProduct(product, 1);
        }

        return b.build();
    }

    /**
     * Hediye siparişi oluşturur.
     * Farklı teslimat adresi, hediye paketi ve özel not içerir.
     * Standart kargo kullanılır.
     *
     * @param customer         Sipariş veren müşteri
     * @param product          Hediye edilecek ürün
     * @param recipientAddress Alıcının teslimat adresi
     * @return Oluşturulan hediye siparişi
     */
    public Order buildGiftOrder(Customer customer, Product product, String recipientAddress) {
        System.out.println("  [DIRECTOR] Hediye siparişi oluşturuluyor...");
        return new OrderBuilder()
                .forCustomer(customer)
                .withProduct(product, 1)
                .toAddress(recipientAddress)
                .withPayment("Kredi Kartı")
                .withStandardShipping()
                .withGiftWrapping()
                .withNotes("Hediye paketiyle gönderilsin")
                .build();
    }

    /**
     * Ücretsiz kargo siparişi oluşturur.
     * Birden fazla ürün, PayPal ödemesi, ücretsiz kargo.
     * Promosyon veya belirli tutar üzeri siparişler için kullanılır.
     *
     * @param customer Sipariş müşterisi
     * @param products Sipariş ürünleri listesi
     * @return Oluşturulan ücretsiz kargo siparişi
     */
    public Order buildFreeShippingOrder(Customer customer, List<Product> products) {
        System.out.println("  [DIRECTOR] Ücretsiz kargo siparişi oluşturuluyor...");
        OrderBuilder b = new OrderBuilder()
                .forCustomer(customer)
                .withPayment("PayPal")
                .withFreeShipping();

        for (Product product : products) {
            b.withProduct(product, 1);
        }

        return b.build();
    }

    /**
     * Kullanılan OrderBuilder'ı döndürür.
     * İleri düzey kullanım için builder'a doğrudan erişim sağlar.
     *
     * @return OrderBuilder nesnesi
     */
    public OrderBuilder getBuilder() {
        return builder;
    }
}
