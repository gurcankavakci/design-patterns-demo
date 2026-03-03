package com.designpatterns.behavioral.observer;

import java.util.HashSet;
import java.util.Set;

public class CustomerStockObserver implements StockObserver {

    private String customerName;
    private String email;
    private Set<String> watchedProductIds = new HashSet<>();

    public CustomerStockObserver(String customerName, String email) {
        this.customerName = customerName;
        this.email = email;
    }

    public void watchProduct(String productId) {
        watchedProductIds.add(productId);
        System.out.println("  [Musteri " + customerName + "] " + productId + " izlemeye alindi");
    }

    @Override
    public void onStockChanged(StockEvent event) {
        if (!watchedProductIds.contains(event.getProductId())) return;

        System.out.println("  [MusteriGozlemci - " + customerName + "] Bildirim: " + event.getEventDescription());
        switch (event.getEventType()) {
            case "RESTOCKED" ->
                System.out.println("    -> E-posta: '" + event.getProductName() + "' yeniden stoga girdi! Hemen al!");
            case "OUT_OF_STOCK" ->
                System.out.println("    -> E-posta: '" + event.getProductName() + "' stokta kalmadi.");
            case "LOW_STOCK" ->
                System.out.println("    -> SMS: '" + event.getProductName() + "' son " + event.getNewStock() + " adet!");
            default ->
                System.out.println("    -> Bildirim: " + event.getEventDescription());
        }
    }

    @Override
    public String getObserverName() {
        return "Musteri: " + customerName + " <" + email + ">";
    }
}
