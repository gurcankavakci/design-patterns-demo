package com.designpatterns.behavioral.observer;

public class AutoReorderObserver implements StockObserver {

    private final int reorderThreshold = 5;
    private final int reorderQuantity = 100;

    @Override
    public void onStockChanged(StockEvent event) {
        if ("OUT_OF_STOCK".equals(event.getEventType()) || "LOW_STOCK".equals(event.getEventType())) {
            System.out.println("  [OtomatikSiparis] Dusuk stok: " + event.getProductName() + " (" + event.getNewStock() + " kalan)");
            System.out.println("    -> Tedarikciye otomatik siparis: " + reorderQuantity + " adet");
            System.out.println("    -> Tahmini teslimat: 3-5 is gunu");
        }
    }

    @Override
    public String getObserverName() {
        return "Otomatik Siparis Sistemi (esik: " + reorderThreshold + ")";
    }
}
