package com.designpatterns.creational.factory;

/**
 * PayPal ödeme fabrikası.
 * Factory Method Pattern'de "ConcreteCreator" rolünü oynar.
 *
 * Bu sınıf, PaymentFactory'nin soyut createPayment() metodunu implement eder
 * ve her çağrıda yeni bir PayPalPayment nesnesi oluşturur.
 *
 * Yeni bir ödeme yöntemi (ör: PayPal) eklemek için:
 * 1. Yeni ConcreteProduct sınıfı yaz (PayPalPayment - Payment impl.)
 * 2. Yeni ConcreteCreator sınıfı yaz (bu sınıf - PaymentFactory extends)
 * Mevcut kod DEGISTIRILMEZ. Open/Closed Principle!
 */
public class PayPalPaymentFactory extends PaymentFactory {

    private String email;

    /**
     * PayPal ödeme fabrikası oluşturur.
     *
     * @param email PayPal hesabına bağlı e-posta adresi
     */
    public PayPalPaymentFactory(String email) {
        this.email = email;
    }

    /**
     * Factory Method implementasyonu.
     * PayPal hesap e-postasıyla yeni bir PayPalPayment nesnesi oluşturur.
     *
     * @return Yeni PayPalPayment nesnesi
     */
    @Override
    public Payment createPayment() {
        System.out.println("  [PAYPAL FACTORY] PayPalPayment nesnesi oluşturuluyor...");
        return new PayPalPayment(email);
    }
}
