package com.designpatterns.behavioral.iterator;

import com.designpatterns.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AllProductsIterator implements ProductIterator {

    private final List<Product> products;
    private int index = 0;

    public AllProductsIterator(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    @Override
    public boolean hasNext() {
        return index < products.size();
    }

    @Override
    public Product next() {
        if (!hasNext()) throw new NoSuchElementException("Iterator sona ulasti");
        return products.get(index++);
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public int getTotalItems() {
        return products.size();
    }

    @Override
    public String getIteratorType() {
        return "Tum Urunler Iterator (" + products.size() + " urun)";
    }
}
