package com.designpatterns.creational.abstractfactory;

/**
 * Açık tema onay kutusu implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * İşaretli durum Unicode semboller (☐/☑) ile gösterilir.
 * Light tema ailesiyle uyumludur.
 */
public class LightThemeCheckbox implements Checkbox {

    private boolean checked = false;
    private String label;

    /**
     * Light tema onay kutusu oluşturur.
     *
     * @param label Onay kutusunun yanında gösterilecek etiket metni
     */
    public LightThemeCheckbox(String label) {
        this.label = label;
    }

    /**
     * Onay kutusunu light tema stilleriyle render eder.
     * İşaretli/işaretsiz duruma göre ☑ veya ☐ sembolü kullanılır.
     */
    @Override
    public void render() {
        String checkSymbol = checked ? "☑" : "☐";
        System.out.println("  [ LIGHT ] Checkbox: " + checkSymbol + " " + label +
                " | Stil: Açık arka plan, Gri onay işareti");
    }

    /**
     * Onay kutusunun işaretli durumunu tersine çevirir.
     */
    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("  [ LIGHT ] Checkbox durumu değişti: " + label +
                " → " + (checked ? "İŞARETLİ" : "İŞARETSİZ"));
    }

    /**
     * @return İşaretli ise true
     */
    @Override
    public boolean isChecked() {
        return checked;
    }

    /**
     * Light tema CSS stilini döndürür.
     *
     * @return CSS stil string'i
     */
    @Override
    public String getStyle() {
        return "accent-color: #555555; background: #FFFFFF; border: 1px solid #CCCCCC; " +
               "width: 16px; height: 16px; cursor: pointer;";
    }

    /**
     * Onay kutusunun etiket metnini döndürür.
     *
     * @return Etiket metni
     */
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "LightThemeCheckbox{label='" + label + "', checked=" + checked + "}";
    }
}
