package com.designpatterns.structural.bridge;

/**
 * BRIDGE PATTERN - Köprü Deseni
 *
 * <p><b>TANIM:</b> Bir abstraksiyonu implementasyonundan ayırır, böylece ikisi
 * birbirinden bağımsız olarak değişebilir.
 *
 * <p><b>PROBLEM:</b> Farklı bildirim TÜRLERİ (sipariş, promosyon, uyarı) ve
 * farklı KANALLAR (Email, SMS, Push) var.
 * NxM kombinasyonu için NxM sınıf yazmak yerine Bridge kullanıyoruz.
 *
 * <p><b>INHERITANCE PATLAMA PROBLEMI (olmadan):</b><br>
 * OrderEmailNotification, OrderSMSNotification, OrderPushNotification,<br>
 * PromoEmailNotification, PromoSMSNotification, PromoSMSNotification...<br>
 * 3 tür x 3 kanal = 9 sınıf. Yeni kanal ekleyince 3 sınıf daha!
 *
 * <p><b>BRIDGE ÇÖZÜMÜ:</b> N + M sınıf (NxM yerine)
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Abstraction (Notification): yüksek seviye kontrol</li>
 *   <li>RefinedAbstraction (OrderNotification, PromotionNotification)</li>
 *   <li>Implementor (NotificationSender): low-level işlemler</li>
 *   <li>ConcreteImplementor (EmailSender, SMSSender, PushSender)</li>
 * </ul>
 *
 * <pre>
 * UML DIAGRAM:
 * Abstraction                    Implementor
 * ┌────────────────────┐         ┌────────────────────┐
 * │ Notification       │--------→│ NotificationSender  │
 * │ - sender: NS       │         ├────────────────────┤
 * │ + send()           │         │+sendMessage(to,msg) │
 * └────────┬───────────┘         └────────┬───────────┘
 *          │ extends                       │ implements
 * ┌────────┴───────────┐         ┌─────────┴──────────┐
 * │OrderNotification   │         │EmailSender          │
 * │PromotionNotification│        │SMSSender            │
 * └────────────────────┘         │PushNotificationSender│
 *                                └────────────────────┘
 * </pre>
 *
 * <p><b>BRIDGE vs ADAPTER:</b><br>
 * Adapter: Uyumsuz sınıfları birbiriyle çalıştırır (sonradan eklenir)<br>
 * Bridge: Tasarım aşamasında abstraction ve implementation'ı ayırır
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * TV kumandası (Abstraction) ve TV (Implementation). Farklı marka TV'ler
 * için farklı kumandalar olmak zorunda değil. Kumanda değişebilir,
 * TV değişebilir, birbirinden bağımsız.
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Abstraction ve implementation bağımsız evrilebilir</li>
 *   <li>NxM yerine N+M sınıf</li>
 *   <li>Runtime'da implementation değiştirilebilir</li>
 *   <li>Single Responsibility Principle</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Tasarım karmaşıklığı artar</li>
 *   <li>Çok basit durumlar için overkill</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface NotificationSender {

    /**
     * Alıcıya mesaj gönderir.
     *
     * <p>Her kanal (email, sms, push) bu methodu kendi protokolüne göre implement eder.
     *
     * @param recipient Alıcı adresi (email/telefon/device ID)
     * @param subject   Mesaj başlığı
     * @param message   Mesaj içeriği
     * @return true gönderim başarılı ise
     */
    boolean sendMessage(String recipient, String subject, String message);

    /**
     * Bu gönderici kanalının tipini döner.
     *
     * @return Kanal tipi adı (örn: "E-posta", "SMS", "Push Bildirim")
     */
    String getSenderType();

    /**
     * Bu kanalın teknik bilgilerini döner (debug/log amaçlı).
     *
     * @return Teslimat teknik bilgisi
     */
    String getDeliveryInfo();
}
