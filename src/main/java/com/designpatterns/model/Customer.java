package com.designpatterns.model;

/**
 * ShopEase platformunun müşteri modeli.
 */
public class Customer {

    private String id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private boolean premium; // Premium üye mi?
    private String address;

    public Customer() {}

    public Customer(String id, String name, String email, String phone,
                    int age, boolean premium, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.premium = premium;
        this.address = address;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public int getAge() { return age; }
    public boolean isPremium() { return premium; }
    public String getAddress() { return address; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAge(int age) { this.age = age; }
    public void setPremium(boolean premium) { this.premium = premium; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Customer{id='%s', name='%s', email='%s', premium=%s}",
                id, name, email, premium ? "Evet" : "Hayir");
    }
}
