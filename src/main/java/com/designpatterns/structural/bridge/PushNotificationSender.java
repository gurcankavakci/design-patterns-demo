package com.designpatterns.structural.bridge;

/**
 * PUSH NOTIFICATION SENDER - Anlık Bildirim Gönderici (ConcreteImplementor)
 *
 * <p>{@link NotificationSender} interface'inin Firebase Cloud Messaging (FCM)
 * implementasyonu. Bridge Pattern'da ConcreteImplementor rolünü üstlenir.
 *
 * <p>Mobil cihazlara anlık bildirim gönderimini simüle eder.
 * Push bildirimler sadece uygulama yüklü ve bildirim izni olan kullanıcılara ulaşır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class PushNotificationSender implements NotificationSender {

    /** FCM proje ID */
    private static final String FCM_PROJECT_ID = "shopease-mobile-prod";

    /**
     * Push bildirim gönderir.
     *
     * @param recipient Hedef cihaz ID'si (FCM token)
     * @param subject   Bildirim başlığı (telefon ekranında görünen başlık)
     * @param message   Bildirim içeriği
     * @return true gönderim başarılı ise
     */
    @Override
    public boolean sendMessage(String recipient, String subject, String message) {
        // Device ID çok uzunsa kısalt (gösterim için)
        String displayDeviceId = recipient.length() > 20
                ? recipient.substring(0, 17) + "..."
                : recipient;

        System.out.println("  [PUSH] Gönderiliyor...");
        System.out.println("    Device ID : " + displayDeviceId);
        System.out.println("    Baslik    : " + subject);
        System.out.println("    Icerik    : " + message);
        System.out.println("    Platform  : FCM (" + FCM_PROJECT_ID + ")");
        System.out.println("    Durum     : GONDERILDI");
        return true;
    }

    @Override
    public String getSenderType() {
        return "Push Bildirim";
    }

    @Override
    public String getDeliveryInfo() {
        return "FCM: Firebase Cloud Messaging | Anlik bildirim | Proje: " + FCM_PROJECT_ID;
    }
}
