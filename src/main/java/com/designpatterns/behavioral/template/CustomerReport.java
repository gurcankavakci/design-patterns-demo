package com.designpatterns.behavioral.template;

import com.designpatterns.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerReport extends ReportTemplate {

    private List<Customer> customers = new ArrayList<>();
    private long premiumCount;
    private double premiumRatio;

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    protected void collectData() {
        System.out.println("  [CustomerReport] Musteri verileri toplaniyor... (" + customers.size() + " musteri)");
    }

    @Override
    protected void analyzeData() {
        premiumCount = customers.stream().filter(Customer::isPremium).count();
        premiumRatio = customers.isEmpty() ? 0 : (double) premiumCount / customers.size() * 100;
        System.out.printf("  [CustomerReport] Premium: %d / %d (%%%.1f)%n", premiumCount, customers.size(), premiumRatio);
    }

    @Override
    protected String formatReport() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("  Toplam Musteri    : %d%n", customers.size()));
        sb.append(String.format("  Premium Uye       : %d (%%%.1f)%n", premiumCount, premiumRatio));
        sb.append(String.format("  Normal Uye        : %d%n", customers.size() - premiumCount));
        sb.append("  --- Musteri Listesi ---\n");
        customers.forEach(c -> sb.append(String.format("  %-20s | %-30s | %s%n",
                c.getName(), c.getEmail(), c.isPremium() ? "Premium" : "Normal")));
        return sb.toString();
    }

    @Override
    protected String getReportTitle() {
        return "Musteri Raporu";
    }

    @Override
    protected String getReportType() {
        return "CUSTOMER";
    }
}
