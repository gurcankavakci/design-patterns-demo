package com.designpatterns.behavioral.visitor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DISCOUNT VISITOR - Indirim Ziyaretcisi (ConcreteVisitor)
 *
 * Her urun tipine ozel indirim kurallari uygular.
 * Visitor state'i biriktirebildigi icin toplam indirim takip edilebilir.
 *
 * Kural ozeti:
 * - Elektronik: Premium %10, Normal %5
 * - Gida: Organik ise indirim yok (%0), degil ise %2
 * - Dijital: Premium %15, Normal %0
 * - Giyim: Premium %20, Normal %10
 */
public class DiscountVisitor implements ProductVisitor {

    private final boolean isPremiumCustomer;
    private final Map<String, Double> discountLog = new LinkedHashMap<>();
    private double totalDiscount = 0;

    /**
     * DiscountVisitor constructor'i.
     *
     * @param isPremiumCustomer Musteri premium uye mi?
     */
    public DiscountVisitor(boolean isPremiumCustomer) {
        this.isPremiumCustomer = isPremiumCustomer;
    }

    @Override
    public double visitElectronics(ElectronicsProduct p) {
        double rate = isPremiumCustomer ? 0.10 : 0.05;
        double amount = p.getPrice() * rate;
        discountLog.put(p.getName(), amount);
        totalDiscount += amount;
        System.out.printf("  [Indirim-Elektronik] %-30s | %%%4.0f indirim = %8.2f TL%n",
                p.getName(), rate * 100, amount);
        return amount;
    }

    @Override
    public double visitFood(FoodProduct p) {
        double rate = p.isOrganic() ? 0.0 : 0.02;
        double amount = p.getPrice() * rate;
        discountLog.put(p.getName(), amount);
        totalDiscount += amount;
        System.out.printf("  [Indirim-Gida]       %-30s | %%%4.0f indirim = %8.2f TL%n",
                p.getName(), rate * 100, amount);
        return amount;
    }

    @Override
    public double visitDigital(DigitalProduct p) {
        double rate = isPremiumCustomer ? 0.15 : 0.0;
        double amount = p.getPrice() * rate;
        discountLog.put(p.getName(), amount);
        totalDiscount += amount;
        System.out.printf("  [Indirim-Dijital]    %-30s | %%%4.0f indirim = %8.2f TL%n",
                p.getName(), rate * 100, amount);
        return amount;
    }

    @Override
    public double visitClothing(ClothingProduct p) {
        double rate = isPremiumCustomer ? 0.20 : 0.10;
        double amount = p.getPrice() * rate;
        discountLog.put(p.getName(), amount);
        totalDiscount += amount;
        System.out.printf("  [Indirim-Giyim]      %-30s | %%%4.0f indirim = %8.2f TL%n",
                p.getName(), rate * 100, amount);
        return amount;
    }

    @Override
    public String getVisitorName() {
        return "Indirim Hesaplayici (Premium: " + isPremiumCustomer + ")";
    }

    /**
     * Toplam indirim ozetini yazdirir.
     */
    public void printDiscountSummary() {
        System.out.printf("%n  Toplam Indirim Tutari: %.2f TL%n", totalDiscount);
        System.out.printf("  Musteri Tipi: %s%n", isPremiumCustomer ? "Premium" : "Normal");
    }

    /**
     * Indirim logunu dondurur.
     *
     * @return Urun adi -> indirim tutari map'i
     */
    public Map<String, Double> getDiscountLog() {
        return discountLog;
    }

    /**
     * Toplam indirim tutarini dondurur.
     *
     * @return Toplam indirim (TL)
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }
}
