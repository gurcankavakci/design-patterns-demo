package com.designpatterns.structural.proxy;

/**
 * REAL SUBJECT - Gerçek görsel yükleyici.
 *
 * <p>Pahalı yükleme işlemi yapan asıl nesne.
 * Constructor çağrılması görsel yüklemez; yükleme {@code load()} ile tetiklenir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class RealProductImage implements ProductImage {

    private final String url;
    private final long fileSizeKB;
    private boolean loaded = false;
    private byte[] imageData;

    /**
     * Gerçek görsel nesnesini oluşturur. Yükleme YAPMAZ.
     *
     * @param url        Görselin URL adresi
     * @param fileSizeKB Görselin boyutu (KB)
     */
    public RealProductImage(String url, long fileSizeKB) {
        this.url = url;
        this.fileSizeKB = fileSizeKB;
        // Kasıtlı olarak burada yükleme yapılmıyor - lazy loading proxy tarafından yönetilir
    }

    /**
     * Görseli ağdan indirir (simüle edilmiş).
     * Zaten yüklüyse tekrar yükleme yapılmaz (caching davranışı).
     */
    @Override
    public void load() {
        if (loaded) {
            System.out.println("  [RealImage] Zaten yüklü, tekrar yükleme atlanıyor.");
            return;
        }
        System.out.println("  [RealImage] Görsel indiriliyor: " + url + " (" + fileSizeKB + " KB)...");
        long start = System.currentTimeMillis();
        // Ağ gecikmesi simülasyonu - CPU spin
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }
        imageData = ("BINARY_IMAGE_DATA:" + url).getBytes();
        loaded = true;
        System.out.println("  [RealImage] Yükleme tamamlandı (" + (System.currentTimeMillis() - start) + "ms)");
    }

    /**
     * Görseli görüntüler. Yüklü değilse önce yükler.
     *
     * @param productName Ürün adı
     */
    @Override
    public void display(String productName) {
        if (!loaded) {
            load();
        }
        System.out.println("  [RealImage] Görüntüleniyor: " + productName
                + " | " + url + " | " + fileSizeKB + "KB");
    }

    @Override
    public boolean isLoaded() { return loaded; }

    @Override
    public String getUrl() { return url; }

    @Override
    public long getFileSizeKB() { return fileSizeKB; }
}
