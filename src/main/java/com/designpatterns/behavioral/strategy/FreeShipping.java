package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

public class FreeShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Order order) {
        System.out.println("  [Ucretsiz Kargo] Premium uye avantaji - kargo bedava!");
        return 0.0;
    }

    @Override
    public String getDeliveryTime() {
        return "5-7 is gunu";
    }

    @Override
    public String getStrategyName() {
        return "Ucretsiz Kargo (Premium)";
    }

    @Override
    public String getDescription() {
        return "Premium uyelere ozel ucretsiz kargo. 5-7 is gunu.";
    }

    @Override
    public boolean isAvailableFor(Customer customer) {
        return customer.isPremium();
    }
}
