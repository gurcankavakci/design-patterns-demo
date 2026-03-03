package com.designpatterns.structural.adapter;

/**
 * LEGACY PAYMENT ADAPTER - Adaptör Sınıfı
 *
 * <p>{@link LegacyPaymentSystem}'i {@link ModernPaymentGateway} interface'ine adapt eder.
 * Client kodu ModernPaymentGateway kullanır, arka planda LegacySystem çalışır.
 *
 * <p><b>Bu sınıfın yaptığı dönüşümler:</b>
 * <ol>
 *   <li>Para birimi dönüşümü: USD/EUR → TL (yaklaşık kur ile)</li>
 *   <li>Birim dönüşümü: TL → Kuruş (x100)</li>
 *   <li>Müşteri ID format dönüşümü: modern UUID → legacy kod</li>
 *   <li>Durum kodu eşleştirme: COMPLETED → modern status</li>
 * </ol>
 *
 * <p><b>OBJECT ADAPTER YAKLAŞIMI:</b>
 * LegacyPaymentSystem'i miras almak yerine kompozisyon ile içeriyor.
 * Bu Java'nın tek kalıtım kısıtını aşar ve daha esnek bir tasarım sağlar.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 * @see ModernPaymentGateway
 * @see LegacyPaymentSystem
 */
public class LegacyPaymentAdapter implements ModernPaymentGateway {

    /** Wrap edilen eski sistem nesnesi (Adaptee) */
    private final LegacyPaymentSystem legacySystem;

    /** Yaklaşık USD/TL kuru */
    private static final double USD_TO_TL_RATE = 32.5;

    /** Yaklaşık EUR/TL kuru */
    private static final double EUR_TO_TL_RATE = 35.0;

    /**
     * Adaptör constructor'ı.
     * LegacyPaymentSystem'i oluşturur ve bağlantı kurar.
     */
    public LegacyPaymentAdapter() {
        System.out.println("[ADAPTER] LegacyPaymentAdapter başlatılıyor...");
        this.legacySystem = new LegacyPaymentSystem();
        legacySystem.connect();
        System.out.println("[ADAPTER] Adaptör hazır. " + legacySystem.getVersion());
        System.out.println();
    }

    /**
     * Modern ödeme interface'ini legacy sisteme adapt eder.
     *
     * <p>Yapılan dönüşümler:
     * <ol>
     *   <li>Para birimi TL değilse kur dönüşümü yapılır</li>
     *   <li>TL tutarı kuruşa çevrilir (x100)</li>
     *   <li>Müşteri ID legacy formatına dönüştürülür</li>
     * </ol>
     *
     * @param amount     Ödeme tutarı
     * @param currency   Para birimi (TL, USD, EUR)
     * @param customerId Müşteri kimliği
     * @return Ödeme sonucu
     */
    @Override
    public PaymentResult pay(double amount, String currency, String customerId) {
        System.out.println("[ADAPTER] ModernGateway.pay() çağrısı alındı");
        System.out.printf("[ADAPTER] Dönüşüm yapılıyor - %.2f %s -> TL%n", amount, currency);

        double amountInTL = convertToTL(amount, currency);

        if (!currency.equalsIgnoreCase("TL")) {
            System.out.printf("[ADAPTER] Kur dönüşümü: %.2f %s = %.2f TL%n",
                              amount, currency, amountInTL);
        }

        // TL -> Kuruş dönüşümü (Legacy sistem kuruş istiyor)
        int amountInKurus = (int) Math.round(amountInTL * 100);
        System.out.printf("[ADAPTER] Birim dönüşümü: %.2f TL = %d kuruş%n", amountInTL, amountInKurus);

        // Müşteri ID dönüşümü: modern UUID -> legacy kısa kod
        String legacyCustomerCode = convertToLegacyCode(customerId);
        System.out.println("[ADAPTER] Müşteri kodu dönüştürüldü: " + customerId + " -> " + legacyCustomerCode);

        try {
            String legacyTxnId = legacySystem.makePayment(amountInKurus, legacyCustomerCode);
            System.out.println("[ADAPTER] Legacy TXN ID alındı: " + legacyTxnId);
            return new PaymentResult(true, legacyTxnId, "Ödeme başarılı (Legacy sistem üzerinden)", amount, currency);
        } catch (Exception e) {
            System.err.println("[ADAPTER] Ödeme hatası: " + e.getMessage());
            return PaymentResult.failure("Ödeme başarısız: " + e.getMessage());
        }
    }

    /**
     * Ödeme iptal isteğini legacy sisteme iletir.
     *
     * @param transactionId İptal edilecek işlem kimliği
     * @return true iptal başarılı ise
     */
    @Override
    public boolean cancelPayment(String transactionId) {
        System.out.println("[ADAPTER] cancelPayment() -> reversePayment() dönüşümü");
        return legacySystem.reversePayment(transactionId);
    }

    /**
     * Durum sorgusunu legacy sisteme iletir ve modern formata dönüştürür.
     *
     * @param transactionId Sorgulanacak işlem kimliği
     * @return Modern durum kodu
     */
    @Override
    public String getPaymentStatus(String transactionId) {
        System.out.println("[ADAPTER] getPaymentStatus() -> checkTransaction() dönüşümü");
        String legacyStatus = legacySystem.checkTransaction(transactionId);

        // Legacy durum -> Modern durum eşleştirmesi
        return switch (legacyStatus.toUpperCase()) {
            case "COMPLETED"  -> "SUCCESS";
            case "FAILED"     -> "FAILED";
            case "PROCESSING" -> "PENDING";
            default           -> "UNKNOWN";
        };
    }

    /**
     * Desteklenen para birimlerini döner.
     * Legacy sistem sadece TL destekliyor, adaptör kur dönüşümü yapıyor.
     *
     * @return Desteklenen para birimleri açıklaması
     */
    @Override
    public String getSupportedCurrencies() {
        return "TL (native), USD (kur: " + USD_TO_TL_RATE + "), EUR (kur: " + EUR_TO_TL_RATE + ") " +
               "- [Legacy sistem yalnızca TL destekler, adaptör dönüşüm yapar]";
    }

    // ===== PRIVATE HELPER METHODS =====

    /**
     * Belirtilen para birimindeki tutarı TL'ye dönüştürür.
     */
    private double convertToTL(double amount, String currency) {
        return switch (currency.toUpperCase()) {
            case "TL"  -> amount;
            case "USD" -> amount * USD_TO_TL_RATE;
            case "EUR" -> amount * EUR_TO_TL_RATE;
            default    -> {
                System.out.println("[ADAPTER] Bilinmeyen para birimi: " + currency + ", TL olarak kabul edildi");
                yield amount;
            }
        };
    }

    /**
     * Modern müşteri ID'sini legacy kısa koda dönüştürür.
     * Legacy sistem maksimum 8 haneli alfanumerik kod istiyor.
     */
    private String convertToLegacyCode(String customerId) {
        if (customerId == null || customerId.isEmpty()) {
            return "UNKNOWN";
        }
        // İlk 8 karakteri al ve büyük harfe çevir
        String cleaned = customerId.replaceAll("[^A-Za-z0-9]", "");
        return cleaned.substring(0, Math.min(8, cleaned.length())).toUpperCase();
    }
}
