package com.designpatterns.behavioral.state;

/**
 * CONCRETE STATE - Kargoda Durumu
 *
 * <p>STATE PATTERN'de ConcreteState rolunu ustlenir.</p>
 *
 * <p>SHIPPED (Kargoda) durumundaki bir siparis:
 * <ul>
 *   <li>TESLIM EDILEBILIR: deliver() -> DELIVERED durumuna gecer</li>
 *   <li>IPTAL EDILEMEZ: cancel() -> hata! Kargodaki siparis iptal edilemez.</li>
 *   <li>ONAYLANAMAZ: confirm() -> hata mesaji</li>
 *   <li>TEKRAR KARGOYA VERILEMEZ: ship() -> hata mesaji</li>
 * </ul>
 * </p>
 *
 * <p>Critical business rule: Once an order is shipped, it cannot be cancelled.
 * The customer must go through the return process instead.</p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class ShippedState implements OrderState {

    /**
     * {@inheritDoc}
     * Kargodaki siparis zaten onaylanmis, tekrar onaylanamaz.
     */
    @Override
    public void confirm(OrderContext context) {
        System.out.println("  [ShippedState] HATA: Siparis zaten kargoda! Onay gerekmiyor.");
    }

    /**
     * {@inheritDoc}
     * Kargodaki siparis zaten kargoda, tekrar kargoya verilemez.
     */
    @Override
    public void ship(OrderContext context) {
        System.out.println("  [ShippedState] HATA: Siparis zaten kargoda!");
        System.out.println("    Mevcut kargo takip numaranizla sureci takip edin.");
    }

    /**
     * {@inheritDoc}
     * SHIPPED -> DELIVERED: Musteriye teslim bildirimi gonderilir.
     */
    @Override
    public void deliver(OrderContext context) {
        System.out.println("  [ShippedState] Siparis teslim edildi!");
        System.out.println("    Musteriye teslim bildirimi gonderildi.");
        System.out.println("    Siparis tamamlandi. Degerlendirme daveti e-postasi gonderildi.");
        System.out.println("    Kargo firmasi teslimat kaydi guncellendi.");
        context.setState(new DeliveredState());
    }

    /**
     * {@inheritDoc}
     * CRITICAL: Kargodaki siparis iptal EDILEMEZ!
     * Bu is kurali: kargo sirketinden geri cagrma cok pahalı ve zaman alıcıdır.
     */
    @Override
    public void cancel(OrderContext context) {
        System.out.println("  [ShippedState] HATA: Kargodaki siparis iptal EDILEMEZ!");
        System.out.println("    Kargodaki siparisin iptali mumkun degildir.");
        System.out.println("    Alternatif: Urun teslim edildikten sonra iade sureci baslatabilirsiniz.");
        System.out.println("    Iade sureci: Musteri hizmetleri 0850-XXX-XXXX");
    }

    @Override
    public String getStateName() {
        return "Kargoda (SHIPPED)";
    }

    @Override
    public String getStateDescription() {
        return "Siparis kargoya verildi. Kargo firmasinda teslim bekleniyor. Iptal yapilamaz.";
    }
}
