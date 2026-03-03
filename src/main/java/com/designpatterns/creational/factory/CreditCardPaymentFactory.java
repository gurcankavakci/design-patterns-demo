package com.designpatterns.creational.factory;

/**
 * Kredi kartı ödeme fabrikası.
 * Factory Method Pattern'de "ConcreteCreator" rolünü oynar.
 *
 * Bu sınıf, PaymentFactory'nin soyut createPayment() metodunu implement eder
 * ve her çağrıda yeni bir CreditCardPayment nesnesi oluşturur.
 *
 * Kart bilgileri constructor'da alınır ve factory method tarafından
 * CreditCardPayment nesnesine aktarılır.
 */
public class CreditCardPaymentFactory extends PaymentFactory {

    private String cardNumber;
    private String cardHolder;
    private String expiryDate;

    /**
     * Kredi kartı ödeme fabrikası oluşturur.
     *
     * @param cardNumber  16 haneli kart numarası
     * @param cardHolder  Kart sahibinin adı soyadı
     * @param expiryDate  Kartın son kullanma tarihi (MM/YY)
     */
    public CreditCardPaymentFactory(String cardNumber, String cardHolder, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    /**
     * Factory Method implementasyonu.
     * Kredi kartı bilgileriyle yeni bir CreditCardPayment nesnesi oluşturur.
     *
     * @return Yeni CreditCardPayment nesnesi
     */
    @Override
    public Payment createPayment() {
        System.out.println("  [CREDITCARD FACTORY] CreditCardPayment nesnesi oluşturuluyor...");
        return new CreditCardPayment(cardNumber, cardHolder, expiryDate);
    }
}
