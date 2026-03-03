package com.designpatterns.creational.abstractfactory;

/**
 * Koyu tema UI fabrikası - Concrete Factory implementasyonu.
 * Abstract Factory Pattern'de "ConcreteFactory" rolünü oynar.
 *
 * Bu fabrika her zaman birbiriyle uyumlu Dark tema bileşenleri üretir:
 * - DarkThemeButton
 * - DarkThemeTextBox
 * - DarkThemeCheckbox
 *
 * OPEN/CLOSED PRINCIPLE GÖSTERIMI:
 * Yeni bir tema (örneğin "Solarized Theme") eklemek için:
 * 1. SolarizedThemeButton, SolarizedThemeTextBox, SolarizedThemeCheckbox yaz
 * 2. SolarizedThemeFactory yaz (UIThemeFactory implements)
 * Mevcut kod DEGISTIRILMEZ.
 */
public class DarkThemeFactory implements UIThemeFactory {

    /**
     * @return "Dark Theme"
     */
    @Override
    public String getThemeName() {
        return "Dark Theme";
    }

    /**
     * Dark tema butonu oluşturur.
     *
     * @param label Buton etiketi
     * @return Yeni DarkThemeButton nesnesi
     */
    @Override
    public Button createButton(String label) {
        System.out.println("  [DARK  FACTORY] DarkThemeButton oluşturuldu: '" + label + "'");
        return new DarkThemeButton(label);
    }

    /**
     * Dark tema metin kutusu oluşturur.
     *
     * @param placeholder İpucu metni
     * @return Yeni DarkThemeTextBox nesnesi
     */
    @Override
    public TextBox createTextBox(String placeholder) {
        System.out.println("  [DARK  FACTORY] DarkThemeTextBox oluşturuldu: '" + placeholder + "'");
        return new DarkThemeTextBox(placeholder);
    }

    /**
     * Dark tema onay kutusu oluşturur.
     *
     * @param label Onay kutusu etiketi
     * @return Yeni DarkThemeCheckbox nesnesi
     */
    @Override
    public Checkbox createCheckbox(String label) {
        System.out.println("  [DARK  FACTORY] DarkThemeCheckbox oluşturuldu: '" + label + "'");
        return new DarkThemeCheckbox(label);
    }

    @Override
    public String toString() {
        return "DarkThemeFactory{theme='Dark Theme'}";
    }
}
