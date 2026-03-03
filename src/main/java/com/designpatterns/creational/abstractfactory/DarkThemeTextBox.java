package com.designpatterns.creational.abstractfactory;

/**
 * Koyu tema metin kutusu implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Koyu mavi arka plan rengi ve açık metin rengiyle render edilir.
 * Dark tema ailesiyle uyumludur.
 */
public class DarkThemeTextBox implements TextBox {

    private String value = "";
    private String placeholder;

    /**
     * Dark tema metin kutusu oluşturur.
     *
     * @param placeholder Değer girilmeden önce gösterilecek ipucu metni
     */
    public DarkThemeTextBox(String placeholder) {
        this.placeholder = placeholder;
    }

    /**
     * Metin kutusunu dark tema stilleriyle render eder.
     * Koyu mavi arka plan ve açık metin rengi kullanılır.
     */
    @Override
    public void render() {
        String displayValue = value.isEmpty() ? "[" + placeholder + "]" : value;
        System.out.println("  [ DARK  ] TextBox render | Değer: " + displayValue +
                " | Stil: Koyu arka plan (#16213E), Açık metin (#E0E0E0)");
    }

    /**
     * Metin kutusunun değerini ayarlar ve girişi simüle eder.
     *
     * @param value Ayarlanacak metin değeri
     */
    @Override
    public void setValue(String value) {
        this.value = value;
        System.out.println("  [ DARK  ] TextBox değer girildi: " + value + " (" + placeholder + " alanı)");
    }

    /**
     * @return Metin kutusu değeri
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Dark tema CSS stilini döndürür.
     *
     * @return CSS stil string'i (koyu arka plan, açık metin, koyu kenarlık)
     */
    @Override
    public String getStyle() {
        return "background: #16213E; border: 1px solid #444444; color: #E0E0E0; " +
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
        return "DarkThemeTextBox{placeholder='" + placeholder + "', value='" + value + "'}";
    }
}
