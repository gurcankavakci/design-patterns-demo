package com.designpatterns.creational.abstractfactory;

/**
 * Metin kutusu UI bileşeni interface'i.
 * Abstract Factory Pattern'de "AbstractProduct" rolünü oynar.
 *
 * Her tema bu interface'i implement eden kendi TextBox sınıfını sağlar.
 * Placeholder metin, kullanıcı için ipucu görevini görür.
 *
 * ---
 *
 * Text box UI component interface.
 * Plays the "AbstractProduct" role in the Abstract Factory Pattern.
 */
public interface TextBox {

    /**
     * Metin kutusunu ekrana render eder.
     * Her tema farklı görsel stil ile render eder.
     */
    void render();

    /**
     * Metin kutusunun değerini ayarlar.
     * Kullanıcı girişini simüle eder.
     *
     * @param value Ayarlanacak metin değeri
     */
    void setValue(String value);

    /**
     * Metin kutusunun mevcut değerini döndürür.
     *
     * @return Metin kutusu değeri
     */
    String getValue();

    /**
     * Metin kutusunun CSS stil bilgisini döndürür.
     *
     * @return CSS stil string'i
     */
    String getStyle();
}
