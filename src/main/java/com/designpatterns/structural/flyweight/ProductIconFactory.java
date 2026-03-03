package com.designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * FLYWEIGHT FACTORY - Flyweight nesnelerini yönetir.
 *
 * <p>Cache'de varsa mevcut nesneyi döndürür (Cache HIT),
 * yoksa yeni nesne oluşturur ve cache'e ekler (Cache MISS).
 *
 * <p>Bu sınıf utility sınıfıdır; örnek oluşturulamaz.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ProductIconFactory {

    /** Flyweight cache: iconType (büyük harf) -> ProductIcon nesnesi */
    private static final Map<String, ProductIcon> icons = new HashMap<>();

    /** Toplam getIcon() çağrı sayısı */
    private static int requestCount = 0;

    /** Utility class - örnek oluşturulamaz */
    private ProductIconFactory() {}

    /**
     * İkon tipine göre Flyweight nesnesini döner.
     * Cache'de varsa mevcut nesne paylaşılır, yoksa yeni oluşturulur.
     *
     * @param iconType İkon/kategori tipi (büyük/küçük harf fark etmez)
     * @return Paylaşılan ProductIcon nesnesi
     */
    public static ProductIcon getIcon(String iconType) {
        requestCount++;
        String key = iconType.toUpperCase();

        if (icons.containsKey(key)) {
            System.out.println("  [Flyweight Cache HIT] " + key + " - mevcut nesne döndürülüyor");
            return icons.get(key);
        }

        // Cache MISS: yeni nesne oluştur
        System.out.println("  [Flyweight Cache MISS] " + key + " - yeni nesne oluşturuluyor");
        ProductIcon icon = switch (key) {
            case "ELEKTRONIK" -> new ProductIcon("ELEKTRONIK", "#2196F3", "💻");
            case "GIYIM"      -> new ProductIcon("GIYIM",      "#E91E63", "👕");
            case "GIDA"       -> new ProductIcon("GIDA",       "#4CAF50", "🍎");
            case "KITAP"      -> new ProductIcon("KITAP",      "#FF9800", "📚");
            case "SPOR"       -> new ProductIcon("SPOR",       "#9C27B0", "⚽");
            case "KOZMETIK"   -> new ProductIcon("KOZMETIK",   "#F44336", "💄");
            default           -> new ProductIcon(key,          "#607D8B", "📦");
        };

        icons.put(key, icon);
        return icon;
    }

    /**
     * Flyweight istatistiklerini ekrana yazdırır.
     * Cache etkinliğini raporlar.
     */
    public static void printStats() {
        System.out.println("  === Flyweight İstatistikleri ===");
        System.out.println("  Toplam istek: " + requestCount);
        System.out.println("  Oluşturulan nesne: " + icons.size());
        System.out.println("  Cache hit oranı: %" +
                (requestCount > 0 ? (requestCount - icons.size()) * 100 / requestCount : 0));
        System.out.println("  Önlenen nesne oluşturma: " + (requestCount - icons.size()));
    }

    /**
     * @return Cache'deki mevcut Flyweight nesne sayısı
     */
    public static int getCacheSize() {
        return icons.size();
    }

    /**
     * @return Toplam getIcon() çağrı sayısı
     */
    public static int getRequestCount() {
        return requestCount;
    }
}
