package com.designpatterns.structural.decorator;

import com.designpatterns.model.Product;

/**
 * BASIC PRODUCT - Temel Ürün (ConcreteComponent)
 *
 * <p>Decorator Pattern'ın temel bileşenidir. Hiçbir dekoratör eklenmemiş,
 * ham ürün bilgisini taşır.
 *
 * <p>Bu sınıf dekorasyon zincirinin başlangıç noktasıdır. Tüm dekoratörler
 * doğrudan ya da dolaylı olarak bu nesneyi wrap eder.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class BasicProduct implements ShopProduct {

    /** Ham ürün verisi */
    private final Product product;

    /**
     * Belirtilen ürün için temel ürün nesnesi oluşturur.
     *
     * @param product Wrap edilecek ürün
     * @throws IllegalArgumentException product null ise
     */
    public BasicProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product null olamaz!");
        }
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public double getPrice() {
        return product.getPrice();
    }

    @Override
    public String getDescription() {
        if (product.getDescription() != null && !product.getDescription().isEmpty()) {
            return product.getDescription();
        }
        return product.getName() + " (" + product.getBrand() + ")";
    }

    @Override
    public double getWeight() {
        return product.getWeight();
    }

    @Override
    public void displayProductInfo() {
        System.out.println("  Urun    : " + product.getName());
        System.out.println("  Marka   : " + product.getBrand());
        System.out.println("  Kategori: " + product.getCategory());
        System.out.printf ("  Fiyat   : %.2f TL%n", product.getPrice());
        System.out.printf ("  Agirlik : %.2f kg%n", product.getWeight());
        System.out.println("  Stok    : " + product.getStockQuantity() + " adet");
        System.out.println("  Tur     : " + (product.isDigital() ? "Dijital" : "Fiziksel"));
    }

    /**
     * Ham ürüne doğrudan erişim sağlar.
     *
     * @return Product nesnesi
     */
    public Product getRawProduct() {
        return product;
    }
}
