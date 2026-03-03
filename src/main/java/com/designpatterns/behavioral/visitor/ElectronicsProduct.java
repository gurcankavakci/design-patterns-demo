package com.designpatterns.behavioral.visitor;

/**
 * ELECTRONICS PRODUCT - Elektronik Urun (ConcreteElement)
 *
 * Visitor Pattern'in ConcreteElement rolunu ustlenir.
 * accept() metodu ile double dispatch saglanir.
 */
public class ElectronicsProduct implements Visitable {

    private final String name;
    private final String brand;
    private final double price;
    private final int warrantyMonths;
    private final double weight;

    /**
     * ElectronicsProduct constructor'i.
     *
     * @param name           Urun adi
     * @param brand          Marka
     * @param price          Fiyat (TL)
     * @param warrantyMonths Garanti suresi (ay)
     * @param weight         Agirlik (kg)
     */
    public ElectronicsProduct(String name, String brand, double price,
                               int warrantyMonths, double weight) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    /**
     * Double dispatch: visitor.visitElectronics(this) cagirilir.
     * Java hangi visitXxx metodunun cagrilacagini bu sayede bilir.
     */
    @Override
    public double accept(ProductVisitor visitor) {
        return visitor.visitElectronics(this);
    }

    // Getters
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public int getWarrantyMonths() { return warrantyMonths; }
    public double getWeight() { return weight; }

    @Override
    public String toString() {
        return "Elektronik: " + name + " (" + brand + ") - " + price + " TL";
    }
}
