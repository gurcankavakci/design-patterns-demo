package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;

public class SameDayShipping implements ShippingStrategy {

    @Override
    public double calculateCost(Order order) {
        boolean allDigital = order.getItems().keySet().stream().allMatch(Product::isDigital);
        double cost = allDigital ? 0.0 : 89.0;
        System.out.printf("  [Ayni Gun Teslimat] %s | Maliyet: %.2f TL%n",
                allDigital ? "Dijital urun - kargo yok" : "Fiziksel urun", cost);
        return cost;
    }

    @Override
    public String getDeliveryTime() {
        return "Bugun (saat 22:00'a kadar siparis)";
    }

    @Override
    public String getStrategyName() {
        return "Ayni Gun Teslimat";
    }

    @Override
    public String getDescription() {
        return "Ayni gun kapida! Buyuk sehirlerde gecerli.";
    }

    @Override
    public boolean isAvailableFor(Customer customer) {
        return true;
    }
}
