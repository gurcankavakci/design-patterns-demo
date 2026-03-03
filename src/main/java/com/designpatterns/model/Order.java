package com.designpatterns.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * ShopEase platformunun sipariş modeli.
 * State ve Command pattern'larında merkezi olarak kullanılır.
 */
public class Order {

    private String id;
    private Customer customer;
    private Map<Product, Integer> items; // Ürün -> Adet
    private double totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private LocalDateTime createdAt;
    private String status; // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    private String shippingType;
    private double shippingCost;

    public Order() {
        this.items = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    public Order(String id, Customer customer) {
        this();
        this.id = id;
        this.customer = customer;
        this.shippingAddress = customer.getAddress();
    }

    public void addItem(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.totalAmount = items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
        this.totalAmount += shippingCost;
    }

    // Getters
    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getShippingAddress() { return shippingAddress; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getStatus() { return status; }
    public String getShippingType() { return shippingType; }
    public double getShippingCost() { return shippingCost; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setItems(Map<Product, Integer> items) { this.items = items; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setStatus(String status) { this.status = status; }
    public void setShippingType(String shippingType) { this.shippingType = shippingType; }
    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
        recalculateTotal();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return String.format("Order{id='%s', musteri='%s', tutar=%.2f TL, durum='%s', tarih='%s'}",
                id, customer != null ? customer.getName() : "N/A",
                totalAmount, status,
                createdAt != null ? createdAt.format(formatter) : "N/A");
    }
}
