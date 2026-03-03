package com.designpatterns.behavioral.template;

import com.designpatterns.model.Order;

import java.util.ArrayList;
import java.util.List;

public class SalesReport extends ReportTemplate {

    private List<Order> orders = new ArrayList<>();
    private double totalRevenue;
    private int totalOrders;

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    protected void collectData() {
        System.out.println("  [SalesReport] Satis verileri toplaniyor... (" + orders.size() + " siparis)");
    }

    @Override
    protected void analyzeData() {
        totalOrders = orders.size();
        totalRevenue = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        System.out.printf("  [SalesReport] Analiz tamamlandi: %d siparis, %.2f TL gelir%n", totalOrders, totalRevenue);
    }

    @Override
    protected String formatReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Toplam Siparis    : %d%n", totalOrders));
        sb.append(String.format("  Toplam Gelir      : %.2f TL%n", totalRevenue));
        sb.append(String.format("  Ortalama Siparis  : %.2f TL%n", totalOrders > 0 ? totalRevenue / totalOrders : 0));
        return sb.toString();
    }

    @Override
    protected String getReportTitle() {
        return "Satis Raporu";
    }

    @Override
    protected String getReportType() {
        return "SALES";
    }

    @Override
    protected boolean shouldSendEmail() {
        return true; // Override hook!
    }

    @Override
    protected boolean includeCharts() {
        return true; // Override hook!
    }
}
