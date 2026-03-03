package com.designpatterns.creational.factory;

/**
 * PayPal ile ödeme implementasyonu.
 * Factory Method Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * PayPal, kullanıcının e-posta adresiyle tanımlanır.
 * Gerçek bir uygulamada OAuth token tabanlı kimlik doğrulama kullanılır.
 */
public class PayPalPayment implements Payment {

    private String email;

    /**
     * PayPal ödeme nesnesi oluşturur.
     *
     * @param email PayPal hesabına bağlı e-posta adresi
     */
    public PayPalPayment(String email) {
        this.email = email;
    }

    /**
     * PayPal üzerinden ödeme işlemini gerçekleştirir.
     * PayPal API çağrısı simüle edilir.
     *
     * @param amount Ödenecek tutar
     * @return İşlem başarılıysa true
     */
    @Override
    public boolean processPayment(double amount) {
        System.out.println("  [PAYPAL] Ödeme işlemi başlatıldı...");
        System.out.println("  [PAYPAL] PayPal ile ödeme: " + String.format("%.2f", amount) +
                " TL - Hesap: " + email);
        System.out.println("  [PAYPAL] PayPal sunucusuna bağlanılıyor...");
        System.out.println("  [PAYPAL] Hesap bakiyesi kontrol ediliyor...");
        System.out.println("  [PAYPAL] Ödeme onaylandı. İşlem BASARILI.");
        return true;
    }

    /**
     * PayPal üzerinden iade işlemini gerçekleştirir.
     * İade anında hesaba yansır.
     *
     * @param amount İade edilecek tutar
     * @return İade başarılıysa true
     */
    @Override
    public boolean refund(double amount) {
        System.out.println("  [PAYPAL] İade işlemi başlatıldı...");
        System.out.println("  [PAYPAL] PayPal hesabına iade: " + String.format("%.2f", amount) +
                " TL - Hesap: " + email);
        System.out.println("  [PAYPAL] İade anında PayPal hesabınıza yansıdı.");
        return true;
    }

    /**
     * @return "PayPal"
     */
    @Override
    public String getPaymentType() {
        return "PayPal";
    }

    /**
     * PayPal hesap bilgilerini döndürür.
     * E-posta adresi kısmen maskelenerek gösterilir.
     *
     * @return PayPal hesap detayları
     */
    @Override
    public String getPaymentDetails() {
        // E-postanın @ işaretinden önceki kısmını maskele
        String maskedEmail = maskEmail(email);
        return "PayPal Hesabı: " + maskedEmail;
    }

    /**
     * E-posta adresini kısmen maskeler.
     * Örneğin: "ahmet@example.com" → "ah***@example.com"
     *
     * @param email Maskelenecek e-posta adresi
     * @return Kısmen maskelenmiş e-posta
     */
    private String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "***@***.com";
        }
        int atIndex = email.indexOf('@');
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);
        if (localPart.length() <= 2) {
            return localPart + "***" + domainPart;
        }
        return localPart.substring(0, 2) + "***" + domainPart;
    }

    /**
     * PayPal hesabının e-posta adresini döndürür.
     *
     * @return E-posta adresi
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "PayPalPayment{email='" + maskEmail(email) + "'}";
    }
}
