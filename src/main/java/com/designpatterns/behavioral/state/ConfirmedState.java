package com.designpatterns.behavioral.state;

/**
 * CONCRETE STATE - Onaylandi Durumu
 *
 * <p>STATE PATTERN'de ConcreteState rolunu ustlenir.</p>
 *
 * <p>CONFIRMED (Onaylandi) durumundaki bir siparis:
 * <ul>
 *   <li>KARGOYA VERILEBILİR: ship() -> SHIPPED durumuna gecer</li>
 *   <li>IPTAL EDILEBILIR: cancel() -> CANCELLED (odeme iadesi yapilir)</li>
 *   <li>TEKRAR ONAYLANAMAZ: confirm() -> uyari mesaji</li>
 *   <li>TESLIM EDILEMEZ: deliver() -> hata mesaji (once kargoya verilmeli)</li>
 * </ul>
 * </p>
 *
 * <p>An order reaches CONFIRMED state when:
 * stock is reserved, payment is verified, and the order is ready for shipping.</p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class ConfirmedState implements OrderState {

    /**
     * {@inheritDoc}
     * Zaten onaylanmis siparis tekrar onaylanamaz.
     */
    @Override
    public void confirm(OrderContext context) {
        System.out.println("  [ConfirmedState] Siparis zaten onayli! Tekrar onaylamaya gerek yok.");
    }

    /**
     * {@inheritDoc}
     * CONFIRMED -> SHIPPED: Kargo firmasina bildirim, takip numarasi olusturulur.
     */
    @Override
    public void ship(OrderContext context) {
        System.out.println("  [ConfirmedState] Kargoya veriliyor...");
        System.out.println("    Kargo firmasina bildirim gonderildi.");
        System.out.println("    Takip numarasi olusturuldu: TRK-" + System.currentTimeMillis() % 100000);
        System.out.println("    Depo personeline urun hazirlama talimati gonderildi.");
        context.setState(new ShippedState());
    }

    /**
     * {@inheritDoc}
     * CONFIRMED durumunda henuz kargoda degil, teslim edilemez.
     */
    @Override
    public void deliver(OrderContext context) {
        System.out.println("  [ConfirmedState] HATA: Siparis henuz kargoda degil!");
        System.out.println("    Once siparisi kargoya verin: ship()");
    }

    /**
     * {@inheritDoc}
     * CONFIRMED -> CANCELLED: Odeme iadesi yapilir.
     */
    @Override
    public void cancel(OrderContext context) {
        System.out.println("  [ConfirmedState] Onaylanmis siparis iptal ediliyor...");
        System.out.println("    Odeme iadesi baslatildi (3-5 is gunu).");
        System.out.println("    Stok rezervasyonu kaldirildi, stoklar guncellendi.");
        context.setState(new CancelledState());
    }

    @Override
    public String getStateName() {
        return "Onaylandi (CONFIRMED)";
    }

    @Override
    public String getStateDescription() {
        return "Siparis onaylandi. Stok rezerve edildi ve odeme alindi. Kargoya hazir.";
    }
}
