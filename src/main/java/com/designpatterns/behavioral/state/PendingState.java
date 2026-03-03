package com.designpatterns.behavioral.state;

/**
 * CONCRETE STATE - Beklemede Durumu
 *
 * <p>STATE PATTERN'de ConcreteState rolunu ustlenir.</p>
 *
 * <p>PENDING (Beklemede) durumundaki bir siparis:
 * <ul>
 *   <li>ONAYLANABILIR: confirm() -> CONFIRMED durumuna gecer</li>
 *   <li>IPTAL EDILEBILIR: cancel() -> CANCELLED durumuna gecer</li>
 *   <li>KARGOYA VERILEMEZ: ship() -> hata mesaji</li>
 *   <li>TESLIM EDILEMEZ: deliver() -> hata mesaji</li>
 * </ul>
 * </p>
 *
 * <p>Initial state for all new orders.
 * A pending order has been placed but not yet confirmed by the system.</p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class PendingState implements OrderState {

    /**
     * {@inheritDoc}
     * PENDING -> CONFIRMED: Stok rezerve edilir, odeme onaylanir.
     */
    @Override
    public void confirm(OrderContext context) {
        System.out.println("  [PendingState] Siparis onaylaniyor...");
        System.out.println("    Stok rezerve edildi, odeme onaylandi.");
        System.out.println("    Musteri bilgilendirme e-postasi gonderildi.");
        context.setState(new ConfirmedState());
    }

    /**
     * {@inheritDoc}
     * PENDING durumunda kargoya vermek gecerli degildir.
     */
    @Override
    public void ship(OrderContext context) {
        System.out.println("  [PendingState] HATA: Siparis henuz onaylanmadi, kargoya verilemez!");
        System.out.println("    Once siparisi onaylayin: confirm()");
    }

    /**
     * {@inheritDoc}
     * PENDING durumunda teslim etmek gecerli degildir.
     */
    @Override
    public void deliver(OrderContext context) {
        System.out.println("  [PendingState] HATA: Siparis beklemede, teslim edilemez!");
        System.out.println("    Siparis onaylanmadan ve kargoya verilmeden teslim edilemez.");
    }

    /**
     * {@inheritDoc}
     * PENDING -> CANCELLED: Siparis iptal edilir.
     */
    @Override
    public void cancel(OrderContext context) {
        System.out.println("  [PendingState] Siparis iptal ediliyor...");
        System.out.println("    Stok rezervasyonu kaldirildi.");
        System.out.println("    Odeme iptali islendi (henuz cekilmediyse).");
        context.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Beklemede (PENDING)";
    }

    @Override
    public String getStateDescription() {
        return "Siparis alindi, henuz onaylanmadi. Stok ve odeme kontrolu bekleniyor.";
    }
}
