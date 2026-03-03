package com.designpatterns.structural.flyweight;

/**
 * FLYWEIGHT PATTERN - Sinek Siklet Deseni
 *
 * <p><b>TANIM:</b> Çok sayıda benzer nesneyi verimli şekilde desteklemek için paylaşım kullanır.
 *
 * <p><b>INTRINSIC vs EXTRINSIC STATE:</b>
 * <ul>
 *   <li>Intrinsic (paylaşılan): iconType, iconColor, iconSymbol - nesne içinde saklanır</li>
 *   <li>Extrinsic (context'e özgü): productName, price, position - dışarıdan verilir</li>
 * </ul>
 *
 * <p><b>PROBLEM:</b> 100.000 ürün var, her biri kategori ikonu gösteriyor.
 * "Elektronik" kategorisinde 50.000 ürün = 50.000 identik ikon nesnesi → RAM israfı!
 * Flyweight ile tek bir ikon nesnesi paylaşılır.
 *
 * <p><b>BELLEK TASARRUFU:</b>
 * <ul>
 *   <li>Olmadan: 100,000 nesne x 1KB = ~100MB</li>
 *   <li>Flyweight ile: 10 nesne x 1KB = ~10KB</li>
 * </ul>
 *
 * <pre>
 * UML DIAGRAM:
 * ┌──────────────────────────┐     ┌───────────────────────────────┐
 * │  ProductIconFactory      │     │  ProductIcon (Flyweight)       │
 * │  (Flyweight Factory)     │────>│  - iconType: String (intrinsic)│
 * ├──────────────────────────┤     │  - iconColor: String           │
 * │-icons: Map<String, Icon> │     │  - iconSymbol: String          │
 * │+getIcon(type): Icon      │     ├───────────────────────────────┤
 * └──────────────────────────┘     │+display(name, price, position)│
 *                                  │  (extrinsic state as params)  │
 *                                  └───────────────────────────────┘
 * </pre>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Word'de 'A' harfi binlerce kez kullanılır ama her instance ayrı font/style
 * nesnesi tutmaz. Font bilgisi paylaşılır, sadece konum (extrinsic) değişir.
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Dramatik bellek tasarrufu</li>
 *   <li>Nesne oluşturma maliyetini azaltır</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Intrinsic/extrinsic ayrımı karmaşıklık katar</li>
 *   <li>Lookup maliyeti (factory'den alma)</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ProductIcon {

    // Intrinsic state - paylaşılan, değişmeyen veriler
    private final String iconType;
    private final String iconColor;
    private final String iconSymbol;
    private final byte[] iconData;

    /**
     * Yeni bir ProductIcon oluşturur.
     * Bu constructor sadece cache MISS durumunda çağrılmalıdır.
     *
     * @param iconType   İkon kategorisi (ör: "ELEKTRONIK")
     * @param iconColor  İkon rengi (hex ör: "#2196F3")
     * @param iconSymbol İkon sembolü (ör: "💻")
     */
    public ProductIcon(String iconType, String iconColor, String iconSymbol) {
        this.iconType = iconType;
        this.iconColor = iconColor;
        this.iconSymbol = iconSymbol;
        this.iconData = iconType.getBytes(); // simüle - gerçekte PNG/SVG binary verisi olurdu
        System.out.println("  [Flyweight] Yeni ikon nesnesi oluşturuldu: " + iconType
                + " (Bu mesaj sadece bir kez görünmeli!)");
    }

    /**
     * Ürünü ekrana görüntüler. Extrinsic state parametre olarak alınır.
     *
     * @param productName Ürün adı (extrinsic)
     * @param price       Ürün fiyatı (extrinsic)
     * @param position    Listedeki sırası (extrinsic)
     */
    public void display(String productName, double price, int position) {
        // Extrinsic state parametre olarak alınır - bu nesne içinde saklanmaz
        System.out.printf("  %s %-12s | %-35s | %8.2f TL | Sira: %d%n",
                iconSymbol, "[" + iconType + "]", productName, price, position);
    }

    // Getters
    public String getIconType() { return iconType; }
    public String getIconColor() { return iconColor; }
    public String getIconSymbol() { return iconSymbol; }
}
