package com.designpatterns.behavioral.visitor;

/**
 * VISITOR PATTERN - Ziyaretci Deseni
 *
 * TANIM: Bir nesne yapisi uzerindeki operasyonlari, elemanlarin siniflarini
 * degistirmeden tanimlar. Yeni operasyon = yeni Visitor sinifi.
 *
 * PROBLEM: Farkli urun tiplerinde (elektronik, gida, dijital, giyim) farkli
 * indirim ve vergi hesaplama kurallari var.
 * Bu kurallari urun siniflarına koyarsak: God Class!
 * Yeni kural ekleyince tum urun siniflari degisir.
 *
 * DOUBLE DISPATCH COZUMU:
 * visitor.visit(product) -> Java tek dispatch (sadece visitor tipine bakiyor)
 * product.accept(visitor) -> visitor.visit(this) -> DOUBLE DISPATCH
 * (hem product tipi hem visitor tipi dikkate alinir)
 *
 * ROLLER:
 * - Visitor (ProductVisitor): her element tipi icin visit()
 * - ConcreteVisitor (DiscountVisitor, TaxVisitor): gercek operasyon
 * - Element (Visitable): accept(visitor) metodunu sunar
 * - ConcreteElement: gercek urun siniflari
 *
 * UML DIAGRAM:
 * +-------------------------------------+
 * | <<interface>> ProductVisitor        |
 * +-------------------------------------+
 * |+visitElectronics(ElectronicsProduct)|
 * |+visitFood(FoodProduct)              |
 * |+visitDigital(DigitalProduct)        |
 * |+visitClothing(ClothingProduct)      |
 * +------------------+------------------+
 *                    | implements
 *            DiscountVisitor   TaxCalculatorVisitor
 *
 * <<interface>> Visitable:
 * + accept(visitor): double
 *
 * OPEN/CLOSED:
 * Yeni operasyon -> yeni Visitor (Element siniflari degismez) (+)
 * Yeni Element -> tum Visitor'lar guncellenmeli (-) (trade-off)
 *
 * AVANTAJLAR:
 * + Element degismeden yeni operasyon ekleme
 * + Ilgili operasyonlar bir Visitor sinifinda toplanir
 * + Accumulating state (Visitor kendi state'ini tutar)
 *
 * DEZAVANTAJLAR:
 * - Yeni Element = tum Visitor'lar guncellenmeli
 * - Encapsulation zayiflayabilir
 */
public interface ProductVisitor {

    /**
     * Elektronik urunu ziyaret eder.
     *
     * @param product Ziyaret edilecek elektronik urun
     * @return Hesaplanan deger (indirim veya vergi tutari)
     */
    double visitElectronics(ElectronicsProduct product);

    /**
     * Gida urunu ziyaret eder.
     *
     * @param product Ziyaret edilecek gida urunu
     * @return Hesaplanan deger (indirim veya vergi tutari)
     */
    double visitFood(FoodProduct product);

    /**
     * Dijital urunu ziyaret eder.
     *
     * @param product Ziyaret edilecek dijital urun
     * @return Hesaplanan deger (indirim veya vergi tutari)
     */
    double visitDigital(DigitalProduct product);

    /**
     * Giyim urunu ziyaret eder.
     *
     * @param product Ziyaret edilecek giyim urunu
     * @return Hesaplanan deger (indirim veya vergi tutari)
     */
    double visitClothing(ClothingProduct product);

    /**
     * Ziyaretcinin adini dondurur.
     *
     * @return Ziyaretci adi
     */
    String getVisitorName();
}
