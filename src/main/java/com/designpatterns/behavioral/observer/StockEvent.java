package com.designpatterns.behavioral.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * STOCK EVENT - Stok degisim olayi.
 * Observer pattern'de Subject'in Observer'lara gonderdigi veri.
 */
public class StockEvent {

    private final String productId;
    private final String productName;
    private final int oldStock;
    private final int newStock;
    private final String eventType;
    private final LocalDateTime timestamp;

    public StockEvent(String productId, String productName, int oldStock, int newStock, String eventType) {
        this.productId = productId;
        this.productName = productName;
        this.oldStock = oldStock;
        this.newStock = newStock;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getOldStock() { return oldStock; }
    public int getNewStock() { return newStock; }
    public String getEventType() { return eventType; }
    public LocalDateTime getTimestamp() { return timestamp; }

    public boolean isIncrease() {
        return newStock > oldStock;
    }

    public String getEventDescription() {
        return String.format("%-15s | %-25s | %d -> %d | %s",
                eventType, productName, oldStock, newStock,
                timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
