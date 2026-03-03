package com.designpatterns.behavioral.visitor;

/**
 * DIGITAL PRODUCT - Dijital Urun (ConcreteElement)
 *
 * Visitor Pattern'in ConcreteElement rolunu ustlenir.
 * Lisans turu (SINGLE_USER, MULTI_USER, SUBSCRIPTION) farkli vergi kurallarini belirler.
 */
public class DigitalProduct implements Visitable {

    private final String name;
    private final double price;
    private final String licenseType;

    /**
     * DigitalProduct constructor'i.
     *
     * @param name        Urun adi
     * @param price       Fiyat (TL)
     * @param licenseType Lisans turu (SINGLE_USER, MULTI_USER, SUBSCRIPTION)
     */
    public DigitalProduct(String name, double price, String licenseType) {
        this.name = name;
        this.price = price;
        this.licenseType = licenseType;
    }

    /**
     * Double dispatch: visitor.visitDigital(this) cagirilir.
     */
    @Override
    public double accept(ProductVisitor visitor) {
        return visitor.visitDigital(this);
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getLicenseType() { return licenseType; }

    @Override
    public String toString() {
        return "Dijital: " + name + " (" + licenseType + ") - " + price + " TL";
    }
}
