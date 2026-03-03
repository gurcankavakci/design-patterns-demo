package com.designpatterns.creational.abstractfactory;

/**
 * Koyu tema onay kutusu implementasyonu.
 * Abstract Factory Pattern'de "ConcreteProduct" rolünü oynar.
 *
 * İşaretli durum Unicode semboller (☐/☑) ile gösterilir, koyu tema renkleriyle.
 * Dark tema ailesiyle uyumludur.
 */
public class DarkThemeCheckbox implements Checkbox {

    private boolean checked = false;
    private String label;

    /**
     * Dark tema onay kutusu oluşturur.
     *
     * @param label Onay kutusunun yanında gösterilecek etiket metni
     */
    public DarkThemeCheckbox(String label) {
        this.label = label;
    }

    /**
     * Onay kutusunu dark tema stilleriyle render eder.
     * İşaretli/işaretsiz duruma göre ☑ veya ☐ sembolü kullanılır.
     * Koyu tema renkleri uygulanır.
     */
    @Override
    public void render() {
        String checkSymbol = checked ? "☑" : "☐";
        System.out.println("  [ DARK  ] Checkbox: " + checkSymbol + " " + label +
                " | Stil: Koyu arka plan (#1A1A2E), Açık onay işareti (#E0E0E0)");
    }

    /**
     * Onay kutusunun işaretli durumunu tersine çevirir.
     */
    @Override
    public void toggle() {
        checked = !checked;
        System.out.println("  [ DARK  ] Checkbox durumu değişti: " + label +
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
     * Dark tema CSS stilini döndürür.
     *
     * @return CSS stil string'i (koyu arka plan, açık onay işareti)
     */
    @Override
    public String getStyle() {
        return "accent-color: #E0E0E0; background: #1A1A2E; border: 1px solid #555555; " +
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
        return "DarkThemeCheckbox{label='" + label + "', checked=" + checked + "}";
    }
}
