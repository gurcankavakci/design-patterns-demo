package com.designpatterns.creational.abstractfactory;

/**
 * Koyu tema buton implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * Koyu arka plan, gri kenarlık ve açık metin rengiyle render edilir.
 * Dark tema ailesiyle (DarkThemeTextBox, DarkThemeCheckbox) uyumludur.
 *
 * ABSTRACT FACTORY'NİN DEĞERI:
 * LightThemeButton yerine DarkThemeButton kullanmak için sadece
 * factory nesnesini değiştirmek yeterlidir. Bileşen kodunu değiştirmek gerekmez.
 */
public class DarkThemeButton implements Button {

    private String label;
    private boolean enabled = true;

    /**
     * Dark tema butonu oluşturur.
     *
     * @param label Buton üzerinde gösterilecek etiket metni
     */
    public DarkThemeButton(String label) {
        this.label = label;
    }

    /**
     * Butonu dark tema stilleriyle render eder.
     * Koyu arka plan, orta gri kenarlık ve açık metin rengi kullanılır.
     */
    @Override
    public void render() {
        System.out.println("  [ DARK  ] Buton render: " + label +
                " | Stil: Koyu arka plan (#1A1A2E), Açık metin (#E0E0E0), Gri kenarlık (#444)" +
                " | Durum: " + (enabled ? "Aktif" : "Pasif"));
    }

    /**
     * Buton tıklama olayını simüle eder.
     * Pasif butonlar tıklanamaz.
     */
    @Override
    public void onClick() {
        if (!enabled) {
            System.out.println("  [ DARK  ] Buton pasif durumda, tıklama işlemi yapılamaz: " + label);
            return;
        }
        System.out.println("  [ DARK  ] Buton tıklandı: " + label);
    }

    /**
     * @return Buton etiketi
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Dark tema CSS stilini döndürür.
     *
     * @return CSS stil string'i (koyu arka plan, açık metin)
     */
    @Override
    public String getStyle() {
        return "background: #1A1A2E; border: 1px solid #444444; color: #E0E0E0; " +
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
        return "DarkThemeButton{label='" + label + "', enabled=" + enabled + "}";
    }
}
