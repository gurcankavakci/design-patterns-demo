package com.designpatterns.behavioral.iterator;

import com.designpatterns.model.Product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class CategoryIterator implements ProductIterator {

    private final List<Product> filtered;
    private int index = 0;
    private final String category;

    public CategoryIterator(List<Product> products, String category) {
        this.category = category;
        this.filtered = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
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
        return "Kategori Iterator: " + category + " (" + filtered.size() + " urun)";
    }
}
