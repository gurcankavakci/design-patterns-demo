package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * OR EXPRESSION - VEYA Mantigi (Non-Terminal Expression)
 *
 * Interpreter Pattern'in Composite (dal) dugumu.
 * Iki ifadeden biri dogru oldugunda urun eslenir.
 * Birden fazla kategori veya marka aramasi icin idealdir.
 */
public class OrExpression implements SearchExpression {

    private final SearchExpression left;
    private final SearchExpression right;

    /**
     * OrExpression constructor'i.
     *
     * @param left  Sol taraf ifadesi
     * @param right Sag taraf ifadesi
     */
    public OrExpression(SearchExpression left, SearchExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Product product) {
        return left.interpret(product) || right.interpret(product);
    }

    @Override
    public String getDescription() {
        return "(" + left.getDescription() + " OR " + right.getDescription() + ")";
    }
}
