package com.designpatterns.creational.prototype;

import com.designpatterns.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prototype Registry - Prototip Kaydı
 *
 * Registry, önceden oluşturulmuş prototipleri saklar.
 * İstemci kod, doğrudan new kullanmak yerine registry'den klonlanmış
 * nesneler alır. Bu şekilde nesne oluşturma detayları gizlenir.
 *
 * REGISTRY'NİN ROLÜ:
 * 1. Prototipleri anahtar-değer olarak saklar
 * 2. İstemcinin string anahtar ile prototip almasını sağlar
 * 3. Klonlama işlemini istemciden gizler
 *
 * KULLANIM:
 * // Registry kullanmadan (nesne tipini bilmek gerekiyor):
 * PhysicalProductPrototype laptop = new PhysicalProductPrototype(...);
 * PhysicalProductPrototype klonLaptop = (PhysicalProductPrototype) laptop.clonePrototype();
 *
 * // Registry ile (tip bilgisi gizli, sadece anahtar yeterli):
 * ProductRegistry registry = new ProductRegistry();
 * ProductPrototype klonLaptop = registry.cloneProduct("LAPTOP_TEMPLATE");
 *
 * SINGLETON İLE KOMBINASYON:
 * Registry genellikle Singleton ile birlikte kullanılır.
 * Bu örnekte demo amaçlı normal class olarak tasarlanmıştır.
 *
 * ---
 *
 * Prototype Registry - Prototype Registry
 *
 * The Registry stores pre-built prototypes.
 * Client code receives cloned objects from the registry instead of
 * using new directly. This hides object creation details.
 */
public class ProductRegistry {

    private Map<String, ProductPrototype> prototypes = new HashMap<>();

    /**
     * ProductRegistry oluşturur ve varsayılan şablonları kayıt eder.
     *
     * Önceden hazırlanmış şablonlar:
     * - LAPTOP_TEMPLATE: Fiziksel laptop şablonu (16GB RAM, 512GB SSD)
     * - EBOOK_TEMPLATE: Dijital e-kitap şablonu (tek kullanıcı lisansı)
     * - TSHIRT_TEMPLATE: Fiziksel t-shirt şablonu
     */
    public ProductRegistry() {
        System.out.println("  [REGISTRY] Prototip kaydı başlatılıyor...");
        initializeDefaultPrototypes();
        System.out.println("  [REGISTRY] " + prototypes.size() + " prototip kayıt edildi.");
        System.out.println();
    }

    /**
     * Varsayılan prototipleri oluşturur ve kayıt eder.
     */
    private void initializeDefaultPrototypes() {

        // --- LAPTOP ŞABLONU ---
        Product laptopBase = new Product(
                "TPL-001", "Laptop Şablonu", "Generic", "Elektronik",
                15000.0, 100, 2.5, false
        );
        PhysicalProductPrototype laptopTemplate = new PhysicalProductPrototype(laptopBase);
        laptopTemplate.addAttribute("Renk", "Gri")
                      .addAttribute("RAM", "16GB")
                      .addAttribute("Depolama", "512GB SSD")
                      .addAttribute("Ekran", "15.6 inç FHD")
                      .addAttribute("İşlemci", "Intel Core i7");
        registerPrototype("LAPTOP_TEMPLATE", laptopTemplate);

        // --- E-KİTAP ŞABLONU ---
        Product ebookBase = new Product(
                "TPL-002", "E-Kitap Şablonu", "Generic", "Kitap",
                50.0, 999, 0.0, true
        );
        DigitalProductPrototype ebookTemplate = new DigitalProductPrototype(
                ebookBase,
                "https://cdn.shopease.com/ebooks/",
                "SINGLE_USER",
                3
        );
        registerPrototype("EBOOK_TEMPLATE", ebookTemplate);

        // --- T-SHIRT ŞABLONU ---
        Product tshirtBase = new Product(
                "TPL-003", "T-Shirt Şablonu", "Generic", "Giyim",
                200.0, 500, 0.3, false
        );
        PhysicalProductPrototype tshirtTemplate = new PhysicalProductPrototype(tshirtBase);
        tshirtTemplate.addAttribute("Malzeme", "%100 Pamuk")
                      .addAttribute("Yaka", "Bisiklet Yaka")
                      .addAttribute("Kesim", "Regular Fit");
        registerPrototype("TSHIRT_TEMPLATE", tshirtTemplate);
    }

