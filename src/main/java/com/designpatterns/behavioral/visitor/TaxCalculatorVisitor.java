package com.designpatterns.behavioral.visitor;

/**
 * TAX CALCULATOR VISITOR - Vergi Hesaplayicisi (ConcreteVisitor)
 *
 * Turkiye KDV kurallarina gore her urun tipi icin vergi hesaplar.
 *
 * KDV Oranlari:
 * - Elektronik: %%20
 * - Gida (genel): %%1
 * - Dijital Hizmet: %%18
 * - Giyim: %%20
 */
public class TaxCalculatorVisitor implements ProductVisitor {

    private double totalTax = 0;

    @Override
    public double visitElectronics(ElectronicsProduct p) {
        double tax = p.getPrice() * 0.20; // KDV %%20
        totalTax += tax;
        System.out.printf("  [KDV-Elektronik] %-30s | %%20 KDV = %8.2f TL%n",
                p.getName(), tax);
        return tax;
    }

    @Override
    public double visitFood(FoodProduct p) {
        double rate = 0.01; // Gida KDV %%1
        double tax = p.getPrice() * rate;
        totalTax += tax;
        System.out.printf("  [KDV-Gida]       %-30s | %%1 KDV  = %8.2f TL%n",
                p.getName(), tax);
        return tax;
    }

    @Override
    public double visitDigital(DigitalProduct p) {
        double tax = p.getPrice() * 0.18; // Dijital hizmet KDV %%18
        totalTax += tax;
        System.out.printf("  [KDV-Dijital]    %-30s | %%18 KDV = %8.2f TL%n",
                p.getName(), tax);
        return tax;
    }

    @Override
    public double visitClothing(ClothingProduct p) {
        double tax = p.getPrice() * 0.20; // Giyim KDV %%20
        totalTax += tax;
        System.out.printf("  [KDV-Giyim]      %-30s | %%20 KDV = %8.2f TL%n",
                p.getName(), tax);
        return tax;
    }

    @Override
    public String getVisitorName() {
        return "KDV Hesaplayici";
    }

    /**
     * Toplam KDV ozetini yazdirir.
     */
    public void printTaxSummary() {
        System.out.printf("%n  Toplam KDV Tutari: %.2f TL%n", totalTax);
    }

    /**
     * Toplam KDV tutarini dondurur.
     *
     * @return Toplam KDV (TL)
     */
    public double getTotalTax() {
        return totalTax;
    }
}
