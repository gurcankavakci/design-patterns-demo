package com.designpatterns.behavioral.strategy;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Order;

/**
 * STRATEGY PATTERN - Strateji Deseni
 *
 * TANIM: Bir algoritma ailesini tanimlar, her birini kapsullar ve
 * birbirinin yerine gecebilir hale getirir.
 *
 * PROBLEM: Farkli kargo yontemleri farkli maliyet ve sure hesaplar.
 * if-else ile: her yeni yontem kod degistirir (OCP ihlali).
 * Strategy ile: yeni yontem = yeni sinif (OCP uyumlu).
 *
 * UML DIAGRAM:
 * +-------------------------+      +--------------------------+
 * |   ShippingContext        |----->|   <<interface>>          |
 * |   - strategy             |      |   ShippingStrategy       |
 * |   + setStrategy(s)       |      +--------------------------+
 * |   + calculateCost(order) |      |+calculateCost(order)     |
 * +-------------------------+      |+getDeliveryTime()        |
 *                                  |+getStrategyName()        |
 *                                  |+isAvailableFor(customer) |
 *                                  +-------------+------------+
 *                                                |
 *                         +--------------------+-+------------------+
 *                    StandardShipping  ExpressShipping  SameDayShipping  FreeShipping
 *
 * TEMPLATE METHOD vs STRATEGY:
 * Template Method: Inheritance - iskelet sabit, adimlar degisir
 * Strategy: Composition - tum algoritma degisir, runtime'da
 *
 * GERCEK HAYAT ANALOGISI:
 * Google Maps rota secimi: Araba, bisiklet, toplu tasima.
 * Ayni hedefe farkli yollar. Runtime'da degistirilebilir.
 *
 * AVANTAJLAR:
 * + Open/Closed Principle
 * + if-else/switch yerine composition
 * + Her strateji bagimsiz test edilebilir
 * + Runtime'da degistirilebilir
 *
 * DEZAVANTAJLAR:
 * - Client tum stratejileri bilmeli
 * - Az strateji icin overkill
 */
public interface ShippingStrategy {

    double calculateCost(Order order);

    String getDeliveryTime();

    String getStrategyName();

    String getDescription();

    boolean isAvailableFor(Customer customer);
}
