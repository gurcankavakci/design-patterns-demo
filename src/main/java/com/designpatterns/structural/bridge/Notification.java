package com.designpatterns.structural.bridge;

/**
 * NOTIFICATION - Bildirim Soyut Sınıfı (Abstraction)
 *
 * <p>Bridge Pattern'da Abstraction katmanıdır. Yüksek seviye bildirim
 * mantığını tanımlar. Alt sınıflar (RefinedAbstraction) bildirim türlerini,
 * {@link NotificationSender} implementasyonları ise kanalları temsil eder.
 *
 * <p><b>BRIDGE'İN ÖZÜ:</b><br>
 * Bu sınıf bir {@link NotificationSender} referansı tutar (köprü bağlantısı).
 * Bildirim gönderme işini NotificationSender'a delege eder.
 * Böylece bildirim türü ve gönderim kanalı birbirinden bağımsız değişebilir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public abstract class Notification {

    /**
     * Bridge bağlantısı - Implementor referansı.
     *
     * <p>Bu alan alt sınıflar tarafından kullanılabilir (protected).
     * Runtime'da {@link #changeSender(NotificationSender)} ile değiştirilebilir.
     */
    protected NotificationSender sender;

    /**
     * Constructor: Bir NotificationSender ile bildirim oluşturur.
     *
     * @param sender Kullanılacak gönderici kanal implementasyonu
     */
    protected Notification(NotificationSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException("NotificationSender null olamaz!");
        }
        this.sender = sender;
        System.out.println("[Notification] Bildirim nesnesi oluşturuldu. Kanal: " + sender.getSenderType());
    }

    /**
     * Bildirimi gönderir. Alt sınıflar bu methodu implement etmelidir.
     *
     * <p>Her bildirim türü (sipariş, promosyon vb.) mesaj içeriğini
     * kendi formatında oluşturur ve sender'a iletir.
     *
     * @param recipient Alıcı adresi (email/telefon/device ID)
     * @param content   Bildirim içeriği (sipariş detayları, promosyon açıklaması vb.)
     */
    public abstract void send(String recipient, String content);

    /**
     * Bu bildirim nesnesinin türünü döner.
     *
     * @return Bildirim tipi adı (örn: "Siparis Bildirimi")
     */
    public abstract String getNotificationType();

    /**
     * Gönderici kanalını runtime'da değiştirir.
     *
     * <p>Bu Bridge Pattern'ın güçlü özelliğidir: aynı bildirim nesnesi
     * farklı kanallardan gönderilebilir, nesneyi yeniden oluşturmaya gerek yok.
     *
     * @param newSender Yeni gönderici kanal
     */
    public void changeSender(NotificationSender newSender) {
        if (newSender == null) {
            throw new IllegalArgumentException("Yeni sender null olamaz!");
        }
        System.out.printf("[Notification] Gönderici değiştirildi: %s -> %s%n",
                          this.sender.getSenderType(), newSender.getSenderType());
        this.sender = newSender;
    }

    /**
     * Mevcut gönderici kanalının bilgilerini döner.
     *
     * @return Kanal bilgileri
     */
    public String getSenderInfo() {
        return sender.getSenderType() + " | " + sender.getDeliveryInfo();
    }
}
