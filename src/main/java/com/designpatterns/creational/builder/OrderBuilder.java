package com.designpatterns.creational.builder;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;
import com.designpatterns.model.Product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * BUILDER PATTERN - İnşaatçı Deseni
 *
 * TANIM: Karmaşık bir nesneyi adım adım oluşturmanızı sağlar.
 * Aynı inşaat süreci, farklı temsiller oluşturabilir.
 *
 * PROBLEM: Çok sayıda parametresi olan bir nesne oluşturmak istiyoruz.
 * Bazı parametreler zorunlu, bazıları isteğe bağlı.
 *
 * TELESCOPING CONSTRUCTOR ANTI-PATTERN:
 * // Bu kötü! Hangi parametre ne anlama geliyor?
 * new Order(customer, items, address, "KrediKarti", "STANDART", 15.0, true, "Hediye")
 *
 * BUILDER ÇÖZÜMÜ:
 * new OrderBuilder()
 *   .forCustomer(customer)
 *   .withProduct(laptop, 1)
 *   .toAddress("İstanbul, Türkiye")
 *   .withPayment("Kredi Kartı")
 *   .withStandardShipping()
 *   .build()
 *
 * ROLLER:
 * - Builder (OrderBuilder): adım adım inşaat interface/class
 * - Director (OrderDirector): inşaat adımlarının sırasını yönetir
 * - Product (Order): oluşturulan karmaşık nesne
 *
 * DIRECTOR'IN ROLÜ:
 * Client'ın builder'ı nasıl kullanacağını bilmesine gerek yoktur.
 * Director, belirli konfigürasyonlar için hazır reçeteler sunar.
 *
 * UML DIAGRAM:
 * ┌─────────────────┐        ┌─────────────────────────────────┐
 * │   OrderDirector │------> │         OrderBuilder             │
 * └─────────────────┘ uses   ├─────────────────────────────────┤
 *                            │ - customer: Customer             │
 *                            │ - items: Map<Product, Integer>   │
 *                            │ - shippingAddress: String        │
 *                            ├─────────────────────────────────┤
 *                            │ + forCustomer(): OrderBuilder    │
 *                            │ + withProduct(): OrderBuilder    │
 *                            │ + toAddress(): OrderBuilder      │
 *                            │ + withPayment(): OrderBuilder    │
 *                            │ + withShipping(): OrderBuilder   │
 *                            │ + build(): Order                 │
 *                            └──────────────┬──────────────────┘
 *                                           │ creates
 *                                    ┌──────┴──────┐
 *                                    │    Order    │
 *                                    └─────────────┘
 *
 * FLUENT INTERFACE (METHOD CHAINING):
 * Her setter metodu 'this' döndürür, böylece zincirleme çağrı yapılabilir.
 * Okunabilir ve anlaşılır nesne oluşturma sağlanır.
 *
 * AVANTAJLAR:
 * + Telescoping constructor'dan kurtarır
 * + Okunabilir, anlaşılır nesne oluşturma
 * + Nesne her zaman geçerli durumda oluşturulur (build() validasyon yapar)
 * + Aynı builder ile farklı temsiller oluşturulabilir
 * + İsteğe bağlı parametreler kolayca yönetilir
 *
 * DEZAVANTAJLAR:
 * - Ek kod (Builder sınıfı)
 * - Her Product için ayrı Builder gerekebilir
 *
 * NE ZAMAN KULLANILIR:
 * - 4+ constructor parametresi varsa
 * - Bazı parametreler isteğe bağlıysa
 * - İmmutable nesne oluşturmak için
 * - Test fixture oluşturmak için (Test Builder pattern)
 *
 * ---
 *
 * BUILDER PATTERN - Builder Pattern
 *
 * DEFINITION: Lets you construct complex objects step by step.
 * The same construction process can create different representations.
 *
 * PROBLEM: We want to create an object with many parameters,
 * some required and some optional.
 *
 * SOLUTION: A builder class with fluent interface for step-by-step construction.
 * The build() method validates and creates the final object.
 */
