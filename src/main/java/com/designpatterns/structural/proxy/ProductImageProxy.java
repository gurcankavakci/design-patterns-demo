package com.designpatterns.structural.proxy;

/**
 * PROXY - Vekil nesne.
 *
 * <p>Virtual Proxy + Protection Proxy + Caching Proxy kombinasyonu:
 * <ul>
 *   <li><b>Virtual Proxy:</b> RealProductImage lazy olarak oluşturulur (null başlar)</li>
 *   <li><b>Protection Proxy:</b> Role-based erişim kontrolü (public / user / admin)</li>
 *   <li><b>Caching Proxy:</b> Görsel bir kez yüklendikten sonra yeniden indirilmez</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ProductImageProxy implements ProductImage {

    /** Gerçek görsel nesnesi - null = henüz oluşturulmadı (lazy) */
    private RealProductImage realImage = null;

    private final String url;
    private final long fileSizeKB;
    private int displayCount = 0;

    /** Erişim için gereken minimum rol seviyesi */
    private final String requiredRole;

    /** Mevcut kullanıcının rolü */
    private final String currentUserRole;

    /**
     * Proxy nesnesini oluşturur. Gerçek görsel henüz yüklenmez.
     *
     * @param url             Görselin URL adresi
     * @param fileSizeKB      Görselin boyutu (KB)
     * @param requiredRole    Erişim için gereken rol (public / user / admin)
     * @param currentUserRole Mevcut kullanıcının rolü
     */
    public ProductImageProxy(String url, long fileSizeKB,
                              String requiredRole, String currentUserRole) {
        this.url = url;
        this.fileSizeKB = fileSizeKB;
        this.requiredRole = requiredRole;
        this.currentUserRole = currentUserRole;
        System.out.println("  [Proxy] Oluşturuldu: " + url + " (gerçek görsel henüz yüklenmedi - lazy!)");
    }

    /**
     * Rol tabanlı erişim kontrolü.
     *
     * @return true kullanıcının erişim hakkı varsa
     */
    private boolean hasAccess() {
        return switch (requiredRole) {
            case "public" -> true;
            case "user"   -> currentUserRole.equals("user") || currentUserRole.equals("admin");
            case "admin"  -> currentUserRole.equals("admin");
            default       -> false;
        };
    }

    /**
     * Görseli yükler. Erişim kontrolü yapar, gerekirse gerçek nesneyi oluşturur.
     */
    @Override
    public void load() {
        System.out.println("  [Proxy] Yükleme isteği: " + url);
        if (!hasAccess()) {
            System.out.println("  [Proxy] ERİŞİM REDDEDİLDİ! Gerekli: " + requiredRole
                    + ", Mevcut: " + currentUserRole);
            return;
        }
        if (realImage == null) {
            System.out.println("  [Proxy] Gerçek nesne oluşturuluyor (ilk kez)...");
            realImage = new RealProductImage(url, fileSizeKB);
        }
        realImage.load();
    }

    /**
     * Görseli görüntüler. Cache kullanılır; yüklüyse tekrar indirme yapılmaz.
     *
     * @param productName Ürün adı
     */
    @Override
    public void display(String productName) {
        if (!hasAccess()) {
            System.out.println("  [Proxy] ERİŞİM REDDEDİLDİ: " + productName);
            return;
        }
        displayCount++;
        if (realImage != null && realImage.isLoaded()) {
            System.out.println("  [Proxy] Cache'den gösteriliyor (yeniden yükleme yok!) [Erişim #" + displayCount + "]");
        } else {
            load();
        }
        if (realImage != null) {
            realImage.display(productName);
        }
    }

    @Override
    public boolean isLoaded() {
        return realImage != null && realImage.isLoaded();
    }

    @Override
    public String getUrl() { return url; }

    @Override
    public long getFileSizeKB() { return fileSizeKB; }

    /**
     * @return Bu proxy üzerinden kaç kez display() çağrıldığı
     */
    public int getDisplayCount() { return displayCount; }
}
