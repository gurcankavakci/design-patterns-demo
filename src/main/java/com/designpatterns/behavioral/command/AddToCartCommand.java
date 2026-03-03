package com.designpatterns.behavioral.command;

import com.designpatterns.model.Cart;
import com.designpatterns.model.Product;

/**
 * SEPETE EKLE KOMUTU - Add to Cart Command
 *
 * <p>Command Pattern'ın ConcreteCommand rolü.
 * Sepete ürün ekleme işlemini kapsüller ve geri alınabilir hale getirir.</p>
 *
 * <p><b>execute():</b> Ürünü sepete ekler.<br>
 * <b>undo():</b> Ürünü sepetten çıkarır.</p>
 *
 * <p>Receiver: {@link Cart} - gerçek ekleme işlemini yapan nesne.</p>
 */
public class AddToCartCommand implements ShopCommand {

    /** Komutun çalışacağı sepet (Receiver). */
    private final Cart cart;

    /** Sepete eklenecek ürün. */
    private final Product product;

    /** Eklenecek ürün miktarı. */
    private final int quantity;

    /**
     * AddToCartCommand constructor'ı.
     *
     * @param cart     İşlemin yapılacağı sepet
     * @param product  Eklenecek ürün
     * @param quantity Eklenecek miktar
     */
    public AddToCartCommand(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Ürünü sepete ekler.
     * Cart.addProduct() metodunu çağırır.
     */
    @Override
    public void execute() {
        cart.addProduct(product, quantity);
        System.out.println("Komut Calistı [Sepete Ekle]: "
                + product.getName() + " x" + quantity
                + " (Birim fiyat: " + product.getPrice() + " TL)");
    }

    /**
     * Ürünü sepetten çıkararak ekleme işlemini geri alır.
     * Cart.removeProduct() metodunu çağırır.
     */
    @Override
    public void undo() {
        cart.removeProduct(product.getId());
        System.out.println("Komut Geri Alındı [Sepetten Cikar]: "
                + product.getName() + " sepetten kaldirildi");
    }

    @Override
    public String getCommandName() {
        return "Sepete Ekle";
    }

    @Override
    public String getDescription() {
        return product.getName() + " x" + quantity
                + " sepete eklendi (toplam: "
                + String.format("%.2f", product.getPrice() * quantity) + " TL)";
    }

    // Getters (test ve inceleme için)
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
}
