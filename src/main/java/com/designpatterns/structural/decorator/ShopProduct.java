package com.designpatterns.structural.decorator;

/**
 * DECORATOR PATTERN - Dekoratör Deseni
 *
 * <p><b>TANIM:</b> Bir nesneye, sınıfını değiştirmeden dinamik olarak yeni
 * sorumluluklar ekler. Kalıtıma alternatif olarak esneklik sağlar.
 *
 * <p><b>PROBLEM:</b> Ürünlere farklı kombinasyonlarda özellik eklemek istiyoruz:
 * Hediye paketi, garanti, ekspres teslimat.
 *
 * <p><b>INHERITANCE PATLAMA PROBLEMİ (olmadan):</b><br>
 * ProductWithGift, ProductWithWarranty, ProductWithExpress,<br>
 * ProductWithGiftAndWarranty, ProductWithGiftAndExpress,<br>
 * ProductWithWarrantyAndExpress, ProductWithGiftAndWarrantyAndExpress...<br>
 * 2^n kombinasyon!
 *
 * <p><b>DECORATOR ÇÖZÜMÜ:</b> Dinamik sarma
 * <pre>
 * ShopProduct p = new BasicProduct(rawProduct);
 * p = new WarrantyDecorator(p, 2);       // +2 yıl garanti
 * p = new GiftWrapDecorator(p, "Tebrikler!");  // +Hediye paketi
 * p = new ExpressDeliveryDecorator(p);   // +Ekspres teslimat
 * </pre>
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Component (ShopProduct): ortak interface</li>
 *   <li>ConcreteComponent (BasicProduct): decorator olmadan temel nesne</li>
 *   <li>Decorator (ProductDecorator): abstract, ShopProduct'ı wrap eder</li>
 *   <li>ConcreteDecorator (GiftWrap, Warranty, Express): özellik ekler</li>
 * </ul>
 *
 * <pre>
 * UML DIAGRAM:
 * ┌─────────────────────────────┐
 * │    {@literal <<interface>>}            │
 * │    ShopProduct              │
 * ├─────────────────────────────┤
 * │ + getName(): String         │
 * │ + getPrice(): double        │
 * │ + getDescription(): String  │
 * │ + getWeight(): double       │
 * └────────┬────────────────────┘
 *          │ implements
 * ┌────────┴────────┐    ┌──────────────────────────────┐
 * │  BasicProduct   │    │  ProductDecorator (abstract) │
 * │  (base nesne)   │    │  - wrappedProduct: ShopProduct│
 * └─────────────────┘    └──────────────┬───────────────┘
 *                                       │ extends
 *                          ┌────────────┼──────────────┐
 *                    ┌─────┴──┐  ┌──────┴───┐  ┌───────┴──────┐
 *                    │GiftWrap│  │Warranty  │  │ExpressDelivery│
 *                    │Decorator│ │Decorator │  │Decorator     │
 *                    └────────┘  └──────────┘  └──────────────┘
 * </pre>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Kahve siparişi: Espresso + Süt = Latte, Latte + Çikolata = Mocha.
 * Her eklenti fiyata ve içeriğe katkı sağlar.
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Kalıtıma gerek kalmadan sorumluluk eklenir</li>
 *   <li>Kombinasyonlar runtime'da belirlenir</li>
 *   <li>Single Responsibility: Her decorator tek şey yapar</li>
 *   <li>Open/Closed Principle</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Çok sayıda küçük dekoratör sınıfı</li>
 *   <li>Dekoratör sırası önemli olabilir</li>
 *   <li>Bir özel dekoratörü kaldırmak zor</li>
 * </ul>
 *
 * <p><b>NE ZAMAN KULLANILIR:</b>
 * <ul>
 *   <li>Sınıf hiyerarşisini patlatmadan davranış eklemek</li>
 *   <li>Runtime'da özellik kombinasyonları</li>
 *   <li>java.io stream'leri (BufferedReader, InputStreamReader...)</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface ShopProduct {

    /**
     * Ürünün tam adını döner.
     * Dekoratörler bu ada eklemeler yapar.
     *
     * @return Ürün adı (dekoratör eklemeleriyle)
     */
    String getName();

    /**
     * Ürünün toplam fiyatını döner.
     * Dekoratörler baz fiyata ek ücretler ekler.
     *
     * @return Toplam fiyat (TL)
     */
    double getPrice();

    /**
     * Ürünün açıklamasını döner.
     * Dekoratörler açıklamayı genişletir.
     *
     * @return Ürün açıklaması
     */
    String getDescription();

    /**
     * Ürünün toplam ağırlığını döner.
     * Dekoratörler (hediye paket kağıdı, kutu vb.) ağırlık ekleyebilir.
     *
     * @return Toplam ağırlık (kg)
     */
    double getWeight();

    /**
     * Ürün bilgilerini konsola yazdırır.
     * Her dekoratör baz bilgiyi gösterdikten sonra kendi eklemesini yazdırır.
     */
    void displayProductInfo();
}
