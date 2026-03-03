package com.designpatterns.structural.adapter;

/**
 * PaymentResult - Ödeme İşlemi Sonucu
 *
 * <p>Ödeme gateway'inden dönen sonuç nesnesi.
 * Hem başarılı hem de başarısız işlem sonuçlarını taşır.
 *
 * <p>Bu sınıf Adapter Pattern'ın bir parçasıdır: modern ödeme gateway'i
 * sonuçlarını temsil eder ve legacy sistem sonuçlarından bağımsızdır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class PaymentResult {

    /** İşlem başarılı mı? */
    private final boolean success;

    /** Benzersiz işlem kimliği (transaction ID) */
    private final String transactionId;

    /** İşlem hakkında açıklayıcı mesaj */
    private final String message;

    /** İşlem tutarı */
    private final double amount;

    /** Para birimi kodu */
    private final String currency;

    /**
     * Tüm alanları başlatan tam constructor.
     *
     * @param success       İşlem başarılı mı
     * @param transactionId İşlem kimliği
     * @param message       Açıklama mesajı
     * @param amount        Tutar
     * @param currency      Para birimi
     */
    public PaymentResult(boolean success, String transactionId, String message,
                         double amount, String currency) {
        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Başarısız işlem için yardımcı factory methodu.
     *
     * @param errorMessage Hata mesajı
     * @return Başarısız PaymentResult nesnesi
     */
    public static PaymentResult failure(String errorMessage) {
        return new PaymentResult(false, "N/A", errorMessage, 0.0, "N/A");
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getTransactionId() { return transactionId; }
    public String getMessage() { return message; }
    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }

    @Override
    public String toString() {
        return String.format(
            "PaymentResult{success=%s, transactionId='%s', message='%s', amount=%.2f, currency='%s'}",
            success, transactionId, message, amount, currency
        );
    }
}
