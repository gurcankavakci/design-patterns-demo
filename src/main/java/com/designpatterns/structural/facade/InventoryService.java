package com.designpatterns.structural.facade;

import java.util.HashMap;
import java.util.Map;

/**
 * FACADE PATTERN - Cephe Deseni
 *
 * <p><b>TANIM:</b> Bir alt sistemin karmaşık interface'ine daha basit bir
 * interface sağlar. Alt sistemin kullanımını kolaylaştırır.
 *
 * <p><b>PROBLEM:</b> Sipariş vermek için birçok servisle koordinasyon gerekiyor:
 * Stok kontrolü, ödeme işlemi, kargo oluşturma, bildirim gönderme.
 * Client bu karmaşıklığı bilmek zorunda değil.
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Restoranda garson = Facade. Garson'a "Biftek istiyorum" dediniz.
 * Garson mutfakla, kasayla, garderob ile koordine eder. Siz detay bilmezsiniz.
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Facade (CheckoutFacade): basit interface sunar</li>
 *   <li>Subsystem classes (InventoryService, PaymentService vb.): karmaşık iş yapar</li>
 *   <li>Client: sadece Facade ile konuşur</li>
 * </ul>
 *
 * <pre>
 * UML DIAGRAM:
 * ┌──────────────────────────────────────────────────────┐
 * │                    CLIENT                             │
 * └──────────────────────┬───────────────────────────────┘
 *                        │ uses (only this)
 *               ┌────────┴──────────┐
 *               │  CheckoutFacade   │
 *               └────────┬──────────┘
 *              ┌─────────┼──────────┬──────────────┐
 *              ↓         ↓          ↓               ↓
 *    ┌─────────────┐ ┌───────┐ ┌─────────┐ ┌──────────────┐
 *    │InventoryServ│ │Payment│ │Shipping │ │Notification  │
 *    │             │ │Service│ │Service  │ │Service       │
 *    └─────────────┘ └───────┘ └─────────┘ └──────────────┘
 * </pre>
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Alt sistem karmaşıklığını gizler</li>
 *   <li>Client-alt sistem coupling'i azaltır</li>
 *   <li>Katmanlı mimariyi destekler</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>God Object anti-pattern'e dönüşebilir</li>
 *   <li>Facade değişince çok etkilenen yer olabilir</li>
 * </ul>
 *
 * <p><b>NE ZAMAN KULLANILIR:</b>
 * <ul>
 *   <li>Karmaşık alt sisteme basit interface gerektiğinde</li>
 *   <li>Alt sistemi katmanlara ayırmak için</li>
 *   <li>Kütüphane/framework'lerin entry point'i</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class InventoryService {

    /** Ürün stok takip tablosu: productId -> mevcut stok */
    private final Map<String, Integer> inventory = new HashMap<>();

    /**
     * Inventory service'i oluşturur ve başlangıç stokunu yükler.
     */
    public InventoryService() {
        // Başlangıç stok verileri
        inventory.put("LAPTOP-001",   50);
        inventory.put("PHONE-001",   100);
        inventory.put("HEADSET-001",  30);
        inventory.put("TABLET-001",   25);
        inventory.put("WATCH-001",    60);
        System.out.println("  [InventoryService] Stok servisi baslatildi. " +
                           inventory.size() + " urun takip ediliyor.");
    }

    /**
     * Belirtilen ürünün istenen miktarda mevcut olup olmadığını kontrol eder.
     *
     * @param productId Kontrol edilecek ürün kimliği
     * @param quantity  İstenen miktar
     * @return true yeterli stok varsa
     */
    public boolean checkAvailability(String productId, int quantity) {
        int available = inventory.getOrDefault(productId, 0);
        System.out.printf("  [Inventory] Stok kontrolü: %s | İstenen: %d | Mevcut: %d | Sonuç: %s%n",
                          productId, quantity, available, available >= quantity ? "YETERLI" : "YETERSIZ");
        return available >= quantity;
    }

    /**
     * Belirtilen ürün için stok rezervasyonu yapar (sipariş için ayırır).
     *
     * @param productId Ürün kimliği
     * @param quantity  Rezerve edilecek miktar
     */
    public void reserveStock(String productId, int quantity) {
        inventory.merge(productId, -quantity, Integer::sum);
        System.out.printf("  [Inventory] Stok rezerve edildi: %s x%d | Kalan stok: %d%n",
                          productId, quantity, inventory.getOrDefault(productId, 0));
    }

    /**
     * Rezerve edilmiş stoğu serbest bırakır (sipariş iptali durumunda).
     *
     * @param productId Ürün kimliği
     * @param quantity  Serbest bırakılacak miktar
     */
    public void releaseStock(String productId, int quantity) {
        inventory.merge(productId, quantity, Integer::sum);
        System.out.printf("  [Inventory] Stok serbest bırakıldı: %s x%d | Yeni stok: %d%n",
                          productId, quantity, inventory.getOrDefault(productId, 0));
    }

    /**
     * Belirtilen ürünün mevcut stok miktarını döner.
     *
     * @param productId Ürün kimliği
     * @return Mevcut stok adedi (ürün yoksa 0)
     */
    public int getAvailableStock(String productId) {
        return inventory.getOrDefault(productId, 0);
    }
}
