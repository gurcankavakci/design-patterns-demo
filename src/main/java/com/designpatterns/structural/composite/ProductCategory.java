package com.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * PRODUCT CATEGORY - Ürün Kategorisi (Composite)
 *
 * <p>Composite Pattern'da Composite rolünü üstlenir. Hem {@link ProductLeaf}
 * hem de başka {@link ProductCategory} nesneleri içerebilir.
 * İşlemleri tüm alt bileşenlerine (children) delege eder.
 *
 * <p><b>COMPOSITE'İN SORUMLULUKLARI:</b>
 * <ul>
 *   <li>Alt bileşenleri (children) yönetir (add/remove)</li>
 *   <li>getTotalPrice() - tüm çocukların fiyat toplamını döner</li>
 *   <li>getItemCount() - tüm çocukların ürün sayısı toplamını döner</li>
 *   <li>display() - kendini ve tüm çocukları özyineli gösterir</li>
 * </ul>
 *
 * <p><b>ÖRNEK KULLANIM:</b>
 * <pre>
 * ProductCategory elektronik = new ProductCategory("Elektronik", "Tüm elektronik ürünler");
 * ProductCategory bilgisayar = new ProductCategory("Bilgisayar", "PC ve Laptop");
 * ProductLeaf laptop = new ProductLeaf(laptopProduct);
 *
 * bilgisayar.add(laptop);
 * elektronik.add(bilgisayar);
 * elektronik.displayTree(); // Tüm ağacı gösterir
 * </pre>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ProductCategory implements CategoryComponent {

    /** Kategori adı */
    private final String name;

    /** Kategori açıklaması */
    private final String description;

    /** Alt bileşenler listesi (Leaf veya başka Composite olabilir) */
    private final List<CategoryComponent> children = new ArrayList<>();

    /**
     * Boş bir kategori oluşturur.
     *
     * @param name        Kategori adı
     * @param description Kategori açıklaması
     */
    public ProductCategory(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori adi bos olamaz!");
        }
        this.name = name;
        this.description = description;
    }

    /**
     * Kategoriye yeni bir bileşen ekler (Leaf veya başka Category).
     *
     * @param component Eklenecek bileşen
     * @throws IllegalArgumentException component null ise
     */
    public void add(CategoryComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("Eklenecek component null olamaz!");
        }
        children.add(component);
        System.out.printf("[Kategori] '%s' eklendi -> '%s' (Tip: %s)%n",
                          component.getName(), this.name, component.getType());
    }

    /**
     * Kategoriden bir bileşeni kaldırır.
     *
     * @param component Kaldırılacak bileşen
     * @return true kaldırma başarılı ise
     */
    public boolean remove(CategoryComponent component) {
        boolean removed = children.remove(component);
        if (removed) {
            System.out.printf("[Kategori] '%s' kaldırıldı <- '%s'%n",
                              component.getName(), this.name);
        }
        return removed;
    }

    /**
     * Alt bileşenler listesini döner.
     *
     * @return Değiştirilemez children listesi
     */
    public List<CategoryComponent> getChildren() {
        return new ArrayList<>(children); // Defensive copy
    }

    /**
     * Kategoriyi ve tüm alt bileşenlerini özyineli gösterir.
     *
     * @param depth Ağaç derinliği
     */
    @Override
    public void display(int depth) {
        String indent = "    ".repeat(depth);
        System.out.printf("%s[%s] %s (%d urun, toplam: %.2f TL)%n",
                          indent,
                          name,
                          description.length() > 30 ? description.substring(0, 30) + "..." : description,
                          getItemCount(),
                          getTotalPrice());

        // Tüm çocuklara özyineli olarak display() çağır
        for (CategoryComponent child : children) {
            child.display(depth + 1);
        }
    }

    /**
     * Tüm alt bileşenlerin toplam fiyatını döner.
     * Stream API ile çocukların fiyatları toplanır.
     *
     * @return Toplam fiyat (TL)
     */
    @Override
    public double getTotalPrice() {
        return children.stream()
                .mapToDouble(CategoryComponent::getTotalPrice)
                .sum();
    }

    /**
     * Kategori adını döner.
     *
     * @return Kategori adı
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Tüm alt bileşenlerdeki toplam ürün sayısını döner.
     *
     * @return Toplam ürün adedi
     */
    @Override
    public int getItemCount() {
        return children.stream()
                .mapToInt(CategoryComponent::getItemCount)
                .sum();
    }

    /**
     * Composite tipini döner.
     *
     * @return "COMPOSITE"
     */
    @Override
    public String getType() {
        return "COMPOSITE";
    }

    /**
     * Tüm kategori ağacını kökten başlayarak görüntüler.
     * Bu metod sadece kök kategorilerde çağrılmalıdır.
     */
    public void displayTree() {
        System.out.println();
        System.out.println("=== Kategori Agaci ===");
        System.out.printf("Toplam: %d urun | %.2f TL%n", getItemCount(), getTotalPrice());
        System.out.println("─".repeat(50));
        display(0);
        System.out.println("─".repeat(50));
    }

    public String getDescription() {
        return description;
    }
}
