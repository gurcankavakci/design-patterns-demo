package com.designpatterns.creational.factory;

/**
 * FACTORY METHOD PATTERN - Fabrika Metot Deseni
 *
 * Ürün interface'i. Her ödeme yöntemi bu interface'i implement eder.
 *
 * TANIM: Nesne oluşturma için bir interface tanımlar,
 * ancak hangi sınıfın instantiate edileceğine alt sınıflar karar verir.
 *
 * Bu interface, "Product" rolünü oynar.
 * Factory Method Pattern'de "Product" şunları tanımlar:
 * - Tüm concrete product'ların implement etmesi gereken operasyonlar
 * - Client kodunun concrete sınıflardan bağımsız çalışmasını sağlar
 *
 * ---
 *
 * FACTORY METHOD PATTERN - Factory Method Pattern
 *
 * Product interface. Every payment method implements this interface.
 *
 * DEFINITION: Defines an interface for creating objects, but lets
 * subclasses decide which class to instantiate.
 *
 * This interface plays the "Product" role in the pattern.
 */
public interface Payment {

    /**
     * Belirtilen tutarı ödeme yöntemiyle işler.
     * Ödeme sağlayıcısına gönderim simüle edilir.
     *
     * @param amount Ödenecek tutar (TL cinsinden)
     * @return Ödeme başarılıysa true, başarısızsa false
     */
    boolean processPayment(double amount);

    /**
     * Belirtilen tutarı ödeme yöntemine iade eder.
     * İade işlemi simüle edilir.
     *
     * @param amount İade edilecek tutar (TL cinsinden)
     * @return İade başarılıysa true, başarısızsa false
     */
    boolean refund(double amount);

    /**
     * Ödeme yönteminin türünü döndürür.
     * Örneğin: "Kredi Kartı", "PayPal", "Banka Havalesi"
     *
     * @return Ödeme türü string olarak
     */
    String getPaymentType();

    /**
     * Ödeme yöntemine ait detay bilgilerini döndürür.
     * Hassas bilgiler maskelenerek gösterilir.
     *
     * @return Ödeme detayları string olarak
     */
    String getPaymentDetails();
}
