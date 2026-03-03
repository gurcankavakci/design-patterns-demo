package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

/**
 * IN STOCK EXPRESSION - Stok Kontrolu (Terminal Expression)
 *
 * Interpreter Pattern'in Terminal (yaprak) dugumu.
 * Yalnizca stokta mevcut olan urunleri filtreler.
 */
public class InStockExpression implements SearchExpression {

    @Override
    public boolean interpret(Product product) {
        return product.getStockQuantity() > 0;
    }

    @Override
    public String getDescription() {
        return "inStock:true";
    }
}
