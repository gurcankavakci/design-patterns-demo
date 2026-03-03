package com.designpatterns.behavioral.state;

/**
 * CONCRETE STATE - Teslim Edildi Durumu (Terminal)
 *
 * <p>STATE PATTERN'de Terminal ConcreteState rolunu ustlenir.</p>
 *
 * <p>DELIVERED (Teslim Edildi) durumu bir TERMINAL STATE'tir:
 * <ul>
 *   <li>Siparis tamamlanmistir, artik hicbir islem yapilamaz</li>
 *   <li>Sadece iade sureci baslatilabilir (baska bir is akisi)</li>
 * </ul>
 * </p>
 *
 * <p>Terminal states represent the end of a workflow.
 * No further state transitions are possible from DELIVERED.
 * The order lifecycle is complete.</p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class DeliveredState implements OrderState {

    /**
     * {@inheritDoc}
     * Terminal durum - onaylama yapilamaz.
     */
    @Override
    public void confirm(OrderContext context) {
        System.out.println("  [DeliveredState] Siparis teslim edilmis, bu islem yapilamaz!");
        System.out.println("    Teslim edilen siparis uzerinde degisiklik yapilamaz.");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - kargoya verme yapilamaz.
     */
    @Override
    public void ship(OrderContext context) {
        System.out.println("  [DeliveredState] Siparis teslim edilmis, bu islem yapilamaz!");
        System.out.println("    Urun zaten musteriye ulasti.");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - zaten teslim edilmis.
     */
    @Override
    public void deliver(OrderContext context) {
        System.out.println("  [DeliveredState] Siparis zaten teslim edilmis!");
        System.out.println("    Teslim tarihi kaydedildi. Urun musteride bulunuyor.");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - iptal yapilamaz.
     */
    @Override
    public void cancel(OrderContext context) {
        System.out.println("  [DeliveredState] Teslim edilmis siparis iptal edilemez!");
        System.out.println("    Urun iade etmek icin musteri hizmetlerini arayin: 0850-XXX-XXXX");
    }

    @Override
    public String getStateName() {
        return "Teslim Edildi (DELIVERED)";
    }

    @Override
    public String getStateDescription() {
        return "Siparis teslim edildi. Terminal durum - artik islem yapilamaz. Iade sureci icin destek hattini arayin.";
    }
}
