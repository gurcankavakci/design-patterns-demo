package com.designpatterns.behavioral.template;

import com.designpatterns.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryReport extends ReportTemplate {

    private List<Product> products = new ArrayList<>();
    private long lowStockCount;
    private double totalValue;

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    protected void collectData() {
        System.out.println("  [InventoryReport] Stok verileri toplaniyor... (" + products.size() + " urun)");
    }

    @Override
    protected void analyzeData() {
        lowStockCount = products.stream().filter(p -> p.getStockQuantity() < 10).count();
        totalValue = products.stream().mapToDouble(p -> p.getPrice() * p.getStockQuantity()).sum();
        System.out.printf("  [InventoryReport] Dusuk stok: %d urun | Toplam stok degeri: %.2f TL%n", lowStockCount, totalValue);
    }

    @Override
    protected String formatReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Toplam Urun       : %d%n", products.size()));
        sb.append(String.format("  Dusuk Stok (< 10) : %d urun%n", lowStockCount));
        sb.append(String.format("  Stok Degeri       : %.2f TL%n", totalValue));
        sb.append("  --- Urun Listesi ---\n");
        products.forEach(p -> sb.append(String.format("  %-30s | Stok: %3d | Deger: %10.2f TL%n",
                p.getName(), p.getStockQuantity(), p.getPrice() * p.getStockQuantity())));
        return sb.toString();
    }

    @Override
    protected String getReportTitle() {
        return "Stok Raporu";
    }

    @Override
    protected String getReportType() {
        return "INVENTORY";
    }

    // shouldSendEmail() returns false (default) - hook used but not overridden
}
