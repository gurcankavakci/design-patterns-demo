package com.designpatterns.behavioral.iterator;

import com.designpatterns.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PriceRangeIterator implements ProductIterator {

    private final List<Product> filtered;
    private int index = 0;
    private final double min;
    private final double max;

    public PriceRangeIterator(List<Product> products, double min, double max) {
        this.min = min;
        this.max = max;
        this.filtered = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return index < filtered.size();
    }

    @Override
    public Product next() {
        if (!hasNext()) throw new NoSuchElementException();
        return filtered.get(index++);
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public int getTotalItems() {
        return filtered.size();
    }

    @Override
    public String getIteratorType() {
        return "Fiyat Araligi Iterator: " + min + "-" + max + " TL (" + filtered.size() + " urun, fiyata gore sirali)";
    }
}
