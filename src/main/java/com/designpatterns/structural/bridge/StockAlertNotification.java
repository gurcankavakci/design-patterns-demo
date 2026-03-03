package com.designpatterns.structural.bridge;

/**
 * STOCK ALERT NOTIFICATION - Stok Uyarı Bildirimi (RefinedAbstraction)
 *
 * <p>{@link Notification} sınıfının stok uyarısı bildirimi implementasyonu.
 * Bridge Pattern'da RefinedAbstraction rolünü üstlenir.
 *
 * <p>Düşük stok seviyelerini operasyon ekibine veya müşterilere
 * (favori ürünü için) bildirmek amacıyla kullanılır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class StockAlertNotification extends Notification {

    /** Ürün adı */
    private final String productName;

    /** Kalan stok miktarı */
    private final int remainingStock;

    /**
     * Stok uyarı bildirimi constructor'ı.
     *
     * @param sender         Kullanılacak gönderici kanal
     * @param productName    Düşük stoku olan ürünün adı
     * @param remainingStock Kalan stok adedi
     */
    public StockAlertNotification(NotificationSender sender, String productName, int remainingStock) {
        super(sender);
        this.productName = productName;
        this.remainingStock = remainingStock;
    }

    /**
     * Stok uyarı bildirimi gönderir.
     *
     * <p>Kritik stok durumunu vurgulayan bir mesaj oluşturur.
     *
     * @param recipient Alıcı adresi (genellikle operasyon ekibi)
     * @param content   Ek bilgi (tedarik süresi, yeniden sipariş önerisi vb.)
     */
    @Override
    public void send(String recipient, String content) {
        System.out.println("  [StockAlertNotification] Stok uyarisi hazirlanıyor...");

        String urgencyLabel = remainingStock <= 5 ? "[KRITIK] " : "[UYARI] ";
        String subject = urgencyLabel + "Stok Uyarisi: " + productName;
        String message = productName + " ürününde dusuk stok! Kalan: " +
                         remainingStock + " adet. " + content;

        boolean sent = sender.sendMessage(recipient, subject, message);

        if (sent) {
            System.out.printf("  [StockAlertNotification] Stok uyarisi gonderildi -> %s (Kanal: %s)%n",
                              recipient, sender.getSenderType());
        } else {
            System.err.println("  [StockAlertNotification] Bildirim gonderilemedi!");
        }
    }

    @Override
    public String getNotificationType() {
        return "Stok Uyarisi";
    }

    public String getProductName() { return productName; }
    public int getRemainingStock() { return remainingStock; }
}
