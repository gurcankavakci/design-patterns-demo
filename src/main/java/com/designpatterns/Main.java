package com.designpatterns;

import com.designpatterns.model.*;
import com.designpatterns.creational.singleton.DatabaseConnection;
import com.designpatterns.creational.factory.*;
import com.designpatterns.creational.abstractfactory.*;
import com.designpatterns.creational.builder.*;
import com.designpatterns.creational.prototype.*;
import com.designpatterns.structural.adapter.*;
import com.designpatterns.structural.bridge.*;
import com.designpatterns.structural.composite.*;
import com.designpatterns.structural.decorator.*;
import com.designpatterns.structural.facade.*;
import com.designpatterns.structural.flyweight.*;
import com.designpatterns.structural.proxy.*;
import com.designpatterns.behavioral.chain.*;
import com.designpatterns.behavioral.command.*;
import com.designpatterns.behavioral.iterator.*;
import com.designpatterns.behavioral.mediator.*;
import com.designpatterns.behavioral.memento.*;
import com.designpatterns.behavioral.observer.*;
import com.designpatterns.behavioral.state.*;
import com.designpatterns.behavioral.strategy.*;
import com.designpatterns.behavioral.template.*;
import com.designpatterns.behavioral.visitor.*;
import com.designpatterns.behavioral.interpreter.*;

import java.util.*;

/**
 * ShopEase Design Patterns Demo - Ana Sinif
 *
 * 23 GoF (Gang of Four) Design Pattern'in kapsamli demosu.
 * Her pattern gercek bir e-ticaret senaryosuyla gosterilmistir.
 *
 * Kategoriler:
 *   Creational (5):  Singleton, Factory Method, Abstract Factory, Builder, Prototype
 *   Structural (7):  Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy
 *   Behavioral (11): Chain of Responsibility, Command, Iterator, Mediator,
 *                    Memento, Observer, State, Strategy, Template Method, Visitor, Interpreter
 */
public class Main {

