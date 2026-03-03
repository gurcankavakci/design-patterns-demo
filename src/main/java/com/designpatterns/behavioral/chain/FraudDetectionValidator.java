package com.designpatterns.behavioral.chain;

import com.designpatterns.model.Customer;

/**
 * DOLANDIRICILIK TESPİT DOĞRULAYICI - Fraud Detection Validator
 *
 * <p>Chain of Responsibility zincirinin son halkası.
 * Olası dolandırıcılık göstergelerini analiz eder ve şüpheli işlemleri işaretler.</p>
 *
 * <p>Bu validator, isteği reddetmek yerine genellikle UYARI üretir.
 * Bazı durumlarda manuel inceleme için işaretleme yapar.</p>
 *
 * <p><b>Sorumluluk:</b> Dolandırıcılık riski analizi.</p>
 *
 * <p><b>Kural Motoru:</b> Gerçek hayatta makine öğrenmesi modelleri ve
 * kural motorları kullanılır. Bu örnekte basit kural tabanlı kontroller yapılır.</p>
 */
public class FraudDetectionValidator extends OrderValidator {

    /** Yüksek tutar eşiği (TL). Bu değerin üzerindeki işlemler incelenir. */
    private static final double HIGH_AMOUNT_THRESHOLD = 50_000.0;

    /** Premium olmayan müşteriler için maksimum ürün sayısı. */
    private static final int MAX_ITEMS_NON_PREMIUM = 10;

    @Override
    public String getValidatorName() {
        return "Dolandırıcılık Tespit Sistemi";
    }

    /**
     * Siparişi dolandırıcılık göstergelerine karşı analiz eder.
     *
     * <p>Kontrol edilen kurallar:</p>
     * <ol>
     *   <li><b>Yüksek Tutar Kuralı:</b> 50.000 TL üzeri işlemler manuel inceleme için işaretlenir</li>
     *   <li><b>Aşırı Sipariş Kuralı:</b> Premium olmayan müşterinin çok sayıda ürün sipariş etmesi</li>
     * </ol>
     *
     * <p>Not: Bu validator genellikle zinciri kırmaz (setApproved(false) yapmaz),
     * yalnızca uyarı mesajları ekler. Yüksek riskli durumlarda onayı reddedebilir.</p>
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Zincirde bir sonraki validator'ın sonucu
     */
    @Override
    public boolean validate(OrderRequest request) {
        System.out.println("=== [" + getValidatorName() + "] Kontrol ediliyor...");

        double amount = request.getAmount();
        Customer customer = request.getCustomer();

        // Kural 1: Çok yüksek tutarlı işlem
        if (amount > HIGH_AMOUNT_THRESHOLD) {
            System.out.printf("  UYARI: Yuksek tutarli islem tespit edildi: %.2f TL"
                    + " - Manuel inceleme gerekebilir%n", amount);
            request.addMessage("UYARI: Yüksek tutarlı sipariş ("
                    + String.format("%.2f", amount) + " TL) - inceleme için işaretlendi");
            // Çok yüksek tutarda onayı tamamen reddedebiliriz
            if (amount > 200_000.0) {
                request.addMessage("FRAUD HATASI: İşlem tutarı maksimum limiti aşıyor!");
                request.setApproved(false);
                System.out.println("  HATA: İşlem tutarı maksimum limiti aşıyor, REDDEDILDI.");
            }
        } else {
            System.out.printf("  Tutar normal aralikta: %.2f TL%n", amount);
        }

        // Kural 2: Premium olmayan müşteri çok sayıda ürün sipariş ediyor
        int itemCount = request.getOrder().getItems().size();
        if (!customer.isPremium() && itemCount > MAX_ITEMS_NON_PREMIUM) {
            System.out.println("  UYARI: Premium olmayan musteri cok sayida urun siparis ediyor"
                    + " (" + itemCount + " cesit urun)");
            request.addMessage("UYARI: Premium olmayan müşteri " + itemCount
                    + " çeşit ürün sipariş ediyor - inceleme önerilir");
        } else {
            System.out.println("  Siparis profili normal (" + itemCount
                    + " cesit urun, premium=" + customer.isPremium() + ")");
        }

        System.out.println("  [" + getValidatorName() + "] Dolandiricilik analizi tamamlandi.");
        return passToNext(request);
    }
}
