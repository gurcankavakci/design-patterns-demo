package com.designpatterns.structural.composite;

import com.designpatterns.model.Product;

/**
 * PRODUCT LEAF - Ürün Yaprak Düğümü (Leaf)
 *
 * <p>Composite Pattern'da Leaf rolünü üstlenir. Ağacın uç noktasıdır.
 * Alt bileşen içermez - gerçek iş (fiyat, stok bilgisi) burada gerçekleşir.
 *
 * <p>Her {@link ProductLeaf} bir {@link Product} nesnesini wrap eder.
 * {@link CategoryComponent} interface'ini implement ederek hem single product
 * hem de category ağacı içinde uniform şekilde kullanılabilir.
 *
 * <p><b>LEAF'IN SORUMLULUKLARI:</b>
 * <ul>
 *   <li>Gerçek ürün verisini taşır</li>
 *   <li>getItemCount() her zaman 1 döner</li>
 *   <li>getTotalPrice() ürünün fiyatını döner</li>
 *   <li>Alt bileşen içermez, add/remove desteklenmez</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ProductLeaf implements CategoryComponent {

    /** Wrap edilen ürün nesnesi */
    private final Product product;

    /**
     * Belirtilen ürün için bir Leaf düğümü oluşturur.
     *
     * @param product Wrap edilecek ürün nesnesi
     * @throws IllegalArgumentException product null ise
     */
    public ProductLeaf(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product null olamaz!");
        }
        this.product = product;
    }

    /**
     * Ürünü derinlik seviyesine göre girinti ile görüntüler.
     *
     * @param depth Ağaç derinliği (girintileme için)
     */
    @Override
    public void display(int depth) {
        String indent = "    ".repeat(depth);
        System.out.printf("%s└─ %s - %.2f TL (Stok: %d adet, %s)%n",
                          indent,
                          product.getName(),
                          product.getPrice(),
                          product.getStockQuantity(),
                          product.isDigital() ? "Dijital" : "Fiziksel");
    }

    /**
     * Ürünün fiyatını döner.
     *
     * @return Ürün fiyatı (TL)
     */
    @Override
    public double getTotalPrice() {
        return product.getPrice();
    }

    /**
     * Ürünün adını döner.
     *
     * @return Ürün adı
     */
    @Override
    public String getName() {
        return product.getName();
    }

    /**
     * Her zaman 1 döner çünkü bu tek bir üründür.
     *
     * @return 1
     */
    @Override
    public int getItemCount() {
        return 1;
    }

    /**
     * Leaf tipini döner.
     *
     * @return "LEAF"
     */
    @Override
    public String getType() {
        return "LEAF";
    }

    /**
     * Wrap edilen ürüne erişim sağlar.
     *
     * @return Ürün nesnesi
     */
    public Product getProduct() {
        return product;
    }
}
