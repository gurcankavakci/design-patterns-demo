package com.designpatterns.creational.prototype;

import com.designpatterns.model.Product;

/**
 * Dijital ürün prototipi.
 * Prototype Pattern'de "ConcretePrototype" rolünü oynar.
 *
 * Dijital ürünler indirme URL'si, lisans tipi ve maksimum indirme sayısı içerir.
 * Deep copy ile tamamen bağımsız klonlar oluşturur.
 *
 * KULLANIM SENARYOSU:
 * E-kitap şablonu oluştur → farklı lisans tipleri için klonla:
 *   - Klon 1: SINGLE_USER lisansı (bireysel)
 *   - Klon 2: MULTI_USER lisansı (kurumsal, 5 kullanıcı)
 *   - Klon 3: ENTERPRISE lisansı (sınırsız kullanıcı)
 */
public class DigitalProductPrototype implements ProductPrototype {

    private Product product;
    private String downloadUrl;
    private String licenseType; // SINGLE_USER, MULTI_USER, ENTERPRISE
    private int maxDownloads;

    /**
     * Dijital ürün prototipi oluşturur.
     *
     * @param product      Temel dijital ürün (deep copy yapılır)
     * @param downloadUrl  İndirme URL'si
     * @param licenseType  Lisans tipi (SINGLE_USER, MULTI_USER, ENTERPRISE)
     * @param maxDownloads Maksimum indirme sayısı
     */
    public DigitalProductPrototype(Product product, String downloadUrl, String licenseType, int maxDownloads) {
        // Deep copy - orijinal Product nesnesi etkilenmez
        this.product = new Product(product);
        this.downloadUrl = downloadUrl;
        this.licenseType = licenseType;
        this.maxDownloads = maxDownloads;
        System.out.println("  [PROTOTYPE] DigitalProductPrototype oluşturuldu: " + product.getName());
    }

    /**
     * Bu prototip nesnesinin derin kopyasını oluşturur.
     *
     * Deep copy detayları:
     * - Product: new Product(this.product) ile deep copy
     * - String'ler immutable olduğundan referans kopyası güvenlidir
     * - int maxDownloads: primitive, otomatik kopyalanır
     *
     * @return Tamamen bağımsız yeni DigitalProductPrototype nesnesi
     */
    @Override
    public ProductPrototype clonePrototype() {
        System.out.println("  [PROTOTYPE] Dijital ürün klonlandı: " + product.getName());

        // String'ler Java'da immutable olduğundan referans kopyası güvenlidir
        // (String paylaşımı deep copy ile aynı etkiyi sağlar)
        return new DigitalProductPrototype(
                this.product,       // Product deep copy yapılır (kopyalanan constructor'da)
                this.downloadUrl,   // String immutable - güvenli
                this.licenseType,   // String immutable - güvenli
                this.maxDownloads   // primitive int - otomatik kopyalanır
        );
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
     * Dijital ürün bilgilerini konsola yazdırır.
     * URL ve lisans bilgilerini de gösterir.
     */
    @Override
    public void displayInfo() {
        System.out.println("  ┌─ Dijital Ürün Prototipi ──────────────────────");
        System.out.println("  │ Ürün      : " + product.getName() + " (" + product.getId() + ")");
        System.out.println("  │ Marka     : " + product.getBrand());
        System.out.println("  │ Kategori  : " + product.getCategory());
        System.out.println("  │ Fiyat     : " + String.format("%.2f", product.getPrice()) + " TL");
        System.out.println("  │ Dijital   : Evet (indirilebilir)");
        System.out.println("  │ İndirme   : " + downloadUrl);
        System.out.println("  │ Lisans    : " + licenseType + " (" + getLicenseDescription() + ")");
        System.out.println("  │ Max İndirme: " + maxDownloads + " kez");
        System.out.println("  └────────────────────────────────────────────────");
    }

    /**
     * Lisans tipine göre açıklama döndürür.
     *
     * @return Lisans açıklaması
     */
    private String getLicenseDescription() {
        switch (licenseType) {
            case "SINGLE_USER":
                return "Tek kullanıcı lisansı";
            case "MULTI_USER":
                return "Çoklu kullanıcı lisansı (5 cihaz)";
            case "ENTERPRISE":
                return "Kurumsal lisans (sınırsız kullanıcı)";
            default:
                return "Standart lisans";
        }
    }

    /**
     * İndirme URL'sini döndürür.
     *
     * @return İndirme URL'si
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * İndirme URL'sini ayarlar.
     *
     * @param downloadUrl Yeni indirme URL'si
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    /**
     * Lisans tipini döndürür.
     *
     * @return Lisans tipi (SINGLE_USER, MULTI_USER, ENTERPRISE)
     */
    public String getLicenseType() {
        return licenseType;
    }

    /**
     * Lisans tipini ayarlar.
     *
     * @param licenseType Yeni lisans tipi
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * Maksimum indirme sayısını döndürür.
     *
     * @return Maksimum indirme hakkı
     */
    public int getMaxDownloads() {
        return maxDownloads;
    }

    /**
     * Maksimum indirme sayısını ayarlar.
     *
     * @param maxDownloads Yeni maksimum indirme sayısı
     */
    public void setMaxDownloads(int maxDownloads) {
        this.maxDownloads = maxDownloads;
    }

    @Override
    public String toString() {
        return "DigitalProductPrototype{" +
                "product=" + product.getName() +
                ", licenseType='" + licenseType + '\'' +
                ", maxDownloads=" + maxDownloads +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