public class OrderBuilder {

    private Customer customer;
    private Map<Product, Integer> items = new LinkedHashMap<>();
    private String shippingAddress;
    private String paymentMethod;
    private String shippingType = "STANDARD";
    private double shippingCost = 15.0;
    private String notes;
    private boolean giftWrapping = false;

    /**
     * Siparişin müşterisini ayarlar.
     * Müşterinin varsayılan adresi otomatik olarak teslimat adresi olarak atanır.
     * Farklı bir adres için toAddress() çağrılabilir.
     *
     * @param customer Sipariş müşterisi
     * @return this (method chaining için)
     */
    public OrderBuilder forCustomer(Customer customer) {
        this.customer = customer;
        this.shippingAddress = customer.getAddress(); // Varsayılan adres
        System.out.println("  [BUILDER] Müşteri ayarlandı: " + customer.getName() +
                " | Adres: " + customer.getAddress());
        return this;
    }

    /**
     * Siparişe ürün ekler.
     * Aynı ürün birden fazla çağrıldığında mevcut miktara eklenir.
     *
     * @param product  Eklenecek ürün
     * @param quantity Eklenecek adet
     * @return this (method chaining için)
     */
    public OrderBuilder withProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.println("  [BUILDER] Ürün eklendi: " + product.getName() +
                " x" + quantity + " (" + String.format("%.2f", product.getPrice()) + " TL)");
        return this;
    }

    /**
     * Teslimat adresini belirler.
     * Müşterinin varsayılan adresinden farklı bir adrese göndermek için kullanılır.
     *
     * @param address Teslimat adresi
     * @return this (method chaining için)
     */
    public OrderBuilder toAddress(String address) {
        this.shippingAddress = address;
        System.out.println("  [BUILDER] Teslimat adresi ayarlandı: " + address);
        return this;
    }

    /**
     * Ödeme yöntemini belirler.
     * Örnek değerler: "Kredi Kartı", "PayPal", "Banka Havalesi", "Kapıda Ödeme"
     *
     * @param paymentMethod Ödeme yöntemi
     * @return this (method chaining için)
     */
    public OrderBuilder withPayment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        System.out.println("  [BUILDER] Ödeme yöntemi ayarlandı: " + paymentMethod);
        return this;
    }

    /**
     * Kargo tipini ve maliyetini manuel olarak belirler.
     *
     * @param type Kargo tipi (örn: "STANDARD", "EXPRESS", "FREE")
     * @param cost Kargo ücreti (TL cinsinden)
     * @return this (method chaining için)
     */
    public OrderBuilder withShipping(String type, double cost) {
        this.shippingType = type;
        this.shippingCost = cost;
        System.out.println("  [BUILDER] Kargo ayarlandı: " + type +
                " (" + String.format("%.2f", cost) + " TL)");
        return this;
    }

    /**
     * Standart kargo seçer (15 TL, 2-3 iş günü).
     *
     * @return this (method chaining için)
     */
    public OrderBuilder withStandardShipping() {
        return withShipping("STANDARD", 15.0);
    }

    /**
     * Ekspres kargo seçer (35 TL, aynı gün veya ertesi gün).
     *
     * @return this (method chaining için)
     */
    public OrderBuilder withExpressShipping() {
        return withShipping("EXPRESS", 35.0);
    }

    /**
     * Ücretsiz kargo seçer (genellikle belirli bir tutar üzeri veya promosyon).
     *
     * @return this (method chaining için)
     */
    public OrderBuilder withFreeShipping() {
        return withShipping("FREE", 0.0);
    }

    /**
     * Siparişe hediye paketi seçeneği ekler.
     * Ekstra ücret uygulanabilir; bu örnekte ücretsizdir.
     *
     * @return this (method chaining için)
     */
    public OrderBuilder withGiftWrapping() {
        this.giftWrapping = true;
        System.out.println("  [BUILDER] Hediye paketi eklendi");
        return this;
    }

    /**
     * Sipariş notunu ayarlar.
     * Kargo şirketine veya satıcıya özel mesaj içerebilir.
     *
     * @param notes Sipariş notu
     * @return this (method chaining için)
     */
    public OrderBuilder withNotes(String notes) {
        this.notes = notes;
        System.out.println("  [BUILDER] Sipariş notu eklendi: " + notes);
        return this;
    }

    /**
     * Builder'ı sıfırlar. Aynı builder nesnesiyle yeni siparişler oluşturmak için.
     *
     * @return this (method chaining için)
     */
    public OrderBuilder reset() {
        this.customer = null;
        this.items = new LinkedHashMap<>();
        this.shippingAddress = null;
        this.paymentMethod = null;
        this.shippingType = "STANDARD";
        this.shippingCost = 15.0;
        this.notes = null;
        this.giftWrapping = false;
        return this;
    }

    /**
     * Sipariş nesnesini oluşturur ve döndürür.
     *
     * Validasyon adımları:
     * 1. Müşteri zorunlu
     * 2. En az bir ürün zorunlu
     * 3. Ödeme yöntemi zorunlu ve boş olamaz
     *
     * Build başarılı ise Order nesnesi döner, değilse IllegalStateException fırlatılır.
     *
     * @return Oluşturulan Order nesnesi
     * @throws IllegalStateException Zorunlu alanlar eksikse
     */
    public Order build() {
        // Validasyon - Build sürecinde geçersiz durum önlenir
        if (customer == null) {
            throw new IllegalStateException(
                    "Builder Hatası: Müşteri zorunludur. forCustomer() çağrısı yapılmadı.");
        }
        if (items.isEmpty()) {
            throw new IllegalStateException(
                    "Builder Hatası: En az bir ürün gereklidir. withProduct() çağrısı yapılmadı.");
        }
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {
            throw new IllegalStateException(
                    "Builder Hatası: Ödeme yöntemi zorunludur. withPayment() çağrısı yapılmadı.");
        }

        // Benzersiz sipariş ID'si oluştur
        String orderId = "ORD-" + System.currentTimeMillis();

        System.out.println("  [BUILDER] Sipariş oluşturuluyor...");

        // Order nesnesini oluştur ve yapılandır
        Order order = new Order(orderId, customer);

        // Tüm ürünleri ekle
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
        }

        // Kargo ve ödeme bilgilerini ayarla
        order.setShippingCost(shippingCost);
        order.setShippingType(shippingType);
        order.setPaymentMethod(paymentMethod);

        if (shippingAddress != null && !shippingAddress.isEmpty()) {
            order.setShippingAddress(shippingAddress);
        }

        // Durum: İşleniyor
        order.setStatus("PENDING");

        // Özet bilgi
        System.out.println("  [BUILDER] ✓ Sipariş oluşturuldu: " + orderId +
                " | Müşteri: " + customer.getName() +
                " | Tutar: " + String.format("%.2f", order.getTotalAmount()) + " TL" +
                " | Kargo: " + shippingType + " (" + String.format("%.2f", shippingCost) + " TL)" +
                (giftWrapping ? " | [HEDİYE PAKETİ]" : "") +
                (notes != null ? " | Not: " + notes : ""));

        return order;
    }

    /**
     * Builder'ın mevcut durumunu gösterir (debug amaçlı).
     *
     * @return Builder durumu string olarak
     */
    @Override
    public String toString() {
        return "OrderBuilder{" +
                "customer=" + (customer != null ? customer.getName() : "null") +
                ", itemCount=" + items.size() +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", shippingType='" + shippingType + '\'' +
                ", shippingCost=" + shippingCost +
                ", giftWrapping=" + giftWrapping +
                ", notes='" + notes + '\'' +
                '}';
    }
}
