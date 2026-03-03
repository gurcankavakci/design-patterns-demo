package com.designpatterns.behavioral.visitor;

/**
 * FOOD PRODUCT - Gida Urunu (ConcreteElement)
 *
 * Visitor Pattern'in ConcreteElement rolunu ustlenir.
 * Organik ve ithal urun ozellikleri farkli vergi/indirim kurallarini tetikler.
 */
public class FoodProduct implements Visitable {

    private final String name;
    private final double price;
    private final boolean organic;
    private final boolean imported;

    /**
     * FoodProduct constructor'i.
     *
     * @param name     Urun adi
     * @param price    Fiyat (TL)
     * @param organic  Organik mi?
     * @param imported Ithal mi?
     */
    public FoodProduct(String name, double price, boolean organic, boolean imported) {
        this.name = name;
        this.price = price;
        this.organic = organic;
        this.imported = imported;
    }

    /**
     * Double dispatch: visitor.visitFood(this) cagirilir.
     */
    @Override
    public double accept(ProductVisitor visitor) {
        return visitor.visitFood(this);
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isOrganic() { return organic; }
    public boolean isImported() { return imported; }

    @Override
    public String toString() {
        return "Gida: " + name + " - " + price + " TL" + (organic ? " [Organik]" : "");
    }
}
