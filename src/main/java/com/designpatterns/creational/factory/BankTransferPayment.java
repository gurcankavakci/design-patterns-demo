package com.designpatterns.creational.factory;

/**
 * Banka havalesi ile ödeme implementasyonu.
 * Factory Method Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Banka havalesi, IBAN numarası ve banka adı ile tanımlanır.
 * Gerçek bir uygulamada EFT/Havale işlemleri banka API'si üzerinden gerçekleştirilir.
 */
public class BankTransferPayment implements Payment {

    private String iban;
    private String bankName;

    /**
     * Banka havalesi ödeme nesnesi oluşturur.
     *
     * @param iban     Hedef IBAN numarası (TR ile başlayan 26 haneli)
     * @param bankName Bankanın adı
     */
    public BankTransferPayment(String iban, String bankName) {
        this.iban = iban;
        this.bankName = bankName;
    }

    /**
     * Banka havalesi ile ödeme işlemini gerçekleştirir.
     * EFT gönderimi simüle edilir.
     *
     * @param amount Ödenecek tutar
     * @return İşlem başarılıysa true
     */
    @Override
    public boolean processPayment(double amount) {
        System.out.println("  [BANKA HAVALESI] Ödeme işlemi başlatıldı...");
        System.out.println("  [BANKA HAVALESI] Banka havalesi: " + String.format("%.2f", amount) +
                " TL - IBAN: " + maskIban(iban));
        System.out.println("  [BANKA HAVALESI] Banka: " + bankName);
        System.out.println("  [BANKA HAVALESI] EFT talimatı bankaya iletildi...");
        System.out.println("  [BANKA HAVALESI] İşlem sıraya alındı. 1-2 iş günü içinde tamamlanacak.");
        return true;
    }

    /**
     * Banka havalesi için iade işlemini gerçekleştirir.
     * İade havalesi gönderilir.
     *
     * @param amount İade edilecek tutar
     * @return İade başarılıysa true
     */
    @Override
    public boolean refund(double amount) {
        System.out.println("  [BANKA HAVALESI] İade işlemi başlatıldı...");
        System.out.println("  [BANKA HAVALESI] IBAN'a iade havalesi: " + String.format("%.2f", amount) +
                " TL - IBAN: " + maskIban(iban));
        System.out.println("  [BANKA HAVALESI] İade havalesi 1-3 iş günü içinde hesabınıza yansıyacak.");
        return true;
    }

    /**
     * @return "Banka Havalesi"
     */
    @Override
    public String getPaymentType() {
        return "Banka Havalesi";
    }

    /**
     * Banka havalesi detaylarını döndürür.
     * IBAN kısmen maskelenerek gösterilir.
     *
     * @return Banka adı ve maskelenmiş IBAN
     */
    @Override
    public String getPaymentDetails() {
        return "Banka: " + bankName + " | IBAN: " + maskIban(iban);
    }

    /**
     * IBAN numarasını kısmen maskeler.
     * İlk 6 ve son 4 karakter görünür, ortası maskelenir.
     * Örneğin: "TR330006100519786457841326" → "TR3300 **** **** **** 1326"
     *
     * @param iban Maskelenecek IBAN numarası
     * @return Kısmen maskelenmiş IBAN
     */
    private String maskIban(String iban) {
        if (iban == null || iban.length() < 10) {
            return "TR** **** **** **** ****";
        }
        String clean = iban.replaceAll("\\s", "");
        if (clean.length() < 10) {
            return clean;
        }
        String prefix = clean.substring(0, 6);
        String suffix = clean.substring(clean.length() - 4);
        return prefix + " **** **** **** " + suffix;
    }

    /**
     * IBAN numarasını döndürür.
     *
     * @return IBAN numarası
     */
    public String getIban() {
        return iban;
    }

    /**
     * Banka adını döndürür.
     *
     * @return Banka adı
     */
    public String getBankName() {
        return bankName;
    }

    @Override
    public String toString() {
        return "BankTransferPayment{iban='" + maskIban(iban) +
                "', bankName='" + bankName + "'}";
    }
}