    public static void main(String[] args) {
        // Banner
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║        ShopEase - Design Patterns Demo                       ║");
        System.out.println("║        23 GoF Pattern - Java Implementasyonu                 ║");
        System.out.println("║        Hazırlayan: ShopEase Yazılım Ekibi                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Demo veriler
        Customer premiumCustomer = new Customer(
                "C001", "Ahmet Yilmaz", "ahmet@gmail.com",
                "0531-111-2233", 35, true, "Kadikoy, Istanbul");
        Customer normalCustomer = new Customer(
                "C002", "Ayse Kaya", "ayse@gmail.com",
                "0532-444-5566", 22, false, "Cankaya, Ankara");

        Product laptop   = new Product("LAPTOP-001",  "MacBook Pro 14",   "Apple",    "Elektronik", 65000, 10,  1.6,  false);
        Product phone    = new Product("PHONE-001",   "iPhone 15 Pro",    "Apple",    "Elektronik", 45000, 25,  0.2,  false);
        Product tshirt   = new Product("TSHIRT-001",  "Nike Dri-FIT",     "Nike",     "Giyim",       350, 100,  0.3,  false);
        Product ebook    = new Product("EBOOK-001",   "Clean Code",       "O'Reilly", "Kitap",        150, 999,  0.0,  true);
        Product headset  = new Product("HEADSET-001", "Sony WH-1000XM5",  "Sony",     "Elektronik", 8500,   5,  0.25, false);
        Product sneakers = new Product("SHOE-001",    "Nike Air Max",     "Nike",     "Giyim",      2500,  30,  0.8,  false);
        List<Product> allProducts = List.of(laptop, phone, tshirt, ebook, headset, sneakers);

        // Tum pattern demolari calistir
        demonstrateSingleton();
        demonstrateFactoryMethod();
        demonstrateAbstractFactory();
        demonstrateBuilder(premiumCustomer, laptop, phone);
        demonstratePrototype(laptop);
        demonstrateAdapter();
        demonstrateBridge();
        demonstrateComposite(laptop, phone, headset, tshirt, sneakers);
        demonstrateDecorator(laptop);
        demonstrateFacade(normalCustomer, laptop);
        demonstrateFlyweight(allProducts);
        demonstrateProxy();
        demonstrateChainOfResponsibility(premiumCustomer, laptop);
        demonstrateCommand(normalCustomer, laptop, tshirt);
        demonstrateIterator(allProducts);
        demonstrateMediator();
        demonstrateMemento(laptop, phone, tshirt);
        demonstrateObserver();
        demonstrateState();
        demonstrateStrategy(premiumCustomer, normalCustomer, laptop);
        demonstrateTemplateMethod(laptop, phone, tshirt, premiumCustomer, normalCustomer);
        demonstrateVisitor();
        demonstrateInterpreter(allProducts);

        // Final ozet
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              DEMO TAMAMLANDI!                                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Creational (5):  Singleton, Factory, AbstractFactory,       ║");
        System.out.println("║                   Builder, Prototype                         ║");
        System.out.println("║  Structural (7):  Adapter, Bridge, Composite, Decorator,     ║");
        System.out.println("║                   Facade, Flyweight, Proxy                   ║");
        System.out.println("║  Behavioral (11): Chain, Command, Iterator, Mediator,        ║");
        System.out.println("║                   Memento, Observer, State, Strategy,        ║");
        System.out.println("║                   Template, Visitor, Interpreter             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    // ─────────────────────────────────────────────────────────────────
    // Yardimci metod - Bolum ayirici
    private static void sep(String title) {
        System.out.println("\n" + "═".repeat(65));
        System.out.println("  " + title);
        System.out.println("═".repeat(65));
    }

    // ─────────────────────────────────────────────────────────────────
    // 1. SINGLETON PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateSingleton() {
        sep("1. SINGLETON PATTERN - Veritabani Baglanti Havuzu");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("db1 == db2: " + (db1 == db2) + " (ayni instance olmali: true)");
        String conn = db1.getConnection();
        db1.executeQuery("SELECT * FROM orders WHERE status='ACTIVE'");
        db1.executeQuery("SELECT COUNT(*) FROM products");
        db1.releaseConnection(conn);
        System.out.println(db1.getStats());
    }

    // ─────────────────────────────────────────────────────────────────
    // 2. FACTORY METHOD PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateFactoryMethod() {
        sep("2. FACTORY METHOD PATTERN - Odeme Isleyici Fabrikasi");
        PaymentFactory ccFactory = new CreditCardPaymentFactory("4111111111111234", "Ahmet Yilmaz", "12/26");
        PaymentFactory ppFactory = new PayPalPaymentFactory("ahmet@gmail.com");
        PaymentFactory btFactory = new BankTransferPaymentFactory("TR12 0001 2345 6789", "Garanti Bankasi");
        System.out.println("Kredi Karti:");
        ccFactory.processOrderPayment(1250.00);
        System.out.println("PayPal:");
        ppFactory.processOrderPayment(1250.00);
        System.out.println("Banka Havalesi:");
        btFactory.processOrderPayment(1250.00);
    }

    // ─────────────────────────────────────────────────────────────────
    // 3. ABSTRACT FACTORY PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateAbstractFactory() {
        sep("3. ABSTRACT FACTORY PATTERN - UI Tema Fabrikasi");
        System.out.println("--- Light Tema ---");
        UIThemeFactory lightFactory = new LightThemeFactory();
        ShopEaseUI lightUI = new ShopEaseUI(lightFactory);
        lightUI.renderLoginPage();
        System.out.println("\n--- Dark Tema ---");
        UIThemeFactory darkFactory = new DarkThemeFactory();
        lightUI.switchTheme(darkFactory);
        lightUI.renderProductPage();
    }

    // ─────────────────────────────────────────────────────────────────
    // 4. BUILDER PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateBuilder(Customer customer, Product p1, Product p2) {
        sep("4. BUILDER PATTERN - Siparis Olusturucu");
        System.out.println("--- Direkt Builder Kullanimi (method chaining) ---");
        Order order1 = new OrderBuilder()
                .forCustomer(customer)
                .withProduct(p1, 1)
                .withPayment("Kredi Karti")
                .withExpressShipping()
                .withGiftWrapping()
                .withNotes("Dikkatli tasinsin")
                .build();
        System.out.println("Olusturulan: " + order1);

