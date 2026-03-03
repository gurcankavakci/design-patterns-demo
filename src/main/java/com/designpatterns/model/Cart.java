package com.designpatterns.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ShopEase platformunun alışveriş sepeti modeli.
 * Memento, Command ve Observer pattern'larında kullanılır.
 */
public class Cart {

    private String customerId;
    private Map<String, Integer> itemQuantities; // productId -> quantity
    private List<Product> products;

    public Cart(String customerId) {
        this.customerId = customerId;
        this.itemQuantities = new LinkedHashMap<>();
        this.products = new ArrayList<>();
    }

    // Sepete ürün ekle
    public void addProduct(Product product, int quantity) {
        if (itemQuantities.containsKey(product.getId())) {
            itemQuantities.put(product.getId(), itemQuantities.get(product.getId()) + quantity);
        } else {
            itemQuantities.put(product.getId(), quantity);
            products.add(product);
        }
    }

    // Sepetten ürün çıkar
    public boolean removeProduct(String productId) {
        if (itemQuantities.containsKey(productId)) {
            itemQuantities.remove(productId);
            products.removeIf(p -> p.getId().equals(productId));
            return true;
        }
        return false;
    }

    // Toplam tutarı hesapla
    public double getTotalAmount() {
        return products.stream()
                .mapToDouble(p -> p.getPrice() * itemQuantities.getOrDefault(p.getId(), 0))
                .sum();
    }

    // Sepeti temizle
    public void clear() {
        itemQuantities.clear();
        products.clear();
    }

    public String getCustomerId() { return customerId; }
    public Map<String, Integer> getItemQuantities() { return itemQuantities; }
    public List<Product> getProducts() { return products; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Sepet (Musteri: %s):%n", customerId));
        products.forEach(p -> {
            int qty = itemQuantities.get(p.getId());
            sb.append(String.format("  - %s x%d = %.2f TL%n", p.getName(), qty, p.getPrice() * qty));
        });
        sb.append(String.format("  Toplam: %.2f TL", getTotalAmount()));
        return sb.toString();
    }
}
