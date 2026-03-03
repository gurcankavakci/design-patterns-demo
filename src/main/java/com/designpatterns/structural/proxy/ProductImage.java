package com.designpatterns.structural.proxy;

/**
 * PROXY PATTERN - Vekil Deseni
 *
 * <p><b>TANIM:</b> Başka bir nesneye erişimi kontrol eden bir vekil (surrogate) sağlar.
 *
 * <p><b>PROXY TİPLERİ:</b>
 * <ol>
 *   <li>Virtual Proxy: Pahalı nesne lazy loading ile erteler (bu örnekte)</li>
 *   <li>Protection Proxy: Erişim kontrolü (bu örnekte role-based)</li>
 *   <li>Remote Proxy: Uzak nesneye yerel temsil</li>
 *   <li>Caching Proxy: Sonuçları cache'ler (bu örnekte - tekrar yükleme yok)</li>
 * </ol>
 *
 * <p><b>PROBLEM:</b> 100 ürün görseli, her biri 5MB. Hepsini aynı anda yüklersek 500MB!
 * Proxy ile sadece görüntülenen görsel yüklenir (Lazy Loading).
 *
 * <pre>
 * UML DIAGRAM:
 * ┌────────────────────────┐
 * │   &lt;&lt;interface&gt;&gt;        │
 * │   ProductImage         │
 * └──────────┬─────────────┘
 *            │ implements
 *     ┌──────┴──────────────┐
 * ┌───┴──────────┐  ┌───────┴────────────────────────────┐
 * │RealProductImg│  │ProductImageProxy                    │
 * │(yüklenmesi   │  │- realImage: null! (lazy)            │
 * │ pahalı)      │  │- Erişim kontrolü yapar              │
 * └──────────────┘  └─────────────────────────────────────┘
 * </pre>
 *
 * <p><b>DECORATOR vs PROXY:</b>
 * <ul>
 *   <li>Decorator: Davranış EKLER</li>
 *   <li>Proxy: Erişimi KONTROL EDER (lazy loading, security, caching)</li>
 * </ul>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Banka kartı = Banka hesabının proxy'si. Her işlemde bankaya gitmenize gerek yok.
 * Kart işlemi yönetir, büyük işlemlerde bankaya danışır.
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Lazy initialization (performans iyileşmesi)</li>
 *   <li>Erişim kontrolü (güvenlik)</li>
 *   <li>Şeffaf - client aynı interface'i kullanır</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Ek sınıf (karmaşıklık)</li>
 *   <li>Yanıt gecikmesi olabilir</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface ProductImage {

    /**
     * Görseli yükler. Gerçek nesne oluşturulur ve binary veri indirilir.
     */
    void load();

    /**
     * Ürün görselini ekranda gösterir.
     *
     * @param productName Görselin ait olduğu ürün adı
     */
    void display(String productName);

    /**
     * @return Görselin URL adresi
     */
    String getUrl();

    /**
     * @return Görselin boyutu (KB cinsinden)
     */
    long getFileSizeKB();

    /**
     * @return Görsel belleğe yüklenmiş mi?
     */
    boolean isLoaded();
}
