package com.designpatterns.structural.bridge;

/**
 * EMAIL SENDER - E-posta Gönderici (ConcreteImplementor)
 *
 * <p>{@link NotificationSender} interface'inin e-posta implementasyonu.
 * Bridge Pattern'da ConcreteImplementor rolünü üstlenir.
 *
 * <p>SMTP protokolü üzerinden e-posta gönderimini simüle eder.
 * Gerçek bir uygulamada JavaMail API veya SendGrid/Mailgun kullanılır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class EmailSender implements NotificationSender {

    /** SMTP sunucu adresi */
    private static final String SMTP_HOST = "mail.shopease.com";

    /** SMTP port numarası */
    private static final int SMTP_PORT = 587;

    /**
     * E-posta gönderir.
     *
     * @param recipient Alıcı e-posta adresi
     * @param subject   E-posta konusu
     * @param message   E-posta içeriği
     * @return true gönderim başarılı ise
     */
    @Override
    public boolean sendMessage(String recipient, String subject, String message) {
        System.out.println("  [EMAIL] Gönderiliyor...");
        System.out.println("    To      : " + recipient);
        System.out.println("    Subject : " + subject);

        // Mesajın ilk 50 karakterini göster
        String previewMsg = message.length() > 50
                ? message.substring(0, 50) + "..."
                : message;
        System.out.println("    Mesaj   : " + previewMsg);
        System.out.println("    Durum   : GONDERILDI (" + SMTP_HOST + ":" + SMTP_PORT + ")");
        return true;
    }

    @Override
    public String getSenderType() {
        return "E-posta";
    }

    @Override
    public String getDeliveryInfo() {
        return "SMTP: " + SMTP_HOST + ":" + SMTP_PORT + " | Sifrelenmis baglanti (TLS)";
    }
}
