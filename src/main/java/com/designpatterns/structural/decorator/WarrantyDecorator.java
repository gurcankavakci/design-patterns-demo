package com.designpatterns.structural.decorator;

/**
 * WARRANTY DECORATOR - Genişletilmiş Garanti Dekoratörü (ConcreteDecorator)
 *
 * <p>{@link ProductDecorator}'dan türeyen somut dekoratör.
 * Ürüne genişletilmiş garanti özelliği ekler.
 *
 * <p>Garanti süresi yıl cinsinden belirlenir. Her yıl için 150 TL ek ücret alınır.
 * Garanti belgesi dijital olarak gönderilir, ağırlık değişmez.
 *
 * <p><b>KULLANIM ÖRNEĞI:</b>
 * <pre>
 * ShopProduct product = new BasicProduct(rawProduct);
 * product = new WarrantyDecorator(product, 3); // 3 yıl garanti = +450 TL
 * </pre>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class WarrantyDecorator extends ProductDecorator {

    /** Garanti süresi (yıl) */
    private final int warrantyYears;

    /** Yıl başına garanti ücreti (TL) */
    private static final double WARRANTY_PRICE_PER_YEAR = 150.0;

    /**
     * Garanti dekoratörü oluşturur.
     *
     * @param product       Wrap edilecek ürün
     * @param warrantyYears Garanti süresi (yıl, minimum 1)
     * @throws IllegalArgumentException warrantyYears 1'den küçükse
     */
    public WarrantyDecorator(ShopProduct product, int warrantyYears) {
        super(product);
        if (warrantyYears < 1) {
            throw new IllegalArgumentException("Garanti suresi en az 1 yil olmalidir!");
        }
        this.warrantyYears = warrantyYears;
    }

    /**
     * Ürün adına garanti bilgisini ekler.
     *
     * @return "+N Yıl Garanti" eklenmiş ürün adı
     */
    @Override
    public String getName() {
        return wrappedProduct.getName() + " +" + warrantyYears + " Yil Garanti";
    }

    /**
     * Baz fiyata garanti ücretini ekler.
     *
     * @return Baz fiyat + (warrantyYears * 150) TL
     */
    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + (warrantyYears * WARRANTY_PRICE_PER_YEAR);
    }

    /**
     * Açıklamaya garanti bilgisini ekler.
     *
     * @return Genişletilmiş açıklama
     */
    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() +
               " | Genisletilmis garanti: " + warrantyYears + " yil";
    }

    /**
     * Garanti belgesi dijital gönderilir, ağırlık değişmez.
     *
     * @return Baz ağırlık (değişmez)
     */
    @Override
    public double getWeight() {
        return wrappedProduct.getWeight();
    }

    /**
     * Ürün bilgilerini ve garanti detaylarını gösterir.
     */
    @Override
    public void displayProductInfo() {
        super.displayProductInfo();
        double warrantyPrice = warrantyYears * WARRANTY_PRICE_PER_YEAR;
        System.out.printf("  + Garanti        : %d yil (+%.2f TL) | Belge: Dijital gonderilir%n",
                          warrantyYears, warrantyPrice);
    }

    public int getWarrantyYears() { return warrantyYears; }
    public double getWarrantyPrice() { return warrantyYears * WARRANTY_PRICE_PER_YEAR; }
}
