package com.designpatterns.behavioral.mediator;

/**
 * NOTIFICATION COLLEAGUE - Bildirim Bileşeni
 *
 * <p>Sistem olaylarına göre ilgili taraflara e-posta bildirimi gönderir.
 * Diğer bileşenlerle doğrudan iletişim kurmaz; tüm olaylar mediator üzerinden gelir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class NotificationColleague extends ShoppingColleague {

    public NotificationColleague() {
        super("Bildirim Bileşeni");
    }

    /**
     * E-posta gönderimini simüle eder.
     *
     * @param to      Alıcı e-posta adresi
     * @param message Mesaj içeriği
     */
    public void sendEmail(String to, String message) {
        System.out.println("  [Bildirim] E-posta -> " + to + ": " + message);
    }

    @Override
    public void receiveEvent(String event, Object data) {
        System.out.println("  [Bildirim] Olay işleniyor: " + event);
        switch (event) {
            case "LOW_STOCK_ALERT"    -> sendEmail("admin@shopease.com", "Düşük stok uyarısı: " + data);
            case "CHECKOUT_INITIATED" -> sendEmail("musteri@shopease.com", "Siparişiniz işleniyor...");
            case "PRODUCT_REMOVED"    -> System.out.println("  [Bildirim] Ürün silme kaydedildi");
            default                   -> System.out.println("  [Bildirim] Bilinmeyen olay: " + event);
        }
    }
}
