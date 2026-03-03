package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * NON-TERMINAL EXPRESSION - DEĞİL (NOT) mantığı.
 * Başka bir ifadenin sonucunu tersine çevirir.
 */
public class NotExpression implements SearchExpression {

    private final SearchExpression expression;

    public NotExpression(SearchExpression expression) {
        this.expression = expression;
    }

    @Override
    public boolean interpret(Product product) {
        return !expression.interpret(product);
    }

    @Override
    public String getDescription() {
        return "NOT(" + expression.getDescription() + ")";
    }
}
