package com.designpatterns.behavioral.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * CARETAKER - Geçmiş yöneticisi.
 *
 * <p>Memento'ları saklar ama içine BAKMAZ (opaque box).
 * Undo/Redo işlemleri için iki ayrı yığın (stack) kullanır:
 * <ul>
 *   <li>undoStack: Geri alınabilir durumlar (en üstte en son kayıt)</li>
 *   <li>redoStack: Yeniden yapılabilir durumlar</li>
 * </ul>
 *
 * <p>Yeni bir kayıt eklendiğinde redoStack temizlenir
 * (doğrusal geçmiş korunur).
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class CartHistory {

    private final Deque<CartMemento> undoStack = new ArrayDeque<>();
    private final Deque<CartMemento> redoStack = new ArrayDeque<>();

    /** Saklanacak maksimum Memento sayısı */
    private final int maxSize = 10;

    /**
     * Yeni bir durumu geçmişe ekler.
     * Redo geçmişi temizlenir; maxSize aşılırsa en eski kayıt silinir.
     *
     * @param memento Kaydedilecek durum
     */
    public void saveState(CartMemento memento) {
        undoStack.push(memento);
        redoStack.clear(); // Yeni eylem sonrası redo geçmişi geçersiz
        if (undoStack.size() > maxSize) {
            undoStack.pollLast(); // En eski kaydı sil
        }
        System.out.println("  [CartHistory] Kaydedildi (" + undoStack.size() + " kayıt): "
                + memento.getDescription());
    }

    /**
     * Son kaydedilen duruma geri döner (Undo).
     *
     * @return Geri alınacak Memento veya null (geçmiş boşsa)
     */
    public CartMemento undo() {
        if (undoStack.isEmpty()) {
            System.out.println("  [CartHistory] Geri alınacak kayıt yok!");
            return null;
        }
        CartMemento memento = undoStack.pop();
        redoStack.push(memento);
        System.out.println("  [CartHistory] Geri alınıyor: " + memento.getDescription());
        return memento;
    }

    /**
     * Geri alınan eylemi yeniden yapar (Redo).
     *
     * @return Yeniden yapılacak Memento veya null (redo geçmişi boşsa)
     */
    public CartMemento redo() {
        if (redoStack.isEmpty()) {
            System.out.println("  [CartHistory] Yeniden yapılacak kayıt yok!");
            return null;
        }
        CartMemento memento = redoStack.pop();
        undoStack.push(memento);
        System.out.println("  [CartHistory] Yeniden yapılıyor: " + memento.getDescription());
        return memento;
    }

    /**
     * @return Geri alınabilir kayıt var mı?
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * @return Yeniden yapılabilir kayıt var mı?
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Undo geçmişini konsola yazdırır.
     */
    public void printHistory() {
        System.out.println("  === Sepet Geçmişi ===");
        if (undoStack.isEmpty()) {
            System.out.println("  (boş)");
            return;
        }
        undoStack.forEach(m -> System.out.println("  - " + m.toString()));
    }
}
