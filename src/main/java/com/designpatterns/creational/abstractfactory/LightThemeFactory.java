package com.designpatterns.creational.abstractfactory;

/**
 * Açık tema UI fabrikası - Concrete Factory implementasyonu.
 * Abstract Factory Pattern'de "ConcreteFactory" rolünü oynar.
 *
 * Bu fabrika her zaman birbiriyle uyumlu Light tema bileşenleri üretir:
 * - LightThemeButton
 * - LightThemeTextBox
 * - LightThemeCheckbox
 *
 * Client kodu bu sınıfı doğrudan bilmez; UIThemeFactory interface'i üzerinden kullanır.
 * Tema değiştirmek için sadece factory nesnesini değiştirmek yeterlidir.
 */
public class LightThemeFactory implements UIThemeFactory {

    /**
     * @return "Light Theme"
     */
    @Override
    public String getThemeName() {
        return "Light Theme";
    }

    /**
     * Light tema butonu oluşturur.
     *
     * @param label Buton etiketi
     * @return Yeni LightThemeButton nesnesi
     */
    @Override
    public Button createButton(String label) {
        System.out.println("  [LIGHT FACTORY] LightThemeButton oluşturuldu: '" + label + "'");
        return new LightThemeButton(label);
    }

    /**
     * Light tema metin kutusu oluşturur.
     *
     * @param placeholder İpucu metni
     * @return Yeni LightThemeTextBox nesnesi
     */
    @Override
    public TextBox createTextBox(String placeholder) {
        System.out.println("  [LIGHT FACTORY] LightThemeTextBox oluşturuldu: '" + placeholder + "'");
        return new LightThemeTextBox(placeholder);
    }

    /**
     * Light tema onay kutusu oluşturur.
     *
     * @param label Onay kutusu etiketi
     * @return Yeni LightThemeCheckbox nesnesi
     */
    @Override
    public Checkbox createCheckbox(String label) {
        System.out.println("  [LIGHT FACTORY] LightThemeCheckbox oluşturuldu: '" + label + "'");
        return new LightThemeCheckbox(label);
    }

    @Override
    public String toString() {
        return "LightThemeFactory{theme='Light Theme'}";
    }
}
