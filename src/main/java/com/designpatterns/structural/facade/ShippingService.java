package com.designpatterns.structural.facade;

/**
 * SHIPPING SERVICE - Kargo Servisi (Facade Alt Sistemi)
 *
 * <p>Facade Pattern'da subsystem (alt sistem) rolünü üstlenir.
 * Kargo oluşturma, takip ve iptal işlemlerini yönetir.
 *
 * <p>Client bu sınıfı doğrudan değil, {@link CheckoutFacade} üzerinden kullanır.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ShippingService {

    /** Standart teslimat ücreti (TL) */
    private static final double STANDARD_SHIPPING_COST = 15.0;

    /** Ekspres teslimat ücreti (TL) */
    private static final double EXPRESS_SHIPPING_COST  = 45.0;

    /** Ücretsiz kargo eşiği (TL) */
    private static final double FREE_SHIPPING_THRESHOLD = 500.0;

    /** Kg başına ek ücret (5 kg üzeri için) */
    private static final double EXTRA_WEIGHT_COST_PER_KG = 3.0;

    /**
     * Kargo servisini başlatır.
     */
    public ShippingService() {
        System.out.println("  [ShippingService] Kargo servisi baslatildi.");
    }

    /**
     * Yeni bir kargo kaydı oluşturur ve takip numarası döner.
     *
     * @param orderId  Sipariş kimliği
     * @param address  Teslimat adresi
     * @param type     Kargo tipi (STANDARD, EXPRESS, FREE)
     * @return Takip numarası (tracking number)
     */
    public String createShipment(String orderId, String address, String type) {
        String trackingNo = "TRK-" + orderId + "-" + (System.currentTimeMillis() % 10000);
        System.out.printf("  [Shipping] Kargo olusturuldu: %s | Adres: %s... | Tip: %s%n",
                          trackingNo,
                          address.length() > 30 ? address.substring(0, 30) : address,
                          type);
        return trackingNo;
    }

    /**
     * Ağırlık ve kargo tipine göre teslimat ücretini hesaplar.
     *
     * <p>Kural:
     * <ul>
     *   <li>FREE: Ücretsiz (500 TL üzeri sipariş)</li>
     *   <li>STANDARD: 15 TL (5 kg üzeri için +3 TL/kg)</li>
     *   <li>EXPRESS: 45 TL (5 kg üzeri için +3 TL/kg)</li>
     * </ul>
     *
     * @param totalWeightKg Toplam ağırlık (kg)
     * @param type          Kargo tipi
     * @return Kargo ücreti (TL)
     */
    public double calculateShippingCost(double totalWeightKg, String type) {
        double baseCost = switch (type.toUpperCase()) {
            case "EXPRESS"  -> EXPRESS_SHIPPING_COST;
            case "FREE"     -> 0.0;
            default         -> STANDARD_SHIPPING_COST; // STANDARD
        };

        // 5 kg üzeri ek ücret
        double extraCost = 0.0;
        if (totalWeightKg > 5.0) {
            extraCost = (totalWeightKg - 5.0) * EXTRA_WEIGHT_COST_PER_KG;
        }

        double totalCost = baseCost + extraCost;
        System.out.printf("  [Shipping] Kargo ucreti hesaplandi: %.2f TL (Tip: %s, Agirlik: %.2f kg)%n",
                          totalCost, type, totalWeightKg);
        return totalCost;
    }

    /**
     * Takip numarasına göre kargo durumunu döner.
     *
     * @param trackingNo Takip numarası
     * @return Kargo durumu
     */
    public String getShipmentStatus(String trackingNo) {
        System.out.println("  [Shipping] Kargo durumu sorgulandı: " + trackingNo);
        return "HAZIRLANIYOR";
    }

    /**
     * Kargo kaydını iptal eder.
     *
     * @param trackingNo İptal edilecek kargo takip numarası
     */
    public void cancelShipment(String trackingNo) {
        System.out.println("  [Shipping] Kargo iptal edildi: " + trackingNo);
    }

    public double getFreeShippingThreshold() {
        return FREE_SHIPPING_THRESHOLD;
    }
}
