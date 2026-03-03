package com.designpatterns.behavioral.state;

/**
 * CONCRETE STATE - Iptal Edildi Durumu (Terminal)
 *
 * <p>STATE PATTERN'de Terminal ConcreteState rolunu ustlenir.</p>
 *
 * <p>CANCELLED (Iptal Edildi) durumu bir TERMINAL STATE'tir:
 * <ul>
 *   <li>Siparis iptal edilmistir</li>
 *   <li>Artik hicbir islem yapilamaz</li>
 *   <li>Yeni siparis olusturulabilir (baska bir is akisi)</li>
 * </ul>
 * </p>
 *
 * <p>Terminal state alongside DELIVERED.
 * Once an order is cancelled, no further operations are permitted.
 * A new order must be placed if the customer still wants the products.</p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class CancelledState implements OrderState {

    /**
     * {@inheritDoc}
     * Terminal durum - iptal edilmis siparis onaylanamaz.
     */
    @Override
    public void confirm(OrderContext context) {
        System.out.println("  [CancelledState] Iptal edilmis siparis uzerinde islem yapilamaz!");
        System.out.println("    Yeni bir siparis olusturmak icin tekrar satin alim yapiniz.");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - iptal edilmis siparis kargoya verilemez.
     */
    @Override
    public void ship(OrderContext context) {
        System.out.println("  [CancelledState] Iptal edilmis siparis uzerinde islem yapilamaz!");
        System.out.println("    Siparis sisteme kaydedilmis ancak islem goremiyor.");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - iptal edilmis siparis teslim edilemez.
     */
    @Override
    public void deliver(OrderContext context) {
        System.out.println("  [CancelledState] Iptal edilmis siparis uzerinde islem yapilamaz!");
    }

    /**
     * {@inheritDoc}
     * Terminal durum - zaten iptal edilmis.
     */
    @Override
    public void cancel(OrderContext context) {
        System.out.println("  [CancelledState] Siparis zaten iptal edilmis!");
        System.out.println("    Bu siparis icin islem yapilamaz.");
    }

    @Override
    public String getStateName() {
        return "Iptal Edildi (CANCELLED)";
    }

    @Override
    public String getStateDescription() {
        return "Siparis iptal edildi. Terminal durum - artik islem yapilamaz. Gerekirse yeni siparis olusturun.";
    }
}
