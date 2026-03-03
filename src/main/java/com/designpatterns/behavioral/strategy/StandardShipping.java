package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

public class StandardShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Order order) {
        double weight = order.getItems().entrySet().stream()
                .mapToDouble(e -> e.getKey().getWeight() * e.getValue())
                .sum();
        double cost = 15.0 + Math.max(0, weight - 1) * 3.0;
        System.out.printf("  [Standard Kargo] Agirlik: %.2fkg | Maliyet: %.2f TL%n", weight, cost);
        return cost;
    }

    @Override
    public String getDeliveryTime() {
        return "3-5 is gunu";
    }

    @Override
    public String getStrategyName() {
        return "Standard Kargo";
    }

    @Override
    public String getDescription() {
        return "Ekonomik seccenek. Tum sehirlere 3-5 is gunu teslimat.";
    }

    @Override
    public boolean isAvailableFor(Customer customer) {
        return true;
    }
}
