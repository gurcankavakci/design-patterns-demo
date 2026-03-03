package com.designpatterns.creational.abstractfactory;

/**
 * Açık tema buton implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Beyaz arka plan, gri kenarlık ve koyu metin rengiyle render edilir.
 * Light tema ailesiyle (LightThemeTextBox, LightThemeCheckbox) uyumludur.
 */
public class LightThemeButton implements Button {

    private String label;
    private boolean enabled = true;

    /**
     * Light tema butonu oluşturur.
     *
     * @param label Buton üzerinde gösterilecek etiket metni
     */
    public LightThemeButton(String label) {
        this.label = label;
    }

    /**
     * Butonu light tema stilleriyle render eder.
     * Beyaz arka plan, gri kenarlık ve koyu metin rengi kullanılır.
     */
    @Override
    public void render() {
        System.out.println("  [ LIGHT ] Buton render: " + label +
                " | Stil: Beyaz arka plan, Gri kenarlık, Koyu metin" +
                " | Durum: " + (enabled ? "Aktif" : "Pasif"));
    }

    /**
     * Buton tıklama olayını simüle eder.
     * Pasif butonlar tıklanamaz.
     */
    @Override
    public void onClick() {
        if (!enabled) {
            System.out.println("  [ LIGHT ] Buton pasif durumda, tıklama işlemi yapılamaz: " + label);
            return;
        }
        System.out.println("  [ LIGHT ] Buton tıklandı: " + label);
    }

    /**
     * @return Buton etiketi
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Light tema CSS stilini döndürür.
     *
     * @return CSS stil string'i (beyaz arka plan, gri kenarlık, koyu metin)
     */
    @Override
    public String getStyle() {
        return "background: #FFFFFF; border: 1px solid #CCCCCC; color: #333333; " +
               "padding: 8px 16px; border-radius: 4px; cursor: pointer;";
    }

    /**
     * Butonun aktif/pasif durumunu ayarlar.
     *
     * @param enabled true ise aktif, false ise pasif
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Butonun aktif olup olmadığını döndürür.
     *
     * @return Aktif ise true
     */
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "LightThemeButton{label='" + label + "', enabled=" + enabled + "}";
    }
}
