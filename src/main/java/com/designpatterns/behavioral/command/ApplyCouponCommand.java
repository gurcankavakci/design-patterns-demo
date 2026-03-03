package com.designpatterns.behavioral.command;

import com.designpatterns.model.Cart;

import java.util.HashMap;
import java.util.Map;

/**
 * KUPON UYGULA KOMUTU - Apply Coupon Command
 *
 * <p>Command Pattern'ın ConcreteCommand rolü.
 * Sepete indirim kuponu uygulama işlemini kapsüller.</p>
 *
 * <p><b>execute():</b> Kuponu sepete uygular ve indirim hesaplar.<br>
 * <b>undo():</b> Kuponu iptal eder ve indirimi geri alır.</p>
 *
 * <p>Not: Bu simülasyonda gerçek fiyat değişikliği uygulanmaz;
 * gerçek bir sistemde Cart'a discountCode eklenir ve
 * fiyat hesabında bu kullanılır.</p>
 */
public class ApplyCouponCommand implements ShopCommand {

    /** Sepet (Receiver). */
    private final Cart cart;

    /** Kupon kodu. */
    private final String couponCode;

    /** İndirim yüzdesi (0-100 arası). */
    private final double discountPercentage;

    /** Kupon uygulanmadan önceki fiyatlar (undo için). */
    private Map<String, Double> originalPrices = new HashMap<>();

    /** Hesaplanan indirim tutarı. */
    private double calculatedDiscountAmount;

    /**
     * ApplyCouponCommand constructor'ı.
     *
     * @param cart               İşlemin yapılacağı sepet
     * @param couponCode         Kupon kodu
     * @param discountPercentage İndirim yüzdesi (örn: 15.0 = %15 indirim)
     */
    public ApplyCouponCommand(Cart cart, String couponCode, double discountPercentage) {
        this.cart = cart;
        this.couponCode = couponCode;
        this.discountPercentage = discountPercentage;
    }

    /**
     * Kuponu sepete uygular.
     * İndirim tutarını hesaplar ve sepetteki toplam üzerinden gösterir.
     */
    @Override
    public void execute() {
        double totalAmount = cart.getTotalAmount();

        if (totalAmount <= 0) {
            System.out.println("Komut Uyarısı [Kupon Uygula]: Sepet bos, kupon uygulanamaz.");
            return;
        }

        calculatedDiscountAmount = totalAmount * (discountPercentage / 100.0);

        System.out.println("Komut Calistı [Kupon Uygula]: " + couponCode
                + " - %" + discountPercentage + " indirim uygulandı");
        System.out.printf("  Sepet toplamı: %.2f TL%n", totalAmount);
        System.out.printf("  İndirim tutarı: %.2f TL%n", calculatedDiscountAmount);
        System.out.printf("  Ödenecek tutar: %.2f TL%n", totalAmount - calculatedDiscountAmount);
    }

    /**
     * Kuponu iptal eder.
     */
    @Override
    public void undo() {
        System.out.println("Komut Geri Alındı [Kupon İptal]: " + couponCode
                + " kuponu iptal edildi");
        if (calculatedDiscountAmount > 0) {
            System.out.printf("  İade edilen indirim: %.2f TL%n", calculatedDiscountAmount);
            System.out.printf("  Sepet toplamı geri: %.2f TL%n",
                    cart.getTotalAmount() + calculatedDiscountAmount);
        }
        calculatedDiscountAmount = 0;
    }

    @Override
    public String getCommandName() {
        return "Kupon Uygula: " + couponCode;
    }

    @Override
    public String getDescription() {
        return "Kupon " + couponCode + " uygulandı - %" + discountPercentage
                + " indirim (tutar: " + String.format("%.2f", calculatedDiscountAmount) + " TL)";
    }

    // Getters
    public String getCouponCode() { return couponCode; }
    public double getDiscountPercentage() { return discountPercentage; }
    public double getCalculatedDiscountAmount() { return calculatedDiscountAmount; }
}
