package com.designpatterns.structural.bridge;

/**
 * PROMOTION NOTIFICATION - Promosyon Bildirimi (RefinedAbstraction)
 *
 * <p>{@link Notification} sınıfının promosyon bildirimi implementasyonu.
 * Bridge Pattern'da RefinedAbstraction rolünü üstlenir.
 *
 * <p>İndirim kampanyaları ve özel fırsatları müşterilere bildirmek için kullanılır.
 * Email, SMS veya Push kanalından bağımsız olarak aynı promosyon bilgisini iletir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class PromotionNotification extends Notification {

    /** Promosyon kodu */
    private final String promoCode;

    /** İndirim yüzdesi */
    private final double discountPercentage;

    /**
     * Promosyon bildirimi constructor'ı.
     *
     * @param sender             Kullanılacak gönderici kanal
     * @param promoCode          Kupon/promosyon kodu
     * @param discountPercentage İndirim yüzdesi (örn: 25.0 = %25)
     */
    public PromotionNotification(NotificationSender sender, String promoCode, double discountPercentage) {
        super(sender);
        this.promoCode = promoCode;
        this.discountPercentage = discountPercentage;
    }

    /**
     * Promosyon bildirimi gönderir.
     *
     * <p>Cazip bir başlık ve açıklayıcı mesaj oluşturur,
     * ardından yapılandırılmış kanal üzerinden iletir.
     *
     * @param recipient Alıcı adresi
     * @param content   Ek bilgi (geçerlilik tarihi, minimum sipariş tutarı vb.)
     */
    @Override
    public void send(String recipient, String content) {
        System.out.println("  [PromotionNotification] Promosyon bildirimi hazirlanıyor...");

        String subject = "Ozel Firsat - %" + discountPercentage + " Indirim!";
        String message = "PROMO: " + promoCode + " kodu ile %" + discountPercentage +
                         " indirim kazandınız! " + content;

        boolean sent = sender.sendMessage(recipient, subject, message);

        if (sent) {
            System.out.printf("  [PromotionNotification] Bildirim gonderildi -> %s (Kanal: %s)%n",
                              recipient, sender.getSenderType());
        } else {
            System.err.println("  [PromotionNotification] Bildirim gonderilemedi!");
        }
    }

    @Override
    public String getNotificationType() {
        return "Promosyon Bildirimi";
    }

    public String getPromoCode() { return promoCode; }
    public double getDiscountPercentage() { return discountPercentage; }
}
