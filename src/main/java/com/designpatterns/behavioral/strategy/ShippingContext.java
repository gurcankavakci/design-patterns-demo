package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

import java.util.List;

/**
 * CONTEXT - Kargo baglami. Stratejiyi kullanir.
 */
public class ShippingContext {

    private ShippingStrategy strategy;

    public ShippingContext(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
        System.out.println("  [ShippingContext] Strateji degistirildi: " + strategy.getStrategyName());
    }

    public double calculateShippingCost(Order order) {
        return strategy.calculateCost(order);
    }

    public String getDeliveryInfo() {
        return strategy.getDeliveryTime();
    }

    public ShippingStrategy getStrategy() {
        return strategy;
    }

    public void displayShippingOptions(Order order, Customer customer) {
        System.out.println("  === Kargo Secenekleri ===");
        List<ShippingStrategy> options = List.of(
                new StandardShipping(),
                new ExpressShipping(),
                new SameDayShipping(),
                new FreeShipping()
        );
        for (ShippingStrategy opt : options) {
            if (opt.isAvailableFor(customer)) {
                System.out.printf("  %-25s | %-20s | %.2f TL%n",
                        opt.getStrategyName(), opt.getDeliveryTime(), opt.calculateCost(order));
            }
        }
    }

    public ShippingStrategy recommendStrategy(Customer customer, Order order) {
        if (customer.isPremium()) return new FreeShipping();
        if (order.getTotalAmount() > 500) return new FreeShipping();
        return new StandardShipping();
    }
}
