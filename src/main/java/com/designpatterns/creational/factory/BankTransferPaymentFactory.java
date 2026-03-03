package com.designpatterns.creational.factory;

/**
 * Banka havalesi ödeme fabrikası.
 * Factory Method Pattern'de "ConcreteCreator" rolünü oynar.
 *
 * Bu sınıf, PaymentFactory'nin soyut createPayment() metodunu implement eder
 * ve her çağrıda yeni bir BankTransferPayment nesnesi oluşturur.
 *
 * OPEN/CLOSED PRINCIPLE GÖSTERIMI:
 * Bu sınıf, mevcut kod DEGISTIRILMEDEN eklendi.
 * PaymentFactory, CreditCardPaymentFactory ve PayPalPaymentFactory
 * hiçbir değişikliğe uğramadı. Yalnızca yeni sınıflar eklendi.
 */
public class BankTransferPaymentFactory extends PaymentFactory {

    private String iban;
    private String bankName;

    /**
     * Banka havalesi ödeme fabrikası oluşturur.
     *
     * @param iban     Hedef IBAN numarası
     * @param bankName Bankanın adı
     */
    public BankTransferPaymentFactory(String iban, String bankName) {
        this.iban = iban;
        this.bankName = bankName;
    }

    /**
     * Factory Method implementasyonu.
     * IBAN ve banka adı bilgileriyle yeni bir BankTransferPayment nesnesi oluşturur.
     *
     * @return Yeni BankTransferPayment nesnesi
     */
    @Override
    public Payment createPayment() {
        System.out.println("  [BANK FACTORY] BankTransferPayment nesnesi oluşturuluyor...");
        return new BankTransferPayment(iban, bankName);
    }
}
