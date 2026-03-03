package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * AND EXPRESSION - VE Mantigi (Non-Terminal Expression)
 *
 * Interpreter Pattern'in Composite (dal) dugumu.
 * Iki ifadenin her ikisi de dogru oldugunda urun eslenir.
 * AST'de ic ice sorgu agaci olusturulmasini saglar.
 */
public class AndExpression implements SearchExpression {

    private final SearchExpression left;
    private final SearchExpression right;

    /**
     * AndExpression constructor'i.
     *
     * @param left  Sol taraf ifadesi
     * @param right Sag taraf ifadesi
     */
    public AndExpression(SearchExpression left, SearchExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Product product) {
        return left.interpret(product) && right.interpret(product);
    }

    @Override
    public String getDescription() {
        return "(" + left.getDescription() + " AND " + right.getDescription() + ")";
    }
}
