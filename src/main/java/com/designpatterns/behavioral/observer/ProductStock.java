package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CONCRETE SUBJECT - Urun Stok Yoneticisi
 */
public class ProductStock implements StockSubject {

    private Map<String, Integer> stockLevels = new HashMap<>();
    private Map<String, String> productNames = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();
    private final int lowStockThreshold = 5;

    @Override
    public void addObserver(StockObserver observer) {
        observers.add(observer);
        System.out.println("  [ProductStock] Gozlemci eklendi: " + observer.getObserverName());
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(StockEvent event) {
        System.out.println("  [ProductStock] " + observers.size() + " gozlemci bildiriliyor...");
        observers.forEach(o -> o.onStockChanged(event));
    }

    public void addProduct(String productId, String productName, int initialStock) {
        stockLevels.put(productId, initialStock);
        productNames.put(productId, productName);
        System.out.println("  [ProductStock] Urun eklendi: " + productName + " - " + initialStock + " adet");
    }

    public void updateStock(String productId, int newQuantity) {
        int oldStock = stockLevels.getOrDefault(productId, 0);
        stockLevels.put(productId, newQuantity);
        String name = productNames.getOrDefault(productId, productId);
        String eventType = determineEventType(oldStock, newQuantity);
        System.out.println("  [ProductStock] Stok guncellendi: " + name + " | " + oldStock + " -> " + newQuantity + " (" + eventType + ")");
        StockEvent event = new StockEvent(productId, name, oldStock, newQuantity, eventType);
        notifyObservers(event);
    }

    public void decreaseStock(String productId, int amount) {
        updateStock(productId, getStock(productId) - amount);
    }

    public void increaseStock(String productId, int amount) {
        updateStock(productId, getStock(productId) + amount);
    }

    public int getStock(String productId) {
        return stockLevels.getOrDefault(productId, 0);
    }

    private String determineEventType(int old, int newQty) {
        if (newQty == 0) return "OUT_OF_STOCK";
        if (old == 0 && newQty > 0) return "RESTOCKED";
        if (newQty <= lowStockThreshold) return "LOW_STOCK";
        return "IN_STOCK";
    }
}
