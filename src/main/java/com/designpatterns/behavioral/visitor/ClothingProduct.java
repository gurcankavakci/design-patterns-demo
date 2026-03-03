package com.designpatterns.behavioral.visitor;

/**
 * CLOTHING PRODUCT - Giyim Urunu (ConcreteElement)
 *
 * Visitor Pattern'in ConcreteElement rolunu ustlenir.
 * Beden ve materyal bilgisi indirim ve vergi hesaplamalarinda kullanilabilir.
 */
public class ClothingProduct implements Visitable {

    private final String name;
    private final String brand;
    private final double price;
    private final String size;
    private final String material;

    /**
     * ClothingProduct constructor'i.
     *
     * @param name     Urun adi
     * @param brand    Marka
     * @param price    Fiyat (TL)
     * @param size     Beden (XS, S, M, L, XL, XXL)
     * @param material Materyal (Pamuk, Polyester, Deri vb.)
     */
    public ClothingProduct(String name, String brand, double price,
                            String size, String material) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.material = material;
    }

    /**
     * Double dispatch: visitor.visitClothing(this) cagirilir.
     */
    @Override
    public double accept(ProductVisitor visitor) {
        return visitor.visitClothing(this);
    }

    // Getters
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public String getMaterial() { return material; }

    @Override
    public String toString() {
        return "Giyim: " + name + " (" + brand + ", " + size + ") - " + price + " TL";
    }
}
