package com.designpatterns.behavioral.iterator;

import com.designpatterns.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AGGREGATE - Urun Katalogu. Iterator'lari olusturan ana koleksiyon.
 */
public class ProductCatalog {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public int size() {
        return products.size();
    }

    // Iterator factory methods
    public ProductIterator createIterator() {
        return new AllProductsIterator(products);
    }

    public ProductIterator createCategoryIterator(String category) {
        return new CategoryIterator(products, category);
    }

    public ProductIterator createPriceRangeIterator(double minPrice, double maxPrice) {
        return new PriceRangeIterator(products, minPrice, maxPrice);
    }

    public ProductIterator createInStockIterator() {
        return new InStockIterator(products);
    }
}
