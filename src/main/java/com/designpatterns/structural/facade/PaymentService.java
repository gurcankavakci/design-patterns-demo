package com.designpatterns.structural.facade;

/**
 * PAYMENT SERVICE - Ödeme Servisi (Facade Alt Sistemi)
 *
 * <p>Facade Pattern'da subsystem (alt sistem) rolünü üstlenir.
 * Ödeme doğrulama, işleme ve iade işlemlerini yönetir.
 *
 * <p>Client bu sınıfı doğrudan değil, {@link CheckoutFacade} üzerinden kullanır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class PaymentService {

    /** Desteklenen ödeme yöntemleri */
    private static final String[] SUPPORTED_METHODS = {
        "KREDI_KARTI", "BANKA_KARTI", "HAVALE", "KAPIDA_ODEME", "SHOPEASE_BAKIYE"
    };

    /**
     * Ödeme servisini başlatır.
     */
    public PaymentService() {
        System.out.println("  [PaymentService] Ödeme servisi baslatildi.");
    }

    /**
     * Ödeme yönteminin geçerli ve desteklenip desteklenmediğini doğrular.
     *
     * @param method Ödeme yöntemi
     * @return true geçerli ise
     */
    public boolean validatePaymentMethod(String method) {
        System.out.println("  [Payment] Ödeme yöntemi dogrulanıyor: " + method);
        for (String supported : SUPPORTED_METHODS) {
            if (supported.equalsIgnoreCase(method)) {
                System.out.println("  [Payment] Ödeme yöntemi geçerli: " + method);
                return true;
            }
        }
        System.err.println("  [Payment] Desteklenmeyen ödeme yöntemi: " + method);
        return false;
    }

    /**
     * Ödeme işlemini gerçekleştirir.
     *
     * @param amount     Ödeme tutarı (TL)
     * @param method     Ödeme yöntemi
     * @param customerId Müşteri kimliği
     * @return İşlem kimliği (transaction ID) veya null başarısız ise
     */
    public String processPayment(double amount, String method, String customerId) {
        System.out.printf("  [Payment] Ödeme işleniyor: %.2f TL | Yöntem: %s | Müşteri: %s%n",
                          amount, method, customerId);

        // Ödeme işlemi simülasyonu
        if (amount <= 0) {
            System.err.println("  [Payment] Geçersiz tutar: " + amount);
            return null;
        }

        String txnId = "TXN-" + System.currentTimeMillis();
        System.out.println("  [Payment] Ödeme basarili! Islem No: " + txnId);
        return txnId;
    }

    /**
     * Ödeme iadesi yapar.
     *
     * @param transactionId İade yapılacak işlem kimliği
     * @return true iade başarılı ise
     */
    public boolean refundPayment(String transactionId) {
        System.out.println("  [Payment] İade yapılıyor: " + transactionId);
        System.out.println("  [Payment] İade basarili: " + transactionId);
        return true;
    }
}
