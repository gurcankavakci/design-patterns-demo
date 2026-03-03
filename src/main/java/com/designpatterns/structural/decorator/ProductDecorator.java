package com.designpatterns.structural.decorator;

/**
 * PRODUCT DECORATOR - Soyut Ürün Dekoratörü (Abstract Decorator)
 *
 * <p>Tüm ürün dekoratörlerinin temel sınıfıdır. {@link ShopProduct} interface'ini
 * implement eder ve aynı zamanda bir {@link ShopProduct} referansı tutar.
 *
 * <p><b>ÖNEMLİ TASARIM KARARI:</b><br>
 * Bu sınıf hem ShopProduct'ı implement eder hem de bir ShopProduct içerir.
 * Bu çift rol Decorator Pattern'ın özüdür - decorator hem bir ShopProduct'tır
 * hem de başka bir ShopProduct'ı sarabilir (wrap edebilir).
 *
 * <p>Tüm metodlar varsayılan olarak iç nesneye (wrappedProduct) delege edilir.
 * Alt sınıflar sadece değiştirmek istedikleri metodları override eder.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public abstract class ProductDecorator implements ShopProduct {

    /**
     * Wrap edilen ürün nesnesi.
     *
     * <p>Bu alan alt sınıflar tarafından kullanılabilir (protected).
     * Çağrılar zinciri boyunca en içteki {@link BasicProduct}'a ulaşır.
     */
    protected final ShopProduct wrappedProduct;

    /**
     * Belirtilen ürünü wrap eden dekoratör oluşturur.
     *
     * @param product Wrap edilecek ürün (başka bir dekoratör olabilir)
     * @throws IllegalArgumentException product null ise
     */
    protected ProductDecorator(ShopProduct product) {
        if (product == null) {
            throw new IllegalArgumentException("Wrap edilecek product null olamaz!");
        }
        this.wrappedProduct = product;
    }

    /**
     * İç ürüne delege eder. Alt sınıflar override edip ek bilgi ekleyebilir.
     */
    @Override
    public String getName() {
        return wrappedProduct.getName();
    }

    /**
     * İç ürüne delege eder. Alt sınıflar override edip ek ücret ekleyebilir.
     */
    @Override
    public double getPrice() {
        return wrappedProduct.getPrice();
    }

    /**
     * İç ürüne delege eder. Alt sınıflar override edip açıklama ekleyebilir.
     */
    @Override
    public String getDescription() {
        return wrappedProduct.getDescription();
    }

    /**
     * İç ürüne delege eder. Alt sınıflar override edip ağırlık ekleyebilir.
     */
    @Override
    public double getWeight() {
        return wrappedProduct.getWeight();
    }

    /**
     * İç ürüne delege eder. Alt sınıflar super'i çağırıp ek bilgi yazdırabilir.
     */
    @Override
    public void displayProductInfo() {
        wrappedProduct.displayProductInfo();
    }
}