    /**
     * Yeni bir prototipi registry'ye kayıt eder.
     * Aynı anahtar tekrar kayıt edilirse önceki değerin üzerine yazılır.
     *
     * @param key       Prototip anahtarı (benzersiz tanımlayıcı)
     * @param prototype Kayıt edilecek prototype nesnesi
     */
    public void registerPrototype(String key, ProductPrototype prototype) {
        prototypes.put(key, prototype);
        System.out.println("  [REGISTRY] Prototip kayıt edildi: " + key +
                " → " + prototype.getProduct().getName());
    }

    /**
     * Belirtilen anahtara karşılık gelen prototipi döndürür (klonlamadan).
     * Klonlanmış kopya almak için cloneProduct() kullanın.
     *
     * @param key Prototip anahtarı
     * @return Orijinal ProductPrototype nesnesi
     * @throws IllegalArgumentException Anahtar bulunamazsa
     */
    public ProductPrototype getPrototype(String key) {
        ProductPrototype prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException(
                    "Prototip bulunamadı: '" + key + "'. " +
                    "Mevcut şablonlar: " + prototypes.keySet());
        }
        return prototype;
    }

    /**
     * Belirtilen anahtara karşılık gelen prototipten klonlanmış yeni bir nesne döndürür.
     * Her çağrıda bağımsız bir kopya oluşturulur.
     *
     * @param key Prototip anahtarı
     * @return Klonlanmış yeni ProductPrototype nesnesi
     * @throws IllegalArgumentException Anahtar bulunamazsa
     */
    public ProductPrototype cloneProduct(String key) {
        ProductPrototype prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException(
                    "Klonlanacak prototip bulunamadı: '" + key + "'. " +
                    "Mevcut şablonlar: " + prototypes.keySet());
        }
        System.out.println("  [REGISTRY] Prototip klonlanıyor: " + key);
        return prototype.clonePrototype();
    }

    /**
     * Kayıt edilmiş tüm prototip anahtarlarını döndürür.
     *
     * @return Prototip anahtar listesi
     */
    public List<String> getAvailableTemplates() {
        return new ArrayList<>(prototypes.keySet());
    }

    /**
     * Kayıt edilmiş tüm prototiplerin bilgilerini konsola yazdırır.
     */
    public void displayAllPrototypes() {
        System.out.println("  ╔══════════════════════════════════════════════╗");
        System.out.println("  ║       Kayıtlı Prototip Şablonları            ║");
        System.out.println("  ╚══════════════════════════════════════════════╝");
        System.out.println("  Toplam: " + prototypes.size() + " şablon");
        System.out.println();
        for (Map.Entry<String, ProductPrototype> entry : prototypes.entrySet()) {
            System.out.println("  Anahtar: [" + entry.getKey() + "]");
            entry.getValue().displayInfo();
            System.out.println();
        }
    }

    /**
     * Registry'deki prototip sayısını döndürür.
     *
     * @return Kayıtlı prototip sayısı
     */
    public int getPrototypeCount() {
        return prototypes.size();
    }

    /**
     * Belirtilen anahtarın registry'de olup olmadığını kontrol eder.
     *
     * @param key Kontrol edilecek anahtar
     * @return Anahtar mevcutsa true
     */
    public boolean hasPrototype(String key) {
        return prototypes.containsKey(key);
    }

    /**
     * Belirtilen anahtardaki prototipi registry'den kaldırır.
     *
     * @param key Kaldırılacak prototip anahtarı
     */
    public void removePrototype(String key) {
        if (prototypes.remove(key) != null) {
            System.out.println("  [REGISTRY] Prototip kaldırıldı: " + key);
        } else {
            System.out.println("  [REGISTRY] Prototip bulunamadı, kaldırılamadı: " + key);
        }
    }
}
