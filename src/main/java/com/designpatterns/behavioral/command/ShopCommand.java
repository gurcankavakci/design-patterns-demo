package com.designpatterns.behavioral.command;

/**
 * COMMAND PATTERN - Komut Deseni
 *
 * ============================================================
 * TANIM (Intent):
 * ============================================================
 * Bir isteği nesne olarak kapsüller. Bu sayede farklı isteklerle
 * parametre geçilebilir, kuyruk oluşturulabilir, loglama yapılabilir
 * ve geri alma (undo) işlemleri desteklenebilir.
 *
 * <p>Definition (EN): Encapsulate a request as an object, thereby letting
 * you parameterize clients with different requests, queue or log requests,
 * and support undoable operations.</p>
 *
 * ============================================================
 * PROBLEM (Motivasyon):
 * ============================================================
 * Kullanıcı sepete ürün ekler, çıkarır, sipariş verir, iptal eder.
 * <ul>
 *   <li>Bu işlemlerin geri alınabilmesi (Undo) gerekiyor</li>
 *   <li>Tekrar edilebilmesi (Redo) gerekiyor</li>
 *   <li>İşlem geçmişini kayıt altına almak istiyoruz</li>
 *   <li>İşlemleri kuyruk yapıp sonra çalıştırmak istiyoruz</li>
 * </ul>
 *
 * ============================================================
 * ROLLER (Participants):
 * ============================================================
 * <ul>
 *   <li><b>Command (ShopCommand)</b>: execute ve undo interface'i</li>
 *   <li><b>ConcreteCommand</b>: AddToCartCommand, RemoveFromCartCommand,
 *       PlaceOrderCommand, ApplyCouponCommand</li>
 *   <li><b>Invoker (CartCommandManager)</b>: command'ı çalıştırır, undo/redo yönetir</li>
 *   <li><b>Receiver (Cart, Order)</b>: gerçek iş yapan nesne</li>
 *   <li><b>Client</b>: command nesnelerini oluşturur</li>
 * </ul>
 *
 * ============================================================
 * UML DIAGRAM:
 * ============================================================
 * <pre>
 * ┌─────────────────┐    ┌──────────────────────┐    ┌──────────────────┐
 * │ Client          │    │ CartCommandManager    │    │  <<interface>>   │
 * │ creates commands│    │ (Invoker)             │    │  ShopCommand     │
 * └─────────────────┘    ├──────────────────────┤    ├──────────────────┤
 *         │              │ - history: Deque      │───>│ + execute()      │
 *         │              │ - redoStack: Deque    │    │ + undo()         │
 *         │              │ + execute(cmd)        │    │ + getName()      │
 *         │              │ + undo()              │    │ + getDescription()│
 *         │              │ + redo()              │    └────────┬─────────┘
 *         │              └──────────────────────┘             │ implements
 *         │ creates                                  ┌─────────┴──────────────┐
 *         └──────────────────────────────────────--> │ AddToCartCommand       │
 *                                                    │ RemoveFromCartCommand  │
 *                                                    │ PlaceOrderCommand      │
 *                                                    │ ApplyCouponCommand     │
 *                                                    └────────────────────────┘
 * </pre>
 *
 * ============================================================
 * COMMAND'IN GÜÇLÜ YÖNLERİ:
 * ============================================================
 * <pre>
 * 1. Undo/Redo  : execute() + undo() metotları
 * 2. Makro      : Birden fazla command'ı gruplayabilirsin (MacroCommand)
 * 3. Kuyruk     : Command'ları sonra çalıştırmak için depolayabilirsin
 * 4. Loglama    : Her command kaydedilebilir
 * 5. Transaction: Tüm command'lar başarılı olmalı, yoksa hepsi geri al
 * </pre>
 *
 * ============================================================
 * GERCEK HAYAT ANALOGISI:
 * ============================================================
 * <p>Restoran siparisi. Garson (Invoker) siparisi (Command) not alır ve
 * mutfağa (Receiver) iletir. Müsteri (Client) mutfağı doğrudan aramaz.
 * Siparis iptal edilirse (Undo) garson mutfağa haber verir.
 * Siparis geçmisi kayıt altında tutulur.</p>
 *
 * ============================================================
 * AVANTAJLAR:
 * ============================================================
 * <ul>
 *   <li>+ Gönderici ve alıcı arasındaki decoupling</li>
 *   <li>+ Undo/Redo desteği kolaylaşır</li>
 *   <li>+ Command'lar compose edilebilir (Macro Command)</li>
 *   <li>+ Loglama ve audit trail</li>
 *   <li>+ Open/Closed Principle: Yeni command eklemek mevcut kodu değiştirmez</li>
 * </ul>
 *
 * ============================================================
 * DEZAVANTAJLAR:
 * ============================================================
 * <ul>
 *   <li>- Her işlem için yeni sınıf (class explosion)</li>
 *   <li>- Kod karmaşıklığı artar</li>
 * </ul>
 *
 * ============================================================
 * NE ZAMAN KULLANILIR:
 * ============================================================
 * <ul>
 *   <li>Parameterize operations (GUI butonları farklı işlemler yapar)</li>
 *   <li>Queue'a alınan işlemler (job queue, task scheduler)</li>
 *   <li>Undo/Redo gerektiğinde (metin editörleri, grafikler)</li>
 *   <li>Transactional davranış (tümü başarılı veya tümü geri al)</li>
 *   <li>Makro komutlar (birden fazla işlemi tek komutla çalıştırma)</li>
 * </ul>
 */
public interface ShopCommand {

    /**
     * Komutu çalıştırır.
     * İşlemi gerçekleştirir ve gerekli değişiklikleri yapar.
     */
    void execute();

    /**
     * Komutu geri alır.
     * execute() ile yapılan değişiklikleri tersine çevirir.
     */
    void undo();

    /**
     * Komutun adını döndürür.
     * Loglama ve geçmiş görüntüleme için kullanılır.
     *
     * @return Komut adı
     */
    String getCommandName();

    /**
     * Komutun detaylı açıklamasını döndürür.
     * Geçmiş listesinde gösterim için kullanılır.
     *
     * @return Komut açıklaması
     */
    String getDescription();
}
