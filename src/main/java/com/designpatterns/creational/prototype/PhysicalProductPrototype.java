package com.designpatterns.creational.prototype;

import com.designpatterns.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Fiziksel ürün prototipi.
 * Prototype Pattern'de "ConcretePrototype" rolünü oynar.
 *
 * Fiziksel özellikler (renk, boyut, malzeme) ve indirim yüzdesi içerir.
 * Deep copy ile tamamen bağımsız klonlar oluşturur.
 *
 * KULLANIM SENARYOSU:
 * Laptop şablonu oluştur → farklı konfigürasyonlar için klonla:
 *   - Klon 1: RAM = 16GB, SSD = 512GB (MacBook Pro 14")
 *   - Klon 2: RAM = 32GB, SSD = 1TB  (MacBook Pro 16")
 *   - Klon 3: RAM = 8GB,  SSD = 256GB (MacBook Air)
 * Tüm klonlar bağımsızdır; birini değiştirmek diğerlerini etkilemez.
 */
public class PhysicalProductPrototype implements ProductPrototype {

    private Product product;
    private Map<String, String> physicalAttributes; // renk, boyut, malzeme vb.
    private double discountPercentage;

    /**
     * Fiziksel ürün prototipi oluşturur.
     * Product nesnesi deep copy ile kopyalanır.
     *
     * @param product Temel ürün (deep copy yapılır)
     */
    public PhysicalProductPrototype(Product product) {
        // Deep copy - orijinal Product nesnesi etkilenmez
        this.product = new Product(product);
        this.physicalAttributes = new HashMap<>();
        this.discountPercentage = 0.0;
        System.out.println("  [PROTOTYPE] PhysicalProductPrototype oluşturuldu: " + product.getName());
    }

    /**
     * Fiziksel özellik ekler (method chaining destekli).
     * Örnek: addAttribute("Renk", "Gümüş").addAttribute("RAM", "16GB")
     *
     * @param key   Özellik anahtarı (örn: "Renk", "Boyut", "Malzeme")
     * @param value Özellik değeri (örn: "Kırmızı", "XL", "Alüminyum")
     * @return this (method chaining için)
     */
    public PhysicalProductPrototype addAttribute(String key, String value) {
        physicalAttributes.put(key, value);
        return this;
    }

    /**
     * İndirim yüzdesini ayarlar.
     *
     * @param percentage İndirim yüzdesi (0-100 arası)
     */
    public void setDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("İndirim yüzdesi 0-100 arasında olmalıdır.");
        }
        this.discountPercentage = percentage;
    }

    /**
     * Bu prototip nesnesinin derin kopyasını oluşturur.
     *
     * Deep copy detayları:
     * - Product: new Product(this.product) ile deep copy
     * - physicalAttributes: new HashMap<>(this.physicalAttributes) ile deep copy
     * - discountPercentage: primitive double, otomatik kopyalanır
     *
     * @return Tamamen bağımsız yeni PhysicalProductPrototype nesnesi
     */
    @Override
    public ProductPrototype clonePrototype() {
        System.out.println("  [PROTOTYPE] Fiziksel ürün klonlandı: " + product.getName());

        // Yeni prototype oluştur - Product deep copy yapılır
        PhysicalProductPrototype clone = new PhysicalProductPrototype(this.product);

        // physicalAttributes deep copy - orijinal map ile bağımsız yeni map
        clone.physicalAttributes = new HashMap<>(this.physicalAttributes);

        // Primitive double otomatik kopyalanır
        clone.discountPercentage = this.discountPercentage;

        return clone;
    }

    /**
     * İçerdiği Product nesnesini döndürür.
     *
     * @return Product nesnesi
     */
    @Override
    public Product getProduct() {
        return product;
    }

    /**
     * Ürün ve fiziksel özellik bilgilerini konsola yazdırır.
     */
    @Override
    public void displayInfo() {
        System.out.println("  ┌─ Fiziksel Ürün Prototipi ─────────────────────");
        System.out.println("  │ Ürün    : " + product.getName() + " (" + product.getId() + ")");
        System.out.println("  │ Marka   : " + product.getBrand());
        System.out.println("  │ Kategori: " + product.getCategory());
        System.out.println("  │ Fiyat   : " + String.format("%.2f", product.getPrice()) + " TL" +
                (discountPercentage > 0 ? " (%" + discountPercentage + " indirimli: " +
                        String.format("%.2f", product.getPrice() * (1 - discountPercentage / 100)) + " TL)" : ""));
        System.out.println("  │ Ağırlık : " + product.getWeight() + " kg");
        System.out.println("  │ Stok    : " + product.getStockQuantity() + " adet");
        if (!physicalAttributes.isEmpty()) {
            System.out.println("  │ Özellikler:");
            for (Map.Entry<String, String> entry : physicalAttributes.entrySet()) {
                System.out.println("  │   " + entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("  └────────────────────────────────────────────────");
    }

    /**
     * Belirtilen özelliği döndürür.
     *
     * @param key Özellik anahtarı
     * @return Özellik değeri veya null
     */
    public String getAttribute(String key) {
        return physicalAttributes.get(key);
    }

    /**
     * Tüm fiziksel özellikleri döndürür.
     *
     * @return Özellikler map'i (değiştirilemez kopya)
     */
    public Map<String, String> getPhysicalAttributes() {
        return new HashMap<>(physicalAttributes);
    }

    /**
     * İndirim yüzdesini döndürür.
     *
     * @return İndirim yüzdesi (0.0 - 100.0)
     */
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * İndirimli fiyatı hesaplar.
     *
     * @return İndirim uygulanmış fiyat
     */
    public double getDiscountedPrice() {
        return product.getPrice() * (1 - discountPercentage / 100);
    }

    @Override
    public String toString() {
        return "PhysicalProductPrototype{" +
                "product=" + product.getName() +
                ", attributes=" + physicalAttributes +
                ", discount=" + discountPercentage + "%" +
                '}';
    }
}
