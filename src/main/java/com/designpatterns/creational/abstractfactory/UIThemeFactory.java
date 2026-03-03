package com.designpatterns.creational.abstractfactory;

/**
 * ABSTRACT FACTORY PATTERN - Soyut Fabrika Deseni
 *
 * TANIM: İlgili veya bağımlı nesnelerin ailelerini, somut sınıflarını
 * belirtmeden oluşturmak için bir interface sağlar.
 *
 * FACTORY METHOD vs ABSTRACT FACTORY:
 * Factory Method: Tek bir ürün oluşturur, inheritance kullanır
 * Abstract Factory: Birbiriyle ilişkili ürün AİLESİ oluşturur, composition kullanır
 *
 * PROBLEM: Farklı UI temalarında (Light/Dark) tutarlı bileşenler istiyoruz.
 * Light tema butonuyla Dark tema text box'ı karıştırmak istemiyoruz.
 *
 * ROLLER:
 * - AbstractFactory (UIThemeFactory): tüm ürün tiplerini oluşturma interface'i
 * - ConcreteFactory (LightThemeFactory, DarkThemeFactory): gerçek fabrikalar
 * - AbstractProduct (Button, TextBox, Checkbox): ürün interface'leri
 * - ConcreteProduct: gerçek ürünler
 * - Client (ShopEaseUI): sadece interface'lerle çalışır
 *
 * URUN AİLESİ KAVRAMI:
 * Light Tema ailesi: LightButton + LightTextBox + LightCheckbox (hepsi uyumlu)
 * Dark Tema ailesi: DarkButton + DarkTextBox + DarkCheckbox (hepsi uyumlu)
 *
 * UML DIAGRAM:
 * ┌─────────────────────┐      ┌────────────────────────────────┐
 * │  <<interface>>      │      │      <<interface>>              │
 * │  UIThemeFactory     │      │      Button / TextBox / Checkbox│
 * ├─────────────────────┤      └──────────┬─────────────────────┘
 * │+createButton()      │                 │ implements
 * │+createTextBox()     │      ┌──────────┴──────────────────┐
 * │+createCheckbox()    │      │ LightThemeButton             │
 * └──────┬──────────────┘      │ DarkThemeButton              │
 *        │implements           └─────────────────────────────-┘
 * ┌──────┴──────────────┐
 * │LightThemeFactory    │
 * │DarkThemeFactory     │
 * └─────────────────────┘
 *
 * GERCEK HAYAT ANALOGISI:
 * IKEA mağazasını düşünün. "Modern" koleksiyondan koltuk, masa ve sandalye
 * alırsanız hepsi birbiriyle uyumludur. "Klasik" koleksiyondan alırsanız
 * da birbiriyle uyumludur. Ancak Modern koltuk + Klasik masa pek uymaz.
 * Aynı şekilde, LightThemeFactory her zaman birbiriyle uyumlu bileşenler üretir.
 *
 * AVANTAJLAR:
 * + Ürün ailelerinin uyumluluğunu garanti eder
 * + Somut sınıfları client'tan gizler
 * + Single Responsibility and Open/Closed Principle
 * + Tema değiştirmek tek satır kod değişikliği gerektirir
 *
 * DEZAVANTAJLAR:
 * - Yeni ürün tipi eklemek tüm factory'lerin değiştirilmesini gerektirir
 *   (Örneğin: yeni "Dropdown" interface'i eklemek LightThemeFactory ve
 *    DarkThemeFactory'de yeni metot gerektirir)
 * - Kod karmaşıklığı artar
 *
 * NE ZAMAN KULLANILIR:
 * - Birbiriyle çalışması gereken nesneler ailesi oluşturmak için
 * - Cross-platform UI kütüphaneleri (Windows/Mac/Linux buttonları)
 * - Database abstraction (MySQL/PostgreSQL/Oracle)
 * - Farklı temalar veya skinler
 * - Bir yapılandırmaya bağlı nesne aileleri
 *
 * ---
 *
 * ABSTRACT FACTORY PATTERN - Abstract Factory Pattern
 *
 * DEFINITION: Provides an interface for creating families of related or
 * dependent objects without specifying their concrete classes.
 *
 * PROBLEM: We want consistent components across different UI themes (Light/Dark).
 * We don't want to mix a Light theme button with a Dark theme textbox.
 *
 * ROLES:
 * - AbstractFactory (this interface): interface for creating all product types
 * - ConcreteFactory: actual factories (Light/Dark)
 * - AbstractProduct: product interfaces (Button, TextBox, Checkbox)
 * - ConcreteProduct: real products
 * - Client (ShopEaseUI): works only with interfaces
 */
public interface UIThemeFactory {

    /**
     * Temaya uygun bir buton bileşeni oluşturur.
     * Light factory → LightThemeButton, Dark factory → DarkThemeButton
     *
     * @param label Buton etiketi
     * @return Temaya uygun Button nesnesi
     */
    Button createButton(String label);

    /**
     * Temaya uygun bir metin kutusu bileşeni oluşturur.
     * Light factory → LightThemeTextBox, Dark factory → DarkThemeTextBox
     *
     * @param placeholder İpucu metni
     * @return Temaya uygun TextBox nesnesi
     */
    TextBox createTextBox(String placeholder);

    /**
     * Temaya uygun bir onay kutusu bileşeni oluşturur.
     * Light factory → LightThemeCheckbox, Dark factory → DarkThemeCheckbox
     *
     * @param label Onay kutusu etiketi
     * @return Temaya uygun Checkbox nesnesi
     */
    Checkbox createCheckbox(String label);

    /**
     * Fabrikanın oluşturduğu tema adını döndürür.
     *
     * @return Tema adı (örn: "Light Theme", "Dark Theme")
     */
    String getThemeName();
}
