package com.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class AdminStockObserver implements StockObserver {

    private String adminEmail;
    private List<StockEvent> eventLog = new ArrayList<>();

    public AdminStockObserver(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    @Override
    public void onStockChanged(StockEvent event) {
        eventLog.add(event);
        System.out.println("  [AdminGozlemci] Kayit: " + event.getEventDescription());
        if ("OUT_OF_STOCK".equals(event.getEventType()) || "LOW_STOCK".equals(event.getEventType())) {
            System.out.println("    -> KRITIK UYARI: Admin paneline bildirim gonderildi -> " + adminEmail);
        }
    }

    @Override
    public String getObserverName() {
        return "Admin: " + adminEmail;
    }

    public void printEventLog() {
        System.out.println("  === Admin Event Log ===");
        eventLog.forEach(e -> System.out.println("  - " + e.getEventDescription()));
    }
}
