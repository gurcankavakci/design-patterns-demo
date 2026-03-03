package com.designpatterns.creational.abstractfactory;

/**
 * ShopEase UI istemci sınıfı.
 * Abstract Factory Pattern'de "Client" rolünü oynar.
 *
 * Bu sınıf yalnızca UIThemeFactory, Button, TextBox ve Checkbox
 * interface'leriyle çalışır. Hiçbir concrete sınıfı (LightThemeButton vb.)
 * doğrudan bilmez.
 *
 * TEMANIN KOLAY DEGİŞTİRİLMESİ:
 * ShopEaseUI'ya Light veya Dark factory geçilebilir.
 * Aynı renderXxx() metotları her iki temada da doğru çalışır.
 *
 * CLIENT KODU DEGİŞMEZ:
 * renderLoginPage() metodu, Light tema ile Dark tema için
 * tamamen aynı kodu çalıştırır. Sadece factory farklıdır.
 */
public class ShopEaseUI {

    // Client sadece interface'i tutar - concrete factory'yi bilmez
    private UIThemeFactory factory;

    /**
     * ShopEase UI oluşturur.
     * Geçilen factory, hangi tema bileşenlerinin kullanılacağını belirler.
     *
     * @param factory Kullanılacak UI tema fabrikası (Light veya Dark)
     */
    public ShopEaseUI(UIThemeFactory factory) {
        this.factory = factory;
        System.out.println("  [SHOPEASE UI] Kullanılan tema: " + factory.getThemeName());
    }

    /**
     * Giriş sayfasını render eder.
     * E-posta, şifre alanları ve "Beni Hatırla" seçeneği içerir.
     * Tema bağımsız çalışır - factory hangi temanın kullanılacağını belirler.
     */
    public void renderLoginPage() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║  ShopEase - Giriş Sayfası            ║");
        System.out.println("  ║  Tema: " + String.format("%-30s", factory.getThemeName()) + "║");
        System.out.println("  ╚══════════════════════════════════════╝");

        // Factory method çağrıları - hangi sınıfın oluşturulduğunu bilmiyoruz
        TextBox emailBox = factory.createTextBox("E-posta");
        TextBox passwordBox = factory.createTextBox("Şifre");
        Checkbox rememberMe = factory.createCheckbox("Beni Hatırla");
        Button loginButton = factory.createButton("Giriş Yap");

        System.out.println();
        System.out.println("  --- Bileşenler Render Ediliyor ---");
        emailBox.render();
        passwordBox.render();
        rememberMe.render();
        loginButton.render();

        // Simüle edilmiş etkileşimler
        System.out.println();
        System.out.println("  --- Kullanıcı Etkileşimi Simülasyonu ---");
        emailBox.setValue("kullanici@shopease.com");
        passwordBox.setValue("••••••••");
        rememberMe.toggle();
        loginButton.onClick();

        System.out.println();
    }

    /**
     * Ürün sayfasını render eder.
     * "Sepete Ekle", "Favorilere Ekle" butonları ve "Hızlı Teslimat" seçeneği içerir.
     */
    public void renderProductPage() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║  ShopEase - Ürün Sayfası             ║");
        System.out.println("  ║  Tema: " + String.format("%-30s", factory.getThemeName()) + "║");
        System.out.println("  ╚══════════════════════════════════════╝");

        Button addToCartButton = factory.createButton("Sepete Ekle");
        Button addToFavoritesButton = factory.createButton("Favorilere Ekle");
        Checkbox expressDelivery = factory.createCheckbox("Hızlı Teslimat (+15 TL)");

        System.out.println();
        System.out.println("  --- Bileşenler Render Ediliyor ---");
        addToCartButton.render();
        addToFavoritesButton.render();
        expressDelivery.render();

        // Simüle edilmiş etkileşimler
        System.out.println();
        System.out.println("  --- Kullanıcı Etkileşimi Simülasyonu ---");
        expressDelivery.toggle();
        expressDelivery.render();
        addToCartButton.onClick();
        addToFavoritesButton.onClick();

        System.out.println();
    }

    /**
     * Kullanıcı arayüzünün temasını değiştirir.
     * Yeni factory ile aynı renderXxx() metotları farklı görsel sunar.
     *
     * Bu metodun gücü: sadece factory değişiyor, client kodu değişmiyor.
     *
     * @param newFactory Yeni tema fabrikası
     */
    public void switchTheme(UIThemeFactory newFactory) {
        System.out.println();
        System.out.println("  [SHOPEASE UI] Tema değiştiriliyor...");
        System.out.println("  [SHOPEASE UI] Eski tema : " + this.factory.getThemeName());
        this.factory = newFactory;
        System.out.println("  [SHOPEASE UI] Yeni tema : " + this.factory.getThemeName());
        System.out.println("  [SHOPEASE UI] Tema değiştirildi: " + factory.getThemeName());
    }

    /**
     * Mevcut tema fabrikasını döndürür.
     *
     * @return Aktif UIThemeFactory
     */
    public UIThemeFactory getFactory() {
        return factory;
    }

    /**
     * Aktif temanın adını döndürür.
     *
     * @return Tema adı
     */
    public String getCurrentTheme() {
        return factory.getThemeName();
    }
}
