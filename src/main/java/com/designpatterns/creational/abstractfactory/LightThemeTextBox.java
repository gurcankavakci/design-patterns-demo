package com.designpatterns.creational.abstractfactory;

/**
 * Açık tema metin kutusu implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Açık arka plan rengi ve koyu metin rengiyle render edilir.
 * Light tema ailesiyle uyumludur.
 */
public class LightThemeTextBox implements TextBox {

    private String value = "";
    private String placeholder;

    /**
     * Light tema metin kutusu oluşturur.
     *
     * @param placeholder Değer girilmeden önce gösterilecek ipucu metni
     */
    public LightThemeTextBox(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * Metin kutusunu light tema stilleriyle render eder.
     * Açık arka plan rengi, ince kenarlık kullanılır.
     */
    @Override
    public void render() {
        String displayValue = value.isEmpty() ? "[" + placeholder + "]" : value;
        System.out.println("  [ LIGHT ] TextBox render | Değer: " + displayValue +
                " | Stil: Açık arka plan, İnce kenarlık, Koyu metin");
    }

    /**
     * Metin kutusunun değerini ayarlar ve girişi simüle eder.
     *
     * @param value Ayarlanacak metin değeri
     */
    @Override
    public void setValue(String value) {
        this.value = value;
        System.out.println("  [ LIGHT ] TextBox değer girildi: " + value + " (" + placeholder + " alanı)");
    }

    /**
     * @return Metin kutusu değeri
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Light tema CSS stilini döndürür.
     *
     * @return CSS stil string'i (beyaz arka plan, açık kenarlık, siyah metin)
     */
    @Override
    public String getStyle() {
        return "background: #FFFFFF; border: 1px solid #DDDDDD; color: #000000; " +
               "padding: 8px 12px; border-radius: 4px; font-size: 14px;";
    }

    /**
     * Placeholder metnini döndürür.
     *
     * @return İpucu metni
     */
    public String getPlaceholder() {
        return placeholder;
    }

    /**
     * Placeholder metnini ayarlar.
     *
     * @param placeholder Yeni ipucu metni
     */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public String toString() {
        return "LightThemeTextBox{placeholder='" + placeholder + "', value='" + value + "'}";
    }
}
