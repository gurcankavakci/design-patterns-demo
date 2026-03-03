package com.designpatterns.creational.factory;

/**
 * Kredi kartı ile ödeme implementasyonu.
 * Factory Method Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Kart numarasının son 4 hanesi hariç tüm bilgiler maskelenir.
 * Gerçek bir uygulamada PCI-DSS standartlarına uygun şifreleme kullanılır.
 */
public class CreditCardPayment implements Payment {

    private String maskedCardNumber;
    private String cardHolder;
    private String expiryDate;

    /**
     * Kredi kartı ödeme nesnesi oluşturur.
     * Kart numarası otomatik olarak maskelenir; sadece son 4 hane gösterilir.
     *
     * @param cardNumber  16 haneli kart numarası (boşluksuz)
     * @param cardHolder  Kart üzerindeki isim
     * @param expiryDate  Son kullanma tarihi (MM/YY formatında)
     */
    public CreditCardPayment(String cardNumber, String cardHolder, String expiryDate) {
        // Güvenlik: Kart numarasının son 4 hanesi dışı maskelenir
        this.maskedCardNumber = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
    }

    /**
     * Kredi kartıyla ödeme işlemini gerçekleştirir.
     * 3D Secure doğrulama simüle edilir.
     *
     * @param amount Ödenecek tutar
     * @return İşlem başarılıysa true
     */
    @Override
    public boolean processPayment(double amount) {
        System.out.println("  [KREDI KARTI] Ödeme işlemi başlatıldı...");
        System.out.println("  [KREDI KARTI] Kredi kartı ile ödeme: " + String.format("%.2f", amount) +
                " TL - Kart: " + maskedCardNumber);
        System.out.println("  [KREDI KARTI] Kart sahibi: " + cardHolder + " | Son kullanma: " + expiryDate);
        System.out.println("  [KREDI KARTI] 3D Secure doğrulama simüle ediliyor...");
        System.out.println("  [KREDI KARTI] Banka onayı alındı. Ödeme BASARILI.");
        return true;
    }

    /**
     * Kredi kartına iade işlemi gerçekleştirir.
     * İade 3-5 iş günü içinde hesaba yansır.
     *
     * @param amount İade edilecek tutar
     * @return İade başarılıysa true
     */
    @Override
    public boolean refund(double amount) {
        System.out.println("  [KREDI KARTI] İade işlemi başlatıldı...");
        System.out.println("  [KREDI KARTI] Kredi kartına iade: " + String.format("%.2f", amount) +
                " TL - Kart: " + maskedCardNumber);
        System.out.println("  [KREDI KARTI] İade 3-5 iş günü içinde hesabınıza yansıyacak.");
        return true;
    }

    /**
     * @return "Kredi Kartı"
     */
    @Override
    public String getPaymentType() {
        return "Kredi Kartı";
    }

    /**
     * Maskelenmiş kart bilgilerini döndürür.
     * Hassas veriler asla tam olarak gösterilmez.
     *
     * @return Kart sahibi ve maskelenmiş kart numarası
     */
    @Override
    public String getPaymentDetails() {
        return "Kart Sahibi: " + cardHolder +
                " | Kart No: " + maskedCardNumber +
                " | Son Kullanma: " + expiryDate;
    }

    /**
     * Maskelenmiş kart numarasını döndürür.
     *
     * @return Maskelenmiş kart numarası (örn: **** **** **** 4242)
     */
    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    /**
     * Kart sahibinin adını döndürür.
     *
     * @return Kart sahibi adı
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Kartın son kullanma tarihini döndürür.
     *
     * @return Son kullanma tarihi (MM/YY)
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{maskedCardNumber='" + maskedCardNumber +
                "', cardHolder='" + cardHolder +
                "', expiryDate='" + expiryDate + "'}";
    }
}
