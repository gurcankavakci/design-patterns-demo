package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * PRICE RANGE EXPRESSION - Fiyat Araligi Filtresi (Terminal Expression)
 *
 * Interpreter Pattern'in Terminal (yaprak) dugumu.
 * Urunleri belirtilen fiyat araligi icinde filtreler.
 */
public class PriceRangeExpression implements SearchExpression {

    private final double minPrice;
    private final double maxPrice;

    /**
     * PriceRangeExpression constructor'i.
     *
     * @param minPrice Minimum fiyat (dahil)
     * @param maxPrice Maksimum fiyat (dahil)
     */
    public PriceRangeExpression(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean interpret(Product product) {
        return product.getPrice() >= minPrice && product.getPrice() <= maxPrice;
    }

    @Override
    public String getDescription() {
        return "price:" + minPrice + "-" + maxPrice;
    }
}
