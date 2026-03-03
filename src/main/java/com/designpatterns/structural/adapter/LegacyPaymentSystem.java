package com.designpatterns.structural.adapter;

/**
 * LEGACY PAYMENT SYSTEM - Eski Ödeme Sistemi (Adaptee)
 *
 * <p>Bu sınıf ShopEase'in 10 yıl önce yazılmış eski ödeme sistemidir.
 * Interface'i farklı, sadece TL destekliyor ve kuruş cinsinden çalışıyor.
 * Doğrudan kullanamayız ama {@link LegacyPaymentAdapter} ile sarabiliyoruz.
 *
 * <p><b>NEDEN KULLANILMAYA DEVAM EDİYOR?</b><br>
 * - Bazı bankalarla entegrasyon sadece bu sistem üzerinden çalışıyor
 * - Yüz binlerce işlem kaydı bu sistemde
 * - Tamamen yeniden yazmak çok maliyetli
 *
 * <p><b>ADAPTER PATTERN'DA ROLÜ:</b> Adaptee (Adapt edilmesi gereken sınıf)<br>
 * Bu sınıf değiştirilmez. LegacyPaymentAdapter onu wrap ederek modern
 * interface'e uyumlu hale getirir.
 *
 * @author ShopEase Legacy Team (2014)
 * @version 1.0
 */
public class LegacyPaymentSystem {

    /**
     * Eski sisteme bağlantı kurar.
     * Modern sistemde bu işlem constructor'da otomatik yapılır.
     */
    public void connect() {
        System.out.println("  [LEGACY] Eski ödeme sistemine bağlanıldı (Legacy v1.0)");
        System.out.println("  [LEGACY] Desteklenen para birimi: TL | Format: Kuruş cinsinden");
    }

    /**
     * Ödeme işlemi yapar.
     *
     * <p>DİKKAT: Bu eski API kuruş (1/100 TL) cinsinden çalışır!
     * Adaptör bu dönüşümü otomatik yapar.
     *
     * @param amountInKurus Ödeme miktarı (KURUŞ cinsinden, örn: 150 TL = 15000)
     * @param customerCode  Müşteri kodu (eski sistem 6 haneli kod kullanır)
     * @return Eski format işlem kimliği (LEGACY-TXN-{timestamp})
     */
    public String makePayment(int amountInKurus, String customerCode) {
        System.out.printf("  [LEGACY] Ödeme yapılıyor: %d kuruş (= %.2f TL) | Müşteri: %s%n",
                          amountInKurus, amountInKurus / 100.0, customerCode);
        String txnId = "LEGACY-TXN-" + System.currentTimeMillis();
        System.out.println("  [LEGACY] İşlem tamamlandı. TXN ID: " + txnId);
        return txnId;
    }

    /**
     * Ödemeyi geri alır (iptal eder).
     *
     * @param legacyTransactionId Eski format işlem kimliği
     * @return true iptal başarılı ise
     */
    public boolean reversePayment(String legacyTransactionId) {
        System.out.println("  [LEGACY] İptal işlemi başlatıldı: " + legacyTransactionId);
        System.out.println("  [LEGACY] Ödeme başarıyla iptal edildi: " + legacyTransactionId);
        return true;
    }

    /**
     * İşlem durumunu kontrol eder.
     *
     * <p>Eski sistem sadece "COMPLETED" ve "FAILED" durumlarını destekler.
     * Modern sistem daha fazla durum destekler.
     *
     * @param legacyTransactionId Kontrol edilecek işlem kimliği
     * @return "COMPLETED", "FAILED" veya "UNKNOWN"
     */
    public String checkTransaction(String legacyTransactionId) {
        System.out.println("  [LEGACY] Durum sorgulanıyor: " + legacyTransactionId);
        // Simüle: tüm işlemler tamamlandı varsayalım
        return "COMPLETED";
    }

    /**
     * Eski sistemin versiyon bilgisini döner.
     *
     * @return Versiyon bilgisi
     */
    public String getVersion() {
        return "Legacy Payment System v1.0 (Sadece TL, Kuruş cinsinden)";
    }
}
