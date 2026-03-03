package com.designpatterns.creational.abstractfactory;

/**
 * Onay kutusu UI bileşeni interface'i.
 * Abstract Factory Pattern'de "AbstractProduct" rolünü oynar.
 *
 * Her tema bu interface'i implement eden kendi Checkbox sınıfını sağlar.
 * İşaretli/işaretsiz durumu toggle() ile değiştirilebilir.
 *
 * ---
 *
 * Checkbox UI component interface.
 * Plays the "AbstractProduct" role in the Abstract Factory Pattern.
 */
public interface Checkbox {

    /**
     * Onay kutusunu ekrana render eder.
     * İşaretli duruma göre farklı simge gösterilir (☐ veya ☑).
     */
    void render();

    /**
     * Onay kutusunun durumunu tersine çevirir.
     * İşaretli ise işaretsiz, işaretsiz ise işaretli yapar.
     */
    void toggle();

    /**
     * Onay kutusunun işaretli olup olmadığını döndürür.
     *
     * @return İşaretli ise true, işaretsiz ise false
     */
    boolean isChecked();

    /**
     * Onay kutusunun CSS stil bilgisini döndürür.
     *
     * @return CSS stil string'i
     */
    String getStyle();
}
