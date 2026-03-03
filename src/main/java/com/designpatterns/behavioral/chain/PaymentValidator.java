package com.designpatterns.behavioral.chain;

import java.util.List;

/**
 * ÖDEME DOĞRULAYICI - Payment Validator
 *
 * <p>Chain of Responsibility zincirinin ikinci halkası.
 * Ödeme yönteminin desteklenip desteklenmediğini ve
 * işlem tutarının geçerli olup olmadığını kontrol eder.</p>
 *
 * <p><b>Sorumluluk:</b> Yalnızca ödeme yöntemi ve tutar doğrulaması.</p>
 */
public class PaymentValidator extends OrderValidator {

    /** Desteklenen ödeme yöntemleri listesi. */
    private List<String> supportedMethods = List.of(
            "Kredi Kartı",
            "PayPal",
            "Banka Havalesi",
            "Kripto"
    );

    @Override
    public String getValidatorName() {
        return "Ödeme Doğrulayıcı";
    }

    /**
     * Ödeme yöntemini ve tutarı doğrular.
     *
     * <p>Kontroller:</p>
     * <ol>
     *   <li>Ödeme yöntemi destekleniyor mu?</li>
     *   <li>Sipariş tutarı sıfırdan büyük mü?</li>
     * </ol>
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Zincirde bir sonraki validator'ın sonucu
     */
    @Override
    public boolean validate(OrderRequest request) {
        System.out.println("=== [" + getValidatorName() + "] Kontrol ediliyor...");

        String method = request.getPaymentMethod();

        // Ödeme yöntemi kontrolü
        if (!supportedMethods.contains(method)) {
            request.addMessage("ÖDEME HATASI: Desteklenmeyen ödeme yöntemi: " + method
                    + " (Desteklenenler: " + supportedMethods + ")");
            request.setApproved(false);
            System.out.println("  HATA: Desteklenmeyen ödeme yöntemi: " + method);
        } else {
            System.out.println("  Ödeme yöntemi gecerli: " + method);
        }

        // Tutar kontrolü
        if (request.getAmount() <= 0) {
            request.addMessage("TUTAR HATASI: Geçersiz tutar: " + request.getAmount()
                    + " TL (sıfırdan büyük olmalı)");
            request.setApproved(false);
            System.out.println("  HATA: Geçersiz tutar: " + request.getAmount() + " TL");
        } else {
            System.out.printf("  Tutar gecerli: %.2f TL%n", request.getAmount());
        }

        System.out.println("  [" + getValidatorName() + "] Kontrol tamamlandı.");
        return passToNext(request);
    }

    /**
     * Desteklenen ödeme yöntemlerini döndürür.
     *
     * @return Desteklenen ödeme yöntemleri listesi
     */
    public List<String> getSupportedMethods() {
        return supportedMethods;
    }
}
