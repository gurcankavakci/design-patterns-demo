package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * BRAND EXPRESSION - Marka Filtresi (Terminal Expression)
 *
 * Interpreter Pattern'in Terminal (yaprak) dugumu.
 * Urunleri marka adina gore filtreler. Kucuk/buyuk harf duyarsizdir.
 */
public class BrandExpression implements SearchExpression {

    private final String brand;

    /**
     * BrandExpression constructor'i.
     *
     * @param brand Aralinacak marka adi
     */
    public BrandExpression(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean interpret(Product product) {
        return product.getBrand().equalsIgnoreCase(brand);
    }

    @Override
    public String getDescription() {
        return "brand:" + brand;
    }
}
