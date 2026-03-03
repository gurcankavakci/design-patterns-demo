package com.designpatterns.structural.composite;

/**
 * COMPOSITE PATTERN - Bileşik Nesne Deseni
 *
 * <p><b>TANIM:</b> Nesneleri ağaç yapısında düzenleyerek parça-bütün hiyerarşilerini
 * temsil eder. Client, tekil nesneleri ve bileşik nesneleri aynı şekilde işler.
 *
 * <p><b>PROBLEM:</b> ShopEase'de ürünler kategorilere, kategoriler de üst kategorilere
 * aittir. "Elektronik" gt; "Bilgisayar" gt; "Laptop" gibi. Hem tek ürün üzerinde
 * hem de tüm kategori üzerinde aynı işlemleri yapmak istiyoruz.
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Component (CategoryComponent): ortak interface</li>
 *   <li>Leaf (ProductLeaf): alt nesne yok, gerçek iş yapan</li>
 *   <li>Composite (ProductCategory): alt bileşenler içerir, Leaf'lara iletir</li>
 * </ul>
 *
 * <pre>
 * UML DIAGRAM:
 * ┌───────────────────────┐
 * │ {@literal <<interface>>}         │
 * │ CategoryComponent     │
 * ├───────────────────────┤
 * │ + display(depth)      │
 * │ + getTotalPrice()     │
 * │ + getName()           │
 * │ + getItemCount()      │
 * └──────────┬────────────┘
 *            │ implements
 *     ┌──────┴───────────┐
 * ┌───┴────────┐  ┌──────┴──────────────────────┐
 * │ProductLeaf │  │ProductCategory (Composite)   │
 * │(tek ürün)  │  │- children: List{@literal <CategoryComp>}│
 * └────────────┘  │+ add(component)              │
 *                 │+ remove(component)            │
 *                 └──────────────────────────────┘
 * </pre>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Şirket organizasyon şeması. CEO altında direktörler, direktörler altında
 * müdürler, müdürler altında çalışanlar. Hepsi "çalışan" gibi davranır:
 * maaş alır, raporlar, izin kullanır. Ama direktörlerin altındakiler var.
 *
 * <p><b>CLIENT KODU DEGIŞMEZ:</b><br>
 * {@code component.display()} - Hem tek ürün, hem tüm kategori için aynı çağrı!
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Karmaşık ağaç yapıları kolayca yönetilir</li>
 *   <li>Client kodu basitleşir (tekil ve bileşik aynı)</li>
 *   <li>Open/Closed: Yeni leaf/composite tipler eklenebilir</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Tüm componentlere ortak interface tasarlamak zor olabilir</li>
 *   <li>Tip güvenliği zayıflayabilir</li>
 * </ul>
 *
 * <p><b>NE ZAMAN KULLANILIR:</b>
 * <ul>
 *   <li>Ağaç yapıları (dosya sistemi, organizasyon şeması, menüler)</li>
 *   <li>Parça-bütün ilişkileri</li>
 *   <li>Tekil ve bileşik nesnelerin aynı muameleyi görmesi gerektiğinde</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface CategoryComponent {

    /**
     * Bileşeni hiyerarşik olarak görüntüler.
     *
     * <p>depth parametresi girintileme seviyesini belirler.
     * Leaf'lar son düğüm olarak, Composite'ler alt bileşenleriyle birlikte gösterilir.
     *
     * @param depth Ağaçtaki derinlik seviyesi (0 = kök)
     */
    void display(int depth);

    /**
     * Bileşenin toplam fiyatını döner.
     *
     * <p>Leaf: ürünün fiyatını döner.
     * Composite: tüm alt bileşenlerin toplam fiyatını döner.
     *
     * @return Toplam fiyat (TL)
     */
    double getTotalPrice();

    /**
     * Bileşenin adını döner.
     *
     * @return Ürün adı veya kategori adı
     */
    String getName();

    /**
     * Bu bileşen altındaki toplam ürün sayısını döner.
     *
     * <p>Leaf: 1 döner.
     * Composite: tüm alt bileşenlerin ürün sayılarının toplamını döner.
     *
     * @return Ürün sayısı
     */
    int getItemCount();

    /**
     * Bileşen tipini döner.
     *
     * @return "LEAF" veya "COMPOSITE"
     */
    String getType();
}
