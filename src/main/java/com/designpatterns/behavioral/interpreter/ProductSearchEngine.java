package com.designpatterns.behavioral.interpreter;

import com.designpatterns.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PRODUCT SEARCH ENGINE - Urun Arama Motoru
 *
 * SearchExpression yorumlayicisini kullanarak urun arar.
 * Composite SearchExpression agacini (AST) yorumlayarak filtreleme yapar.
 *
 * Kullanim:
 * ProductSearchEngine engine = new ProductSearchEngine(products);
 * SearchExpression query = new AndExpression(
 *     new CategoryExpression("Elektronik"),
 *     new PriceRangeExpression(0, 50000)
 * );
 * List<Product> results = engine.searchAndDisplay(query);
 */
public class ProductSearchEngine {

    private final List<Product> products;

    /**
     * ProductSearchEngine constructor'i.
     *
     * @param products Arama yapilacak urun listesi
     */
    public ProductSearchEngine(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    /**
     * Verilen ifadeye gore urunleri filtreler ve sonuclari dondurur.
     * Sorgu aciklamasini ve bulunan urun sayisini yazdirir.
     *
     * @param expression Uygulanacak arama ifadesi
     * @return Esleyen urunlerin listesi
     */
    public List<Product> search(SearchExpression expression) {
        System.out.println("  Sorgu: " + expression.getDescription());
        List<Product> results = products.stream()
                .filter(p -> expression.interpret(p))
                .collect(Collectors.toList());
        System.out.println("  Bulunan: " + results.size() + " urun");
        return results;
    }

    /**
     * Sonuclari formatli olarak ekrana yazdirir.
     *
     * @param results Yazdirilacak urun listesi
     */
    public void displayResults(List<Product> results) {
        if (results.isEmpty()) {
            System.out.println("  Sonuc bulunamadi.");
            return;
        }
        results.forEach(p -> System.out.printf("  %-35s | %-10s | %10.2f TL | Stok: %3d%n",
                p.getName(), p.getBrand(), p.getPrice(), p.getStockQuantity()));
    }

    /**
     * Arama yapar ve sonuclari aninda ekrana yazdirir.
     * search() + displayResults() kombinasyonudur.
     *
     * @param expression Uygulanacak arama ifadesi
     * @return Esleyen urunlerin listesi
     */
    public List<Product> searchAndDisplay(SearchExpression expression) {
        List<Product> results = search(expression);
        displayResults(results);
        return results;
    }
}
