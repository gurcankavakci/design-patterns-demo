package com.designpatterns.behavioral.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * STOCK COLLEAGUE - Stok Bileşeni
 *
 * <p>Stok durumunu takip eder. PRODUCT_ADDED olayında stoğu düşürür;
 * stok kritik seviyeye düştüğünde LOW_STOCK_ALERT olayını yayar.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class StockColleague extends ShoppingColleague {

    /** Ürün stok tablosu: productId -> stok adedi */
    private final Map<String, Integer> stock = new HashMap<>();

    public StockColleague() {
        super("Stok Bileşeni");
        // Başlangıç stokları
        stock.put("LAPTOP-001",  10);
        stock.put("PHONE-001",    3);
        stock.put("TSHIRT-001",  50);
    }

    /**
     * Belirtilen ürünün istenen miktarda stokta olup olmadığını kontrol eder.
     *
     * @param productId Ürün kimliği
     * @param qty       İstenen miktar
     * @return true yeterli stok varsa
     */
    public boolean checkStock(String productId, int qty) {
        return stock.getOrDefault(productId, 0) >= qty;
    }

    /**
     * Stok miktarını günceller. Kritik seviyeye (<=3) düşerse LOW_STOCK_ALERT yayar.
     *
     * @param productId Ürün kimliği
     * @param change    Değişim miktarı (negatif = azaltma, pozitif = artırma)
     */
    public void updateStock(String productId, int change) {
        int newQty = stock.merge(productId, change, Integer::sum);
        System.out.println("  [Stok] Güncellendi: " + productId + " -> " + newQty + " adet");
        if (newQty <= 3) {
            Map<String, Object> data = new HashMap<>();
            data.put("productId", productId);
            data.put("remaining", newQty);
            mediator.notify(this, "LOW_STOCK_ALERT", data);
        }
    }

    @Override
    public void receiveEvent(String event, Object data) {
        System.out.println("  [Stok] Olay alındı: " + event);
        if ("PRODUCT_ADDED".equals(event) && data instanceof Map<?, ?> m) {
            String pid = (String) m.get("productId");
            Object qtyObj = m.get("quantity");
            int qty = qtyObj instanceof Integer ? (Integer) qtyObj : 1;
            System.out.println("  [Stok] Rezervasyon: " + pid + " x" + qty);
            updateStock(pid, -qty);
        }
        if ("CHECKOUT_INITIATED".equals(event)) {
            System.out.println("  [Stok] Checkout için stok doğrulaması yapılıyor...");
        }
    }
}
