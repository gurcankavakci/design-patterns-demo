package com.designpatterns.behavioral.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * COMMAND MANAGER (INVOKER) - Komut Yöneticisi
 *
 * <p>Command Pattern'ın Invoker rolü.
 * Komutları çalıştırır ve Undo/Redo için geçmişi yönetir.</p>
 *
 * <p><b>Invoker'ın Sorumluluğu:</b></p>
 * <ul>
 *   <li>Command'ları execute eder (ama nasıl çalıştığını bilmez)</li>
 *   <li>Çalıştırılan command'ları geçmiş stack'e ekler</li>
 *   <li>Undo için geçmiş stack'ten alır ve undo() çağırır</li>
 *   <li>Redo için redo stack'ten alır ve execute() yeniden çağırır</li>
 * </ul>
 *
 * <p><b>Undo/Redo Mantığı:</b></p>
 * <pre>
 * Başlangıç:  history=[], redo=[]
 * Execute A:  history=[A], redo=[]
 * Execute B:  history=[B,A], redo=[]
 * Execute C:  history=[C,B,A], redo=[]
 * Undo:       history=[B,A], redo=[C]   → C geri alındı
 * Undo:       history=[A], redo=[B,C]   → B geri alındı
 * Redo:       history=[B,A], redo=[C]   → B yeniden yapıldı
 * Execute D:  history=[D,B,A], redo=[]  → Yeni komut, redo stack temizlendi
 * </pre>
 *
 * <p><b>maxHistorySize:</b> Belleği korumak için geçmiş boyutu sınırlıdır.</p>
 */
public class CartCommandManager {

    /**
     * Çalıştırılan komutların geçmişi (Undo için).
     * En son çalıştırılan komut en üstte (Deque olarak Stack gibi kullanılır).
     */
    private Deque<ShopCommand> commandHistory = new ArrayDeque<>();

    /**
     * Geri alınan komutlar (Redo için).
     * En son geri alınan en üstte.
     */
    private Deque<ShopCommand> redoStack = new ArrayDeque<>();

    /** Maksimum geçmiş boyutu. Bellek yönetimi için. */
    private int maxHistorySize = 20;

    /**
     * CartCommandManager varsayılan constructor'ı.
     * maxHistorySize = 20 olarak ayarlanır.
     */
    public CartCommandManager() {}

    /**
     * CartCommandManager constructor'ı özel geçmiş boyutuyla.
     *
     * @param maxHistorySize Maksimum geçmiş boyutu
     */
    public CartCommandManager(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
    }

    /**
     * Komutu çalıştırır ve geçmişe ekler.
     *
     * <p>Yeni bir komut çalıştırıldığında redo stack temizlenir.
     * Bu, standart Undo/Redo davranışıdır.</p>
     *
     * @param command Çalıştırılacak komut
     */
    public void executeCommand(ShopCommand command) {
        command.execute();
        commandHistory.push(command);
        redoStack.clear(); // Yeni komut çalışınca redo stack temizlenir

        // Geçmiş boyutu sınırını aşarsa en eski komutu sil
        if (commandHistory.size() > maxHistorySize) {
            commandHistory.pollLast();
        }

        System.out.println("  [Manager] Komut gecmise eklendi: " + command.getCommandName()
                + " (gecmis boyutu: " + commandHistory.size() + ")");
    }

    /**
     * Son komutun işlemini geri alır.
     *
     * @return Geri alma başarılıysa true, geçmiş boşsa false
     */
    public boolean undo() {
        if (commandHistory.isEmpty()) {
            System.out.println("Geri alinacak komut yok!");
            return false;
        }

        ShopCommand cmd = commandHistory.pop();
        cmd.undo();
        redoStack.push(cmd);

        System.out.println("  [Manager] Undo tamamlandı: " + cmd.getCommandName()
                + " (redo stack boyutu: " + redoStack.size() + ")");
        return true;
    }

    /**
     * Geri alınan son komutu yeniden çalıştırır.
     *
     * @return Yeniden yapma başarılıysa true, redo stack boşsa false
     */
    public boolean redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Yeniden yapilacak komut yok!");
            return false;
        }

        ShopCommand cmd = redoStack.pop();
        cmd.execute();
        commandHistory.push(cmd);

        System.out.println("  [Manager] Redo tamamlandı: " + cmd.getCommandName()
                + " (gecmis boyutu: " + commandHistory.size() + ")");
        return true;
    }

    /**
     * Tüm geçmişi temizler.
     */
    public void clearHistory() {
        commandHistory.clear();
        redoStack.clear();
        System.out.println("  [Manager] Komut gecmisi temizlendi.");
    }

    /**
     * Komut geçmişini görüntüler.
     */
    public void printHistory() {
        System.out.println("=== Komut Gecmisi (en son üstte) ===");
        if (commandHistory.isEmpty()) {
            System.out.println("  (gecmis bos)");
            return;
        }
        commandHistory.forEach(c -> System.out.println("  - " + c.getCommandName()
                + ": " + c.getDescription()));
        System.out.println("  Toplam: " + commandHistory.size() + " komut");
    }

    /**
     * Redo stack içeriğini görüntüler.
     */
    public void printRedoStack() {
        System.out.println("=== Redo Stack (en son üstte) ===");
        if (redoStack.isEmpty()) {
            System.out.println("  (redo stack bos)");
            return;
        }
        redoStack.forEach(c -> System.out.println("  - " + c.getCommandName()
                + ": " + c.getDescription()));
    }

    // Getters
    public int getHistorySize() { return commandHistory.size(); }
    public int getRedoStackSize() { return redoStack.size(); }
    public boolean canUndo() { return !commandHistory.isEmpty(); }
    public boolean canRedo() { return !redoStack.isEmpty(); }
}
