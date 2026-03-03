package com.designpatterns.structural.facade;

/**
 * FACADE PATTERN - Checkout Sonuç Nesnesi
 *
 * <p>CheckoutFacade'in placeOrder() metodundan dönen sonuç nesnesi.
 * Başarılı veya başarısız sipariş sonucunu kapsüller.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class CheckoutResult {

    private final boolean success;
    private final String orderId;
    private final String transactionId;
    private final String trackingNumber;
    private final double totalAmount;
    private final String message;

    /**
     * Tam parametreli constructor.
     *
     * @param success       İşlem başarılı mı?
     * @param orderId       Oluşturulan sipariş ID'si
     * @param transactionId Ödeme işlem ID'si
     * @param trackingNumber Kargo takip numarası
     * @param totalAmount   Toplam tutar (TL)
     * @param message       Sonuç mesajı
     */
    public CheckoutResult(boolean success, String orderId, String transactionId,
                          String trackingNumber, double totalAmount, String message) {
        this.success = success;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.trackingNumber = trackingNumber;
        this.totalAmount = totalAmount;
        this.message = message;
    }

    /**
     * Başarısız sonuç için statik factory metodu.
     *
     * @param message Hata mesajı
     * @return Başarısız CheckoutResult
     */
    public static CheckoutResult failure(String message) {
        return new CheckoutResult(false, null, null, null, 0, message);
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getOrderId() { return orderId; }
    public String getTransactionId() { return transactionId; }
    public String getTrackingNumber() { return trackingNumber; }
    public double getTotalAmount() { return totalAmount; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return String.format(
            "CheckoutResult{success=%s, orderId='%s', transactionId='%s', " +
            "trackingNumber='%s', totalAmount=%.2f TL, message='%s'}",
            success, orderId, transactionId, trackingNumber, totalAmount, message);
    }
}
