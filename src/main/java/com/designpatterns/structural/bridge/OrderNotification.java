package com.designpatterns.structural.bridge;

/**
 * ORDER NOTIFICATION - Sipariş Bildirimi (RefinedAbstraction)
 *
 * <p>{@link Notification} sınıfının sipariş bildirimi implementasyonu.
 * Bridge Pattern'da RefinedAbstraction rolünü üstlenir.
 *
 * <p>Sipariş durum değişikliklerini müşterilere bildirmek için kullanılır.
 * Hangi kanal üzerinden gönderileceği {@link NotificationSender} implementasyonu
 * tarafından belirlenir - bu sınıf bunu bilmek zorunda değildir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class OrderNotification extends Notification {

    /** Sipariş kimliği */
    private final String orderId;

    /** Yeni sipariş durumu */
    private final String orderStatus;

    /**
     * Sipariş bildirimi constructor'ı.
     *
     * @param sender      Kullanılacak gönderici kanal
     * @param orderId     Sipariş kimliği
     * @param orderStatus Yeni sipariş durumu (ONAYLANDI, KARGOLANDI, TESLiM EDiLDi vb.)
     */
    public OrderNotification(NotificationSender sender, String orderId, String orderStatus) {
        super(sender);
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    /**
     * Sipariş bildirimi gönderir.
     *
     * <p>Sipariş ID ve durum bilgisini mesaj formatına dönüştürür,
     * ardından yapılandırılmış gönderici kanal üzerinden iletir.
     *
     * @param recipient Alıcı adresi
     * @param content   Ek bilgi (teslimat tarihi, notlar vb.)
     */
    @Override
    public void send(String recipient, String content) {
        System.out.println("  [OrderNotification] Siparis bildirimi hazirlanıyor...");

        String subject = "Siparis Guncelleme - #" + orderId;
        String message = "Siparisiniiz (#" + orderId + ") guncellendi. " +
                         "Yeni durum: " + orderStatus + ". " + content;

        boolean sent = sender.sendMessage(recipient, subject, message);

        if (sent) {
            System.out.printf("  [OrderNotification] Bildirim gonderildi -> %s (Kanal: %s)%n",
                              recipient, sender.getSenderType());
        } else {
            System.err.println("  [OrderNotification] Bildirim gonderilemedi!");
        }
    }

    @Override
    public String getNotificationType() {
        return "Siparis Bildirimi";
    }

    public String getOrderId() { return orderId; }
    public String getOrderStatus() { return orderStatus; }
}
