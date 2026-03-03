package com.designpatterns.behavioral.mediator;

/**
 * MEDIATOR PATTERN - Arabulucu Deseni
 *
 * <p><b>TANIM:</b> Nesnelerin birbirleriyle nasıl etkileşeceğini kapsüller.
 * Nesneler arası doğrudan iletişimi önler; iletişim mediator üzerinden geçer.
 *
 * <p><b>PROBLEM:</b> 5 bileşen (Sepet, Stok, Fiyat, Bildirim, Öneri) birbirleriyle konuşuyor.
 * Her biri diğerlerini doğrudan çağırsa: N*(N-1) = 20 bağlantı! Spaghetti Code!
 * Mediator ile: Her bileşen sadece Mediator'ı bilir → 5 bağlantı.
 *
 * <pre>
 * UML DIAGRAM:
 *                ┌──────────────────────────┐
 *                │    ShoppingEventMediator  │
 *                └──────────┬───────────────┘
 *         ┌─────────────────┼────────────────┐
 *         ↓                 ↓                ↓
 *    CartColleague    StockColleague   NotificationColleague
 *    (Colleague)      (Colleague)      (Colleague)
 * </pre>
 *
 * <p><b>OBSERVER vs MEDIATOR:</b>
 * <ul>
 *   <li>Observer: Subject → Observer'lara tek yönlü bildirim</li>
 *   <li>Mediator: Merkezi koordinasyon; her bileşen her yönde iletişim kurabilir</li>
 * </ul>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Hava trafik kontrolörü. Uçaklar birbirleriyle konuşmaz, hepsi ATC'ye rapor eder.
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Bileşenler arası coupling azalır (loose coupling)</li>
 *   <li>Koordinasyon mantığı tek yerde</li>
 *   <li>Bileşenler bağımsız geliştirilebilir</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Mediator God Object'e dönüşebilir</li>
 *   <li>Tek hata noktası olabilir</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface ShoppingMediator {

    /**
     * Bir bileşenden gelen olayı diğer bileşenlere iletir.
     *
     * @param sender Olayı gönderen bileşen
     * @param event  Olay adı (ör: "PRODUCT_ADDED", "LOW_STOCK_ALERT")
     * @param data   Olayla birlikte gönderilen veri
     */
    void notify(ShoppingColleague sender, String event, Object data);

    /**
     * Bir bileşeni mediator'a kaydeder.
     *
     * @param colleague Kaydedilecek bileşen
     */
    void register(ShoppingColleague colleague);
}
