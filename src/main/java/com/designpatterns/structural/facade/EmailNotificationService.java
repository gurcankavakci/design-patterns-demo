package com.designpatterns.structural.facade;

/**
 * EMAIL NOTIFICATION SERVICE - E-posta Bildirim Servisi (Facade Alt Sistemi)
 *
 * <p>Facade Pattern'da subsystem (alt sistem) rolünü üstlenir.
 * Sipariş ile ilgili tüm e-posta bildirimlerini gönderir.
 *
 * <p>Client bu sınıfı doğrudan değil, {@link CheckoutFacade} üzerinden kullanır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class EmailNotificationService {

    /** Gönderici e-posta adresi */
    private static final String SENDER_EMAIL = "noreply@shopease.com";

    /**
     * E-posta bildirim servisini başlatır.
     */
    public EmailNotificationService() {
        System.out.println("  [EmailService] E-posta bildirim servisi baslatildi.");
    }

    /**
     * Sipariş onay e-postası gönderir.
     *
     * @param email   Alıcı e-posta adresi
     * @param orderId Sipariş kimliği
     * @param amount  Toplam tutar
     */
    public void sendOrderConfirmation(String email, String orderId, double amount) {
        System.out.printf("  [Email] Gönderiliyor -> %s%n", email);
        System.out.printf("         Konu  : Siparisiniiz Onaylandi! #%s%n", orderId);
        System.out.printf("         Icerik: Siparisiniiz basariyla alindi. Toplam: %.2f TL%n", amount);
        System.out.printf("         From  : %s%n", SENDER_EMAIL);
    }

    /**
     * Ödeme onay e-postası gönderir.
     *
     * @param email   Alıcı e-posta adresi
     * @param txnId   İşlem kimliği
     */
    public void sendPaymentConfirmation(String email, String txnId) {
        System.out.printf("  [Email] Gönderiliyor -> %s%n", email);
        System.out.printf("         Konu  : Odemeniz Alindi%n");
        System.out.printf("         Icerik: Odemeniz basariyla islendi. Islem No: %s%n", txnId);
        System.out.printf("         From  : %s%n", SENDER_EMAIL);
    }

    /**
     * Kargo bildirim e-postası gönderir.
     *
     * @param email      Alıcı e-posta adresi
     * @param trackingNo Kargo takip numarası
     */
    public void sendShipmentNotification(String email, String trackingNo) {
        System.out.printf("  [Email] Gönderiliyor -> %s%n", email);
        System.out.printf("         Konu  : Siparisiniiz Kargolandi!%n");
        System.out.printf("         Icerik: Kargolanmistir. Takip No: %s | shopease.com/track%n", trackingNo);
        System.out.printf("         From  : %s%n", SENDER_EMAIL);
    }

    /**
     * Sipariş iptal e-postası gönderir.
     *
     * @param email   Alıcı e-posta adresi
     * @param orderId İptal edilen sipariş kimliği
     */
    public void sendOrderCancellation(String email, String orderId) {
        System.out.printf("  [Email] Gönderiliyor -> %s%n", email);
        System.out.printf("         Konu  : Siparis Iptal Edildi: #%s%n", orderId);
        System.out.printf("         Icerik: Siparisiniiz iptal edilmistir. Iade 3-5 is gunu icinde yapilir.%n");
        System.out.printf("         From  : %s%n", SENDER_EMAIL);
    }
}
