package com.designpatterns.behavioral.memento;

import com.designpatterns.model.Product;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ORIGINATOR - Sepet (durum yaratıcı)
 *
 * <p>Durumu {@link CartMemento} olarak kaydeder ve Memento'dan geri yükler.
 * Caretaker ({@link CartHistory}) sadece Memento nesnelerini saklar; içlerine bakmaz.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class MementoCart {

    /** Sepetteki ürünler: productId -> miktar */
    private Map<String, Integer> items = new LinkedHashMap<>();

    /** Fiyat hesabı için ürün referansları: productId -> Product */
    private final Map<String, Product> productMap = new HashMap<>();

    /**
     * Sepete ürün ekler. Ürün zaten varsa miktarını artırır.
     *
     * @param product  Eklenecek ürün
     * @param quantity Eklenecek miktar
     */
    public void addItem(Product product, int quantity) {
        items.merge(product.getId(), quantity, Integer::sum);
        productMap.put(product.getId(), product);
        System.out.println("  [MementoCart] Eklendi: " + product.getName() + " x" + quantity);
    }

    /**
     * Sepetten ürün çıkarır.
     *
     * @param productId Çıkarılacak ürün kimliği
     */
    public void removeItem(String productId) {
        if (items.remove(productId) != null) {
            String name = productMap.containsKey(productId)
                    ? productMap.get(productId).getName()
                    : productId;
            System.out.println("  [MementoCart] Çıkarıldı: " + name);
        }
    }

    /**
     * Sepetteki ürünlerin toplam tutarını hesaplar.
     *
     * @return Toplam tutar (TL)
     */
    public double getTotalAmount() {
        return items.entrySet().stream().mapToDouble(e -> {
            Product p = productMap.get(e.getKey());
            return p != null ? p.getPrice() * e.getValue() : 0;
        }).sum();
    }

    /**
     * Mevcut sepet durumunun Memento snapshot'ını oluşturur.
     *
     * @param description Snapshot açıklaması (ör: "Laptop eklendi")
     * @return Anlık durum Memento nesnesi
     */
    public CartMemento save(String description) {
        System.out.println("  [MementoCart] Durum kaydediliyor: '" + description + "'");
        return new CartMemento(items, getTotalAmount(), description);
    }

    /**
     * Sepeti belirtilen Memento'nun durumuna geri yükler.
     *
     * @param memento Geri yüklenecek durum
     */
    public void restore(CartMemento memento) {
        System.out.println("  [MementoCart] Durum geri yükleniyor: " + memento.getDescription());
        items = new LinkedHashMap<>(memento.getItems());
        System.out.println("  [MementoCart] Geri yükleme sonrası toplam: " + getTotalAmount() + " TL");
    }

    /**
     * Sepet içeriğini konsola yazdırır.
     */
    public void displayCart() {
        System.out.println("  --- Sepet İçeriği ---");
        if (items.isEmpty()) {
            System.out.println("  Sepet boş.");
        }
        items.forEach((id, qty) -> {
            Product p = productMap.get(id);
            if (p != null) {
                System.out.printf("  - %-30s x%d = %.2f TL%n", p.getName(), qty, p.getPrice() * qty);
            }
        });
        System.out.printf("  Toplam: %.2f TL%n", getTotalAmount());
    }
}
