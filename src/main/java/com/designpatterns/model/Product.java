package com.designpatterns.model;

/**
 * ShopEase platformunun temel ürün modeli.
 * Bu sınıf birçok design pattern tarafından kullanılır.
 */
public class Product {

    private String id;
    private String name;
    private String brand;
    private String category;
    private double price;
    private int stockQuantity;
    private double weight; // kg cinsinden
    private boolean digital; // dijital ürün mü?
    private String imageUrl;
    private String description;

    public Product() {}

    public Product(String id, String name, String brand, String category,
                   double price, int stockQuantity, double weight, boolean digital) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.weight = weight;
        this.digital = digital;
        this.imageUrl = "https://cdn.shopease.com/products/" + id + ".jpg";
    }

    // Deep copy constructor - Prototype pattern için kullanılır
    public Product(Product other) {
        this.id = other.id + "_copy";
        this.name = other.name;
        this.brand = other.brand;
        this.category = other.category;
        this.price = other.price;
        this.stockQuantity = other.stockQuantity;
        this.weight = other.weight;
        this.digital = other.digital;
        this.imageUrl = other.imageUrl;
        this.description = other.description;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public double getWeight() { return weight; }
    public boolean isDigital() { return digital; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(double price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setDigital(boolean digital) { this.digital = digital; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', brand='%s', price=%.2f TL, stock=%d}",
                id, name, brand, price, stockQuantity);
    }
}
