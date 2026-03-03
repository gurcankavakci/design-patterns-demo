package com.designpatterns.structural.adapter;

/**
 * ADAPTER PATTERN - Adaptör Deseni
 *
 * <p><b>TANIM:</b> Uyumsuz interface'lere sahip iki sınıfın birlikte çalışmasını sağlar.
 * Bir sınıfın interface'ini, istemcinin beklediği başka bir interface'e dönüştürür.
 *
 * <p><b>PROBLEM:</b> ShopEase yeni bir ödeme gateway'i kullandığı halde, eski müşteri
 * kodumuz eski LegacyPaymentSystem interface'ini bekliyor. Her ikisini de
 * desteklememiz gerekiyor.
 *
 * <p><b>GERCEK HAYAT ANALOGISI:</b><br>
 * Türkiye'den Amerika'ya gittiğinizde priz adaptörü kullanırsınız.
 * Prizin şeklini değiştirmeden, cihazınızı farklı prize takabilirsiniz.
 *
 * <p><b>ROLLER:</b>
 * <ul>
 *   <li>Target (ModernPaymentGateway): Client'ın beklediği interface</li>
 *   <li>Adaptee (LegacyPaymentSystem): Adapt edilmesi gereken mevcut sınıf</li>
 *   <li>Adapter (LegacyPaymentAdapter): Target'i implement eder, Adaptee'yi kullanır</li>
 *   <li>Client: Target interface'i kullanır</li>
 * </ul>
 *
 * <p><b>OBJECT ADAPTER vs CLASS ADAPTER:</b><br>
 * Object Adapter: Adaptee'yi composition ile içerir (önerilen)<br>
 * Class Adapter: Adaptee'den extend eder (Java'da çoklu kalıtım olmadığı için sınırlı)
 *
 * <pre>
 * UML DIAGRAM:
 * ┌──────────────────────┐       ┌───────────────────────────┐
 * │ {@literal <<interface>>}        │       │  LegacyPaymentSystem       │
 * │ ModernPaymentGateway │       │  (Adaptee - eski sistem)   │
 * ├──────────────────────┤       ├───────────────────────────┤
 * │+pay(amount,currency) │       │+makePayment(amountCents)   │
 * │+cancelPayment(txId)  │       │+reversePayment(txId)       │
 * │+getStatus(txId)      │       │+checkTransaction(id)       │
 * └──────────┬───────────┘       └──────────┬────────────────┘
 *            │ implements                    │ uses (has-a)
 *     ┌──────┴───────────────────────────────┘
 *     │   LegacyPaymentAdapter (Adapter)
 *     └──────────────────────────────────────
 * </pre>
 *
 * <p><b>AVANTAJLAR:</b>
 * <ul>
 *   <li>Mevcut kodu değiştirmeden yeni sistemle entegrasyon</li>
 *   <li>Single Responsibility: Adaptasyon mantığı ayrı sınıfta</li>
 *   <li>Open/Closed Principle: Yeni adaptörler eklenebilir</li>
 * </ul>
 *
 * <p><b>DEZAVANTAJLAR:</b>
 * <ul>
 *   <li>Kod karmaşıklığı artar (ek sınıf)</li>
 *   <li>Tüm interface adapt edilmesi zor olabilir</li>
 * </ul>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public interface ModernPaymentGateway {

    /**
     * Ödeme işlemini gerçekleştirir.
     *
     * <p>Modern gateway birden fazla para birimini destekler.
     * Legacy sisteme adaptasyon gerekebilir.
     *
     * @param amount     Ödeme miktarı
     * @param currency   Para birimi kodu (TL, USD, EUR vb.)
     * @param customerId Müşteri kimliği
     * @return PaymentResult işlem sonucu
     */
    PaymentResult pay(double amount, String currency, String customerId);

    /**
     * Mevcut bir ödemeyi iptal eder.
     *
     * @param transactionId İptal edilecek işlem kimliği
     * @return true iptal başarılı ise
     */
    boolean cancelPayment(String transactionId);

    /**
     * Bir ödeme işleminin durumunu sorgular.
     *
     * @param transactionId Sorgulanacak işlem kimliği
     * @return İşlem durumu (PENDING, COMPLETED, FAILED, CANCELLED)
     */
    String getPaymentStatus(String transactionId);

    /**
     * Bu gateway'in desteklediği para birimlerini döner.
     *
     * @return Desteklenen para birimleri listesi (virgülle ayrılmış)
     */
    String getSupportedCurrencies();
}
