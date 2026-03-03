package com.designpatterns.behavioral.observer;

/**
 * OBSERVER PATTERN - Gozlemci Deseni
 *
 * TANIM: Nesneler arasinda bire-cok (one-to-many) bagimlilik tanimlar.
 * Bir nesne degistiginde, bagimli tum nesneler otomatik bildirilir.
 *
 * PROBLEM: Stok degisince musteri, admin, otomatik siparis sistemi bilgilenmeli.
 * Stok sinifina bu servisleri baglarsak: tight coupling, yeni servis eklemek zor.
 *
 * PUBLISHER-SUBSCRIBER:
 * Subject = Publisher (stok degisimini yayinlar)
 * Observer = Subscriber (degisimi dinler ve tepki verir)
 *
 * UML DIAGRAM:
 * +------------------------+      +--------------------------+
 * |  <<interface>>         |      |  <<interface>>           |
 * |  StockSubject          |1----*|  StockObserver           |
 * +------------------------+      +--------------------------+
 * |+addObserver(observer)  |      |+onStockChanged(event)    |
 * |+removeObserver(observer|      |+getObserverName()        |
 * |+notifyObservers(event) |      +------------+-------------+
 * +----------+-------------+                   | implements
 *            | implements              +--------+-----------------------+
 *     ProductStock              CustomerObs  AdminObs  AutoReorderObs
 *
 * EVENT-DRIVEN MIMARISI:
 * Modern sistemlerde Observer -> Event Bus, Kafka, RabbitMQ ile gelistirilir.
 *
 * AVANTAJLAR:
 * + Loose coupling (Subject Observer'i bilmez, sadece interface)
 * + Runtime'da Observer ekleme/cikarma
 * + Open/Closed Principle
 *
 * DEZAVANTAJLAR:
 * - Bildirim sirasi belirsiz
 * - Memory leak (Observer remove edilmezse)
 * - Cascade guncellemeler
 */
public interface StockObserver {

    void onStockChanged(StockEvent event);

    String getObserverName();
}
