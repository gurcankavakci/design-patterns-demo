package com.designpatterns.creational.factory;

/**
 * FACTORY METHOD PATTERN - Fabrika Metot Deseni
 *
 * TANIM: Nesne oluşturma için bir interface tanımlar, ancak hangi sınıfın
 * instantiate edileceğine subclass'lar karar verir. Factory Method, bir
 * sınıfın instantiation'ını subclass'lara defer eder.
 *
 * PROBLEM: Bir işlemi gerçekleştirirken hangi nesnenin kullanılacağını
 * compile time'da bilemiyoruz. Örneğin, kullanıcı hangi ödeme yöntemini
 * seçeceğini runtime'da belirler.
 *
 * ROLLER:
 * - Creator (PaymentFactory): createPayment() factory method'u tanımlar
 * - ConcreteCreator (CreditCardPaymentFactory vb.): factory method'u implement eder
 * - Product (Payment): oluşturulan nesnenin interface'i
 * - ConcreteProduct (CreditCardPayment vb.): gerçek nesne
 *
 * GERCEK HAYAT ANALOGISI:
 * Bir pizza restoranı düşünün. Sipariş verdiğinizde "pizza" istiyorsunuz.
 * Hangi pizza (Margarita, Pepperoni) yapılacağı şubeye göre değişir.
 * Ana sınıf "pizza yap" der, alt sınıf hangi pizzayı yapacağını bilir.
 *
 * UML DIAGRAM:
 * ┌──────────────────────────┐         ┌──────────────────┐
 * │    <<abstract>>          │         │   <<interface>>  │
 * │    PaymentFactory        │-------> │    Payment       │
 * ├──────────────────────────┤creates  ├──────────────────┤
 * │+createPayment(): Payment │         │+processPayment() │
 * │+processOrderPayment()    │         │+refund()         │
 * └──────────┬───────────────┘         │+getPaymentType() │
 *            │ extends                 └────────┬─────────┘
 *    ┌───────┴──────────┐                       │implements
 * ┌──┴──────────┐  ┌────┴─────────┐    ┌────────┴──────────────┐
 * │CreditCard   │  │PayPal        │    │CreditCardPayment       │
 * │PaymentFact. │  │PaymentFact.  │    │PayPalPayment           │
 * └─────────────┘  └──────────────┘    │BankTransferPayment     │
 *                                      └────────────────────────┘
 *
 * OPEN/CLOSED PRINCIPLE:
 * Yeni bir ödeme yöntemi eklemek için mevcut kodu değiştirmiyoruz.
 * Sadece yeni ConcreteCreator ve ConcreteProduct sınıfları ekliyoruz.
 *
 * AVANTAJLAR:
 * + Nesne oluşturma kodunu kullanım kodundan ayırır
 * + Open/Closed Principle'a uyar
 * + Single Responsibility: Her factory tek bir şey yapar
 * + Yeni ürün tipleri eklemek kolaylaşır
 *
 * DEZAVANTAJLAR:
 * - Her yeni ürün tipi için yeni factory sınıfı gerekir
 * - Kod karmaşıklığı artar
 *
 * SIMPLE FACTORY vs FACTORY METHOD:
 * Simple Factory: Tek bir sınıf tüm nesneleri oluşturur (pattern değil, idiom)
 * Factory Method: Abstract class/interface, subclass'lar oluşturur (gerçek pattern)
 *
 * ---
 *
 * FACTORY METHOD PATTERN - Factory Method Pattern
 *
 * DEFINITION: Defines an interface for creating objects, but lets subclasses
 * decide which class to instantiate. Factory Method defers object
 * instantiation to subclasses.
 *
 * PROBLEM: We cannot know at compile time which object will be used.
 * For example, the user decides which payment method to use at runtime.
 *
 * ROLES:
 * - Creator (PaymentFactory): defines the createPayment() factory method
 * - ConcreteCreator: implements the factory method
 * - Product (Payment): the interface of the created object
 * - ConcreteProduct: the real object
 */
public abstract class PaymentFactory {

    /**
     * Factory Method - Alt sınıflar tarafından implement edilmesi gereken soyut metot.
     * Hangi ödeme nesnesinin oluşturulacağını alt sınıf belirler.
     *
     * Bu metodun soyut olması, Factory Method Pattern'in temelidir.
     * Client kodu sadece Payment interface'ini bilir, implementasyonu bilmez.
     *
     * @return Oluşturulan Payment nesnesi
     */
    public abstract Payment createPayment();

    /**
     * Sipariş ödemesini işler.
     * Bu metot, factory method'u kullanarak ödeme nesnesini alır ve işlemi gerçekleştirir.
     *
     * "Template Method" benzeri bir yapı: Üst sınıf akışı tanımlar,
     * alt sınıf ise hangi nesnenin kullanılacağını belirler.
     *
     * @param amount Ödenecek tutar (TL cinsinden)
     * @return Ödeme başarılıysa true, başarısızsa false
     */
    public boolean processOrderPayment(double amount) {
        System.out.println("  [FACTORY] Ödeme işlemi başlatılıyor...");

        // Factory Method çağrısı - hangi ödeme nesnesinin oluşturulacağını bilmiyoruz
        Payment payment = createPayment();

        System.out.println("  [FACTORY] Ödeme yöntemi belirlendi: " + payment.getPaymentType());
        System.out.println("  [FACTORY] Ödeme detayları: " + payment.getPaymentDetails());

        boolean result = payment.processPayment(amount);

        if (result) {
            System.out.println("  [FACTORY] Ödeme BASARILI tamamlandi: " + String.format("%.2f", amount) + " TL");
        } else {
            System.out.println("  [FACTORY] Ödeme BASARISIZ oldu: " + String.format("%.2f", amount) + " TL");
        }

        return result;
    }

    /**
     * Factory method aracılığıyla ödeme instance'ını döndürür.
     * Client'ın doğrudan ödeme nesnesiyle çalışması gerektiğinde kullanılır.
     *
     * @return Oluşturulan Payment nesnesi
     */
    public Payment getPaymentInstance() {
        System.out.println("  [FACTORY] Payment instance oluşturuluyor...");
        Payment payment = createPayment();
        System.out.println("  [FACTORY] Oluşturuldu: " + payment.getPaymentType());
        return payment;
    }
}
