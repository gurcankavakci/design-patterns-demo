package com.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CART COLLEAGUE - Sepet Bileşeni
 *
 * <p>Sepete ürün ekleme/çıkarma ve ödeme başlatma olaylarını
 * mediator aracılığıyla diğer bileşenlere bildirir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class CartColleague extends ShoppingColleague {

    /** Sepetteki ürün ID'leri */
    private final List<String> cartItems = new ArrayList<>();

    public CartColleague() {
        super("Sepet Bileşeni");
    }

    /**
     * Sepete ürün ekler ve PRODUCT_ADDED olayını yayar.
     *
     * @param productId   Ürün kimliği
     * @param productName Ürün adı
     * @param quantity    Eklenecek miktar
     */
    public void addToCart(String productId, String productName, int quantity) {
        cartItems.add(productId);
        System.out.println("  [Sepet] Ürün eklendi: " + productName + " x" + quantity);

        Map<String, Object> data = new HashMap<>();
        data.put("productId", productId);
        data.put("productName", productName);
        data.put("quantity", quantity);
        mediator.notify(this, "PRODUCT_ADDED", data);
    }

    /**
     * Sepetten ürün çıkarır ve PRODUCT_REMOVED olayını yayar.
     *
     * @param productId   Ürün kimliği
     * @param productName Ürün adı
     */
    public void removeFromCart(String productId, String productName) {
        cartItems.remove(productId);
        System.out.println("  [Sepet] Ürün çıkarıldı: " + productName);

        Map<String, Object> data = new HashMap<>();
        data.put("productId", productId);
        data.put("productName", productName);
        mediator.notify(this, "PRODUCT_REMOVED", data);
    }

    /**
     * Ödeme sürecini başlatır ve CHECKOUT_INITIATED olayını yayar.
     *
     * @param customerId Müşteri kimliği
     */
    public void checkout(String customerId) {
        System.out.println("  [Sepet] Ödeme başlatılıyor... (" + cartItems.size() + " ürün)");

        Map<String, Object> data = new HashMap<>();
        data.put("customerId", customerId);
        data.put("itemCount", cartItems.size());
        mediator.notify(this, "CHECKOUT_INITIATED", data);
    }

    @Override
    public void receiveEvent(String event, Object data) {
        System.out.println("  [Sepet] Olay alındı: " + event + " | " + data);
    }
}
