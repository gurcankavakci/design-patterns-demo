package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * CATEGORY EXPRESSION - Kategori Filtresi (Terminal Expression)
 *
 * Interpreter Pattern'in Terminal (yaprak) dugumu.
 * Urunu kategori adina gore filtreler. Kucuk/buyuk harf duyarsizdir.
 */
public class CategoryExpression implements SearchExpression {

    private final String category;

    /**
     * CategoryExpression constructor'i.
     *
     * @param category Aralinacak kategori adi
     */
    public CategoryExpression(String category) {
        this.category = category;
    }

    @Override
    public boolean interpret(Product product) {
        return product.getCategory().equalsIgnoreCase(category);
    }

    @Override
    public String getDescription() {
        return "category:" + category;
    }
}
