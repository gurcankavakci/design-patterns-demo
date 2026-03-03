package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

public class ExpressShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Order order) {
        double weight = order.getItems().entrySet().stream()
                .mapToDouble(e -> e.getKey().getWeight() * e.getValue())
                .sum();
        double cost = 45.0 + Math.max(0, weight - 0.5) * 7.0;
        System.out.printf("  [Ekspres Kargo] Agirlik: %.2fkg | Maliyet: %.2f TL%n", weight, cost);
        return cost;
    }

    @Override
    public String getDeliveryTime() {
        return "1-2 is gunu";
    }

    @Override
    public String getStrategyName() {
        return "Ekspres Kargo";
    }

    @Override
    public String getDescription() {
        return "Hizli teslimat. 1-2 is gunu, oncelikli islem.";
    }

    @Override
    public boolean isAvailableFor(Customer customer) {
        return true;
    }
}