        System.out.println("\n--- OrderDirector Kullanimi ---");
        OrderDirector director = new OrderDirector();
        Order standardOrder = director.buildStandardOrder(customer, p1);
        System.out.println("Standard: " + standardOrder);
        Order giftOrder = director.buildGiftOrder(customer, p2, "Besiktas, Istanbul");
        System.out.println("Hediye: " + giftOrder);
    }

    // ─────────────────────────────────────────────────────────────────
    // 5. PROTOTYPE PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstratePrototype(Product laptop) {
        sep("5. PROTOTYPE PATTERN - Urun Sablonu Klonlama");
        ProductRegistry registry = new ProductRegistry();
        System.out.println("Mevcut sablonlar: " + registry.getAvailableTemplates());
        System.out.println("\n--- Laptop sablonundan klon ---");
        ProductPrototype clone1 = registry.cloneProduct("LAPTOP_TEMPLATE");
        clone1.displayInfo();
        System.out.println("\n--- Fiziksel urun prototipi ---");
        PhysicalProductPrototype custom = new PhysicalProductPrototype(laptop);
        custom.addAttribute("Renk", "Uzay Grisi").addAttribute("RAM", "32GB").setDiscount(5.0);
        custom.displayInfo();
        System.out.println("\n--- Klonla varyant olusturma ---");
        ProductPrototype clone2 = custom.clonePrototype();
        clone2.displayInfo();
    }

    // ─────────────────────────────────────────────────────────────────
    // 6. ADAPTER PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateAdapter() {
        sep("6. ADAPTER PATTERN - Eski Odeme Sistemi Adaptoru");
        System.out.println("--- Eski sistem direkt kullanimi ---");
        LegacyPaymentSystem legacy = new LegacyPaymentSystem();
        legacy.makePayment(150000, "C001"); // 1500 TL = 150000 kurus
        System.out.println("\n--- Adapter ile modern interface ---");
        ModernPaymentGateway adapter = new LegacyPaymentAdapter();
        PaymentResult result = adapter.pay(1500.0, "TRY", "C001");
        System.out.println("Sonuc: " + result);
        System.out.println("Desteklenen para birimleri: " + adapter.getSupportedCurrencies());
    }

    // ─────────────────────────────────────────────────────────────────
    // 7. BRIDGE PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateBridge() {
        sep("7. BRIDGE PATTERN - Bildirim Koprusu");
        System.out.println("--- Email ile Siparis Bildirimi ---");
        NotificationSender email = new EmailSender();
        Notification orderNotif = new OrderNotification(email, "ORD-12345", "SHIPPED");
        orderNotif.send("ahmet@gmail.com", "Kargoyu takip edebilirsiniz.");

        System.out.println("\n--- SMS ile geciyoruz (runtime bridge degisimi) ---");
        orderNotif.changeSender(new SMSSender());
        orderNotif.send("0531-111-2233", "Kargolandiniz!");

        System.out.println("\n--- Push ile Promosyon Bildirimi ---");
        Notification promoNotif = new PromotionNotification(new PushNotificationSender(), "YILBASI25", 25.0);
        promoNotif.send("device-token-xyz", "Yilbasi kampanyamiz basladi!");
    }

    // ─────────────────────────────────────────────────────────────────
    // 8. COMPOSITE PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateComposite(Product laptop, Product phone,
                                              Product headset, Product tshirt, Product sneakers) {
        sep("8. COMPOSITE PATTERN - Urun Kategori Agaci");
        ProductCategory root        = new ProductCategory("ShopEase Katalog", "Tum kategoriler");
        ProductCategory electronics = new ProductCategory("Elektronik", "Elektronik urunler");
        ProductCategory clothing    = new ProductCategory("Giyim", "Giyim urunleri");

        electronics.add(new ProductLeaf(laptop));
        electronics.add(new ProductLeaf(phone));
        electronics.add(new ProductLeaf(headset));
        clothing.add(new ProductLeaf(tshirt));
        clothing.add(new ProductLeaf(sneakers));
        root.add(electronics);
        root.add(clothing);

        root.displayTree();
        System.out.println("\nToplam katalog degeri: " + root.getTotalPrice() + " TL");
        System.out.println("Toplam urun sayisi: " + root.getItemCount());
    }

    // ─────────────────────────────────────────────────────────────────
    // 9. DECORATOR PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateDecorator(Product laptop) {
        sep("9. DECORATOR PATTERN - Urun Ozellik Dekorasyonu");
        System.out.println("--- Temel urun ---");
        ShopProduct basic = new BasicProduct(laptop);
        basic.displayProductInfo();

        System.out.println("\n--- + Hediye Paketi ---");
        ShopProduct withGift = new GiftWrapDecorator(basic, "Dogum gununuz kutlu olsun!");
        withGift.displayProductInfo();

        System.out.println("\n--- + 2 Yil Garanti ---");
        ShopProduct withWarranty = new WarrantyDecorator(withGift, 2);
        withWarranty.displayProductInfo();

        System.out.println("\n--- + Ekspres Teslimat ---");
        ShopProduct withExpress = new ExpressDeliveryDecorator(withWarranty);
        withExpress.displayProductInfo();

        System.out.printf("%nFinal Fiyat: %.2f TL (temel: %.2f TL)%n",
                withExpress.getPrice(), basic.getPrice());
    }

    // ─────────────────────────────────────────────────────────────────
    // 10. FACADE PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateFacade(Customer customer, Product laptop) {
        sep("10. FACADE PATTERN - Odeme Sureci Cephesi");
        CheckoutFacade facade = new CheckoutFacade();
        Cart cart = new Cart(customer.getId());
        cart.addProduct(laptop, 1);
        System.out.println("Sepet: " + cart);
        CheckoutResult result = facade.placeOrder(customer, cart, "Kredi Karti", "STANDARD");
        System.out.println("Sonuc: " + result);
    }

    // ─────────────────────────────────────────────────────────────────
    // 11. FLYWEIGHT PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateFlyweight(List<Product> products) {
        sep("11. FLYWEIGHT PATTERN - Urun Ikon Paylasimi");
        System.out.println("100 urun icin ikon gosterimi (ayni kategori ikonlari paylasilir):\n");

        List<Product> displayList = new ArrayList<>(products);
        displayList.addAll(products); // 12 urun - tekrarlayan kategoriler flyweight'i gosterir

        for (int i = 0; i < displayList.size(); i++) {
            Product p = displayList.get(i);
            ProductIcon icon = ProductIconFactory.getIcon(p.getCategory());
            icon.display(p.getName(), p.getPrice(), i + 1);
        }
        System.out.println();
        ProductIconFactory.printStats();
    }

    // ─────────────────────────────────────────────────────────────────
    // 12. PROXY PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateProxy() {
        sep("12. PROXY PATTERN - Gorsel Lazy Loading ve Erisim Kontrolu");

        System.out.println("--- Virtual Proxy: Lazy Loading ---");
        ProductImageProxy publicImg = new ProductImageProxy(
                "https://cdn.shopease.com/laptop.jpg", 4096, "public", "user");
        System.out.println("Proxy olusturuldu. Gorsel henuz yuklenmedi!");
        System.out.println("Gorsel yuklendi mi? " + publicImg.isLoaded());

        System.out.println("\nIlk display() cagrisi (gorseli yukler):");
        publicImg.display("MacBook Pro 14");

        System.out.println("\nIkinci display() cagrisi (cache'den - yeniden yukleme yok!):");
        publicImg.display("MacBook Pro 14");

        System.out.println("\n--- Protection Proxy: Erisim Kontrolu ---");
        ProductImageProxy adminImg = new ProductImageProxy(
                "https://admin.shopease.com/sales-report.jpg", 512, "admin", "user");
        System.out.println("Normal kullanici admin gorseline erismeye calisiyor:");
        adminImg.display("Admin Rapor Gorseli");
    }

    // ─────────────────────────────────────────────────────────────────
    // 13. CHAIN OF RESPONSIBILITY PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateChainOfResponsibility(Customer customer, Product laptop) {
        sep("13. CHAIN OF RESPONSIBILITY - Siparis Dogrulama Zinciri");

        // Zinciri kur
        OrderValidator stockValidator   = new StockValidator();
        OrderValidator paymentValidator = new PaymentValidator();
        OrderValidator ageValidator     = new AgeRestrictionValidator();
        OrderValidator fraudValidator   = new FraudDetectionValidator();
        stockValidator.setNext(paymentValidator)
                      .setNext(ageValidator)
                      .setNext(fraudValidator);

        // Gecerli siparis
        Order order = new Order("ORD-TEST-001", customer);
        order.addItem(laptop, 1);
        OrderRequest validRequest = new OrderRequest(
                order, customer, "Kredi Karti", order.getTotalAmount());

        System.out.println("--- Gecerli siparis dogrulaniyor ---");
        stockValidator.validate(validRequest);
        System.out.println("Sonuc: " + (validRequest.isApproved() ? "ONAYLANDI" : "REDDEDILDI"));
        System.out.println("Mesajlar: " + validRequest.getValidationMessages());
    }

    // ─────────────────────────────────────────────────────────────────
    // 14. COMMAND PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateCommand(Customer customer, Product laptop, Product tshirt) {
        sep("14. COMMAND PATTERN - Sepet Islemleri (Undo/Redo)");
        Cart cart = new Cart(customer.getId());
        CartCommandManager manager = new CartCommandManager();

        System.out.println("--- Komutlar calistiriliyor ---");
        manager.executeCommand(new AddToCartCommand(cart, laptop, 1));
        manager.executeCommand(new AddToCartCommand(cart, tshirt, 2));
        manager.executeCommand(new ApplyCouponCommand(cart, "INDIRIM10", 10));

        System.out.println("\nSepet durumu: " + cart);

        System.out.println("\n--- Geri al (Undo) ---");
        manager.undo();

        System.out.println("\n--- Tekrar yap (Redo) ---");
        manager.redo();

        System.out.println("\n--- Komut gecmisi ---");
        manager.printHistory();
    }

    // ─────────────────────────────────────────────────────────────────
    // 15. ITERATOR PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateIterator(List<Product> products) {
        sep("15. ITERATOR PATTERN - Urun Katalogu Iterasyonu");
        ProductCatalog catalog = new ProductCatalog();
        products.forEach(catalog::addProduct);

        System.out.println("--- Tum urunler ---");
        ProductIterator allIter = catalog.createIterator();
        System.out.println(allIter.getIteratorType());
        while (allIter.hasNext()) {
            Product p = allIter.next();
            System.out.println("  " + p);
        }

        System.out.println("\n--- Elektronik kategorisi ---");
        ProductIterator catIter = catalog.createCategoryIterator("Elektronik");
        System.out.println(catIter.getIteratorType());
        while (catIter.hasNext()) {
            System.out.println("  " + catIter.next());
        }

        System.out.println("\n--- 0-10000 TL arasi (fiyata gore sirali) ---");
        ProductIterator priceIter = catalog.createPriceRangeIterator(0, 10000);
        System.out.println(priceIter.getIteratorType());
        while (priceIter.hasNext()) {
            Product p = priceIter.next();
            System.out.printf("  %-30s | %.2f TL%n", p.getName(), p.getPrice());
        }
    }

    // ─────────────────────────────────────────────────────────────────
    // 16. MEDIATOR PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateMediator() {
        sep("16. MEDIATOR PATTERN - Bilesen Koordinasyonu");
        ShoppingEventMediator mediator = new ShoppingEventMediator();
        CartColleague cart          = new CartColleague();
        StockColleague stock        = new StockColleague();
        NotificationColleague notif = new NotificationColleague();

        mediator.register(cart);
        mediator.register(stock);
        mediator.register(notif);

        System.out.println("\n--- Sepete urun ekleniyor ---");
        cart.addToCart("PHONE-001", "iPhone 15 Pro", 2);

        System.out.println("\n--- Checkout baslatiyor ---");
        cart.checkout("C001");
    }

    // ─────────────────────────────────────────────────────────────────
    // 17. MEMENTO PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateMemento(Product laptop, Product phone, Product tshirt) {
        sep("17. MEMENTO PATTERN - Sepet Durum Kaydi");
        MementoCart mCart   = new MementoCart();
        CartHistory history = new CartHistory();

        mCart.addItem(laptop, 1);
        history.saveState(mCart.save("Laptop eklendi"));

        mCart.addItem(phone, 1);
        history.saveState(mCart.save("Telefon eklendi"));

        mCart.displayCart();

        System.out.println("\n--- Geri al (telefonu cikar) ---");
        CartMemento prev = history.undo();
        if (prev != null) {
            mCart.restore(prev);
        }
        mCart.displayCart();

        System.out.println("\n--- Gecmis ---");
        history.printHistory();
    }

    // ─────────────────────────────────────────────────────────────────
    // 18. OBSERVER PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateObserver() {
        sep("18. OBSERVER PATTERN - Stok Bildirimleri");
        ProductStock stock = new ProductStock();

        CustomerStockObserver customerObs = new CustomerStockObserver("Ahmet", "ahmet@gmail.com");
        customerObs.watchProduct("HEADSET-001");
        stock.addObserver(customerObs);
        stock.addObserver(new AdminStockObserver("admin@shopease.com"));
        stock.addObserver(new AutoReorderObserver());

        stock.addProduct("HEADSET-001", "Sony WH-1000XM5", 20);

        System.out.println("\n--- Stok dusuyor ---");
        stock.updateStock("HEADSET-001", 4);

        System.out.println("\n--- Stok tukendi ---");
        stock.updateStock("HEADSET-001", 0);

        System.out.println("\n--- Stok yenilendi ---");
        stock.updateStock("HEADSET-001", 50);
    }

    // ─────────────────────────────────────────────────────────────────
    // 19. STATE PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateState() {
        sep("19. STATE PATTERN - Siparis Durum Makinesi");
        OrderContext order = new OrderContext("ORD-STATE-001");
        System.out.println("Baslangic durumu: " + order.getCurrentState().getStateName());

        System.out.println("\n--- Gecersiz islem dene (kargola) ---");
        order.ship();

        System.out.println("\n--- Onayla ---");
        order.confirm();

        System.out.println("\n--- Kargola ---");
        order.ship();

        System.out.println("\n--- Kargodayken iptal et (olmaz!) ---");
        order.cancel();

        System.out.println("\n--- Teslim et ---");
        order.deliver();

        System.out.println("\n--- Terminal durumda islem dene ---");
        order.confirm();

        System.out.println("\nDurum gecmisi: " + order.getStateHistory());
    }

    // ─────────────────────────────────────────────────────────────────
    // 20. STRATEGY PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateStrategy(Customer premium, Customer normal, Product laptop) {
        sep("20. STRATEGY PATTERN - Kargo Stratejisi");
        Order order = new Order("ORD-SHIP-001", premium);
        order.addItem(laptop, 1);

        ShippingContext ctx = new ShippingContext(new StandardShipping());

        System.out.println("--- Standard Kargo ---");
        double cost1 = ctx.calculateShippingCost(order);
        System.out.println("Teslimat: " + ctx.getDeliveryInfo() + " | Maliyet: " + cost1 + " TL");

        System.out.println("\n--- Ekspres Kargo ---");
        ctx.setStrategy(new ExpressShipping());
        double cost2 = ctx.calculateShippingCost(order);
        System.out.println("Teslimat: " + ctx.getDeliveryInfo() + " | Maliyet: " + cost2 + " TL");

        System.out.println("\n--- Ucretsiz Kargo (Premium) ---");
        ctx.setStrategy(new FreeShipping());
        double cost3 = ctx.calculateShippingCost(order);
        System.out.println("Teslimat: " + ctx.getDeliveryInfo() + " | Maliyet: " + cost3 + " TL");

        System.out.println("\n--- Onerilen strateji ---");
        ShippingStrategy recommended = ctx.recommendStrategy(premium, order);
        System.out.println("Premium musteri icin onerim: " + recommended.getStrategyName());
    }

    // ─────────────────────────────────────────────────────────────────
    // 21. TEMPLATE METHOD PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateTemplateMethod(Product laptop, Product phone, Product tshirt,
                                                   Customer c1, Customer c2) {
        sep("21. TEMPLATE METHOD PATTERN - Rapor Olusturma");

        System.out.println("--- Satis Raporu ---");
        SalesReport salesReport = new SalesReport();
        Order o1 = new Order("ORD-001", c1);
        o1.addItem(laptop, 1);
        Order o2 = new Order("ORD-002", c2);
        o2.addItem(tshirt, 2);
        salesReport.addOrder(o1);
        salesReport.addOrder(o2);
        salesReport.generateReport();

        System.out.println("\n--- Stok Raporu ---");
        InventoryReport invReport = new InventoryReport();
        invReport.addProduct(laptop);
        invReport.addProduct(phone);
        invReport.addProduct(tshirt);
        invReport.generateReport();

        System.out.println("\n--- Musteri Raporu ---");
        CustomerReport custReport = new CustomerReport();
        custReport.addCustomer(c1);
        custReport.addCustomer(c2);
        custReport.generateReport();
    }

    // ─────────────────────────────────────────────────────────────────
    // 22. VISITOR PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateVisitor() {
        sep("22. VISITOR PATTERN - Indirim ve Vergi Hesaplama");

        List<Visitable> products = List.of(
                new ElectronicsProduct("MacBook Pro 14", "Apple", 65000, 12, 1.6),
                new FoodProduct("Organik Elma", 50, true, false),
                new DigitalProduct("Adobe Photoshop", 500, "MULTI_USER"),
                new ClothingProduct("Nike Dri-FIT", "Nike", 350, "L", "Polyester")
        );

        System.out.println("--- Premium Musteri Indirimleri ---");
        DiscountVisitor discount = new DiscountVisitor(true);
        products.forEach(p -> p.accept(discount));
        discount.printDiscountSummary();

        System.out.println("\n--- KDV Hesaplama ---");
        TaxCalculatorVisitor tax = new TaxCalculatorVisitor();
        products.forEach(p -> p.accept(tax));
        tax.printTaxSummary();
    }

    // ─────────────────────────────────────────────────────────────────
    // 23. INTERPRETER PATTERN
    // ─────────────────────────────────────────────────────────────────
    private static void demonstrateInterpreter(List<Product> products) {
        sep("23. INTERPRETER PATTERN - Urun Arama Motoru");
        ProductSearchEngine engine = new ProductSearchEngine(products);

        System.out.println("--- Sorgu 1: Sadece Elektronik ---");
        engine.searchAndDisplay(new CategoryExpression("Elektronik"));

        System.out.println("\n--- Sorgu 2: Elektronik AND 0-50000 TL ---");
        engine.searchAndDisplay(new AndExpression(
                new CategoryExpression("Elektronik"),
                new PriceRangeExpression(0, 50000)
        ));

        System.out.println("\n--- Sorgu 3: Elektronik OR Giyim ---");
        engine.searchAndDisplay(new OrExpression(
                new CategoryExpression("Elektronik"),
                new CategoryExpression("Giyim")
        ));

        System.out.println("\n--- Sorgu 4: Apple markasindan stokta olanlar ---");
        engine.searchAndDisplay(new AndExpression(
                new BrandExpression("Apple"),
                new InStockExpression()
        ));

        System.out.println("\n--- Sorgu 5: Dijital OLMAYAN urunler ---");
        engine.searchAndDisplay(new NotExpression(
                new CategoryExpression("Kitap")
        ));
    }
}
