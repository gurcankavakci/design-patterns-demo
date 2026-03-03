package com.designpatterns.behavioral.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * MEMENTO PATTERN - Anı Deseni
 *
 * <p><b>TANIM:</b> Bir nesnenin iç durumunu dışarıya sızdırmadan yakalayıp saklar
 * ve bu durumu daha sonra geri yüklemeye izin verir.
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Originator (MementoCart): durumu yaratır ve geri yükler</li>
 *   <li>Memento (CartMemento): durumu saklar - IMMUTABLE, caretaker içine bakamaz</li>
 *   <li>Caretaker (CartHistory): Memento'ları yönetir, içine bakmaz</li>
 * </ul>
 *
 * <p><b>ENCAPSULATION KORUNMASI:</b><br>
 * CartHistory sadece CartMemento'yu tutar ama içine bakamaz (opaque).
 * Sadece Originator durumu okuyup yazabilir.
 *
 * <p><b>COMMAND vs MEMENTO:</b>
 * <ul>
 *   <li>Command: Undo = ters operasyonu çalıştır (eklendiyse çıkar)</li>
 *   <li>Memento: Undo = önceki state'i restore et (snapshot)</li>
 * </ul>
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Video oyunlarında save point. Oyunun durumunu kaydedersiniz,
 * öldüğünüzde save point'e dönersiniz (restore).
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Encapsulation ihlali olmadan snapshot</li>
 *   <li>Originator kodu basit kalır</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Çok fazla Memento = yüksek bellek</li>
 *   <li>Caretaker ne zaman sileceğini bilmeli</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public final class CartMemento {

    private final Map<String, Integer> items;
    private final double totalAmount;
    private final LocalDateTime savedAt;
    private final String description;

    /**
     * Sepet durumunun anlık görüntüsünü oluşturur.
     * Defensive copy ile dış değişikliklerden korunur.
     *
     * @param items       Sepet ürünleri (productId -> miktar)
     * @param totalAmount Toplam tutar
     * @param description Bu snapshot'ın açıklaması
     */
    public CartMemento(Map<String, Integer> items, double totalAmount, String description) {
        this.items = new HashMap<>(items); // defensive copy - dış değişiklikten koruma
        this.totalAmount = totalAmount;
        this.savedAt = LocalDateTime.now();
        this.description = description;
    }

    // Sadece getter'lar - setter yok (immutable)

    /**
     * @return Değiştirilemez ürün haritası
     */
    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double getTotalAmount() { return totalAmount; }

    public LocalDateTime getSavedAt() { return savedAt; }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %.2f TL | %d ürün",
                savedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                description,
                totalAmount,
                items.size());
    }
}
