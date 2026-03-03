package com.designpatterns.behavioral.chain;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SİPARİŞ İSTEĞİ - Order Request
 *
 * Chain of Responsibility pattern'ında zincir boyunca taşınan veri nesnesi.
 * Her validator bu nesneyi inceler, gerektiğinde mesaj ekler ve onay durumunu günceller.
 *
 * <p>Bu nesne bir "context" nesnesi görevi görür: doğrulama sürecinde
 * toplanan tüm bilgileri (mesajlar, onay durumu) bir arada tutar.</p>
 */
public class OrderRequest {

    private Order order;
    private Customer customer;
    private String paymentMethod;
    private double amount;
    private Map<String, Integer> productIds; // productId -> quantity
    private boolean approved = true;
    private List<String> validationMessages = new ArrayList<>();

    /**
     * OrderRequest constructor'ı.
     *
     * @param order         Doğrulanacak sipariş
     * @param customer      Siparişi veren müşteri
     * @param paymentMethod Ödeme yöntemi
     * @param amount        Sipariş tutarı
     */
    public OrderRequest(Order order, Customer customer, String paymentMethod, double amount) {
        this.order = order;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.productIds = new HashMap<>();
    }

    /**
     * Doğrulama mesajı ekler.
     *
     * @param msg Eklenecek mesaj
     */
    public void addMessage(String msg) {
        validationMessages.add(msg);
    }

    // Getters
    public Order getOrder() { return order; }
    public Customer getCustomer() { return customer; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmount() { return amount; }
    public Map<String, Integer> getProductIds() { return productIds; }
    public boolean isApproved() { return approved; }
    public List<String> getValidationMessages() { return validationMessages; }

    // Setters
    public void setApproved(boolean approved) { this.approved = approved; }
    public void setProductIds(Map<String, Integer> productIds) { this.productIds = productIds; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OrderRequest{\n");
        sb.append("  Musteri: ").append(customer.getName()).append("\n");
        sb.append("  Tutar: ").append(amount).append(" TL\n");
        sb.append("  Odeme: ").append(paymentMethod).append("\n");
        sb.append("  Onay Durumu: ").append(approved ? "ONAYLANDI" : "REDDEDILDI").append("\n");
        if (!validationMessages.isEmpty()) {
            sb.append("  Mesajlar:\n");
            validationMessages.forEach(m -> sb.append("    - ").append(m).append("\n"));
        }
        sb.append("}");
        return sb.toString();
    }
}
