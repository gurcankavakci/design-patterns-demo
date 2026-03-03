package com.designpatterns.creational.abstractfactory;

/**
 * Buton UI bileşeni interface'i.
 * Abstract Factory Pattern'de "AbstractProduct" rolünü oynar.
 *
 * Her tema (Light/Dark) bu interface'i implement eden kendi buton sınıfını sağlar.
 * Client kodu yalnızca bu interface ile çalışır; hangi temanın butonu
 * kullanıldığını bilmez.
 *
 * ---
 *
 * Button UI component interface.
 * Plays the "AbstractProduct" role in the Abstract Factory Pattern.
 */
public interface Button {

    /**
     * Butonu ekrana render eder.
     * Her tema farklı görsel stil ile render eder.
     */
    void render();

    /**
     * Buton tıklama olayını işler.
     * Tıklama simüle edilir ve ilgili mesaj yazdırılır.
     */
    void onClick();

    /**
     * Butonun etiket metnini döndürür.
     *
     * @return Buton etiketi
     */
    String getLabel();

    /**
     * Butonun CSS stil bilgisini döndürür.
     * Her tema farklı renk ve border değerleri döndürür.
     *
     * @return CSS stil string'i
     */
    String getStyle();
}
