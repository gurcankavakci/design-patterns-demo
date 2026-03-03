package com.designpatterns.structural.bridge;

/**
 * SMS SENDER - SMS Gönderici (ConcreteImplementor)
 *
 * <p>{@link NotificationSender} interface'inin SMS implementasyonu.
 * Bridge Pattern'da ConcreteImplementor rolünü üstlenir.
 *
 * <p>Türk Telekom API üzerinden SMS gönderimini simüle eder.
 * SMS'de konu başlığı kullanılamaz, mesaja dahil edilir.
 * SMS karakter limiti: 160 karakter (tek SMS). Daha uzunu birden fazla SMS.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class SMSSender implements NotificationSender {

    /** SMS başına maksimum karakter sayısı */
    private static final int MAX_SMS_LENGTH = 160;

    /**
     * SMS gönderir.
     *
     * <p>SMS kanalında subject ve message birleştirilerek gönderilir.
     * Mesaj 160 karakteri geçerse birden fazla SMS olarak iletilir.
     *
     * @param recipient Alıcı telefon numarası (ülke kodu dahil: +90...)
     * @param subject   SMS başlığı (mesajın başına eklenir)
     * @param message   SMS içeriği
     * @return true gönderim başarılı ise
     */
    @Override
    public boolean sendMessage(String recipient, String subject, String message) {
        String fullMessage = subject + ": " + message;

        // İlk 100 karakterini göster (SMS önizlemesi)
        String preview = fullMessage.length() > 100
                ? fullMessage.substring(0, 100)
                : fullMessage;

        int smsCount = (int) Math.ceil((double) fullMessage.length() / MAX_SMS_LENGTH);

        System.out.println("  [SMS] Gönderiliyor...");
        System.out.println("    Tel    : " + recipient);
        System.out.println("    Mesaj  : " + preview);
        System.out.printf("    Uzunluk: %d karakter (%d SMS)%n", fullMessage.length(), smsCount);
        System.out.println("    Durum  : GONDERILDI (Turk Telekom API)");
        return true;
    }

    @Override
    public String getSenderType() {
        return "SMS";
    }

    @Override
    public String getDeliveryInfo() {
        return "SMS Gateway: Turk Telekom API | Kisa Mesaj Servisi | Max " + MAX_SMS_LENGTH + " karakter/SMS";
    }
}
