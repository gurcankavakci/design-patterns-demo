package com.designpatterns.behavioral.state;

/**
 * STATE PATTERN - Durum Deseni
 *
 * <p>TANIM: Bir nesnenin ic durumu degistiginde davranisinin da
 * degismesine izin verir. Nesne, sinifini degistirmis gibi gorunur.</p>
 *
 * <p>PROBLEM: Siparis farkli durumlarda farkli davranir:
 * <ul>
 *   <li>Bekleyen siparis: onaylanabilir, iptal edilebilir, kargoya verilemez</li>
 *   <li>Onaylanan siparis: kargoya verilebilir, iptal edilebilir, teslim edilemez</li>
 *   <li>Kargodaki siparis: teslim edilebilir, iptal EDILEMEZ</li>
 *   <li>Teslim edilen siparis: hicbir sey yapilamaz</li>
 * </ul>
 * </p>
 *
 * <p>if-else CEHENNEMI (olmadan):
 * <pre>
 * if(status.equals("PENDING")) { ... }
 * else if(status.equals("CONFIRMED")) { ... }
 * else if(status.equals("SHIPPED")) { ... }
 * Her yeni durum mevcut if-else'lere ekleme gerektirir.
 * </pre>
 * </p>
 *
 * <p>ROLLER:
 * <ul>
 *   <li>Context (OrderContext): mevcut state'i tutar, state metodlarina delege eder</li>
 *   <li>State (OrderState): her durum icin ortak interface</li>
 *   <li>ConcreteState (PendingState, ConfirmedState vb.): her durumun davranisi</li>
 * </ul>
 * </p>
 *
 * <p>STATE GECIS DIAGRAMI:
 * <pre>
 *             +----------+
 *             |  PENDING  |--confirm()--> CONFIRMED
 *             |           |--cancel()-->  CANCELLED
 *             +----------+
 *             +------------+
 *             | CONFIRMED  |--ship()-->   SHIPPED
 *             |            |--cancel()--> CANCELLED
 *             +------------+
 *             +----------+
 *             |  SHIPPED  |--deliver()--> DELIVERED
 *             |           |--cancel()--X  (CANNOT cancel!)
 *             +----------+
 *             +-----------+
 *             | DELIVERED |  (terminal state, nothing changes)
 *             +-----------+
 *             +-----------+
 *             | CANCELLED |  (terminal state)
 *             +-----------+
 * </pre>
 * </p>
 *
 * <p>GERCEK HAYAT ANALOGISI:
 * Trafik isigi. Kirmizi-Sari-Yesil-Sari-Kirmizi. Her durum kendi
 * davranisini bilir. "Sari" durumunda ne yapacagini bilir.</p>
 *
 * <p>AVANTAJLAR:
 * <ul>
 *   <li>if-else karmasikligini ortadan kaldirir</li>
 *   <li>Her durum kendi sinifinda izole (Single Responsibility)</li>
 *   <li>Yeni durum eklemek kolay (Open/Closed)</li>
 *   <li>Durum gecisleri acik ve yonetilebilir</li>
 * </ul>
 * </p>
 *
 * <p>DEZAVANTAJLAR:
 * <ul>
 *   <li>Az durumlu sistemlerde overkill</li>
 *   <li>Durum sayisi cok olursa cok sinif gerekir</li>
 * </ul>
 * </p>
 *
 * <p>NE ZAMAN KULLANILIR:
 * <ul>
 *   <li>Nesne duruma bagli farkli davranislar sergilediginde</li>
 *   <li>Durum gecisleri karmasiklastiginda</li>
 *   <li>Durum kontrolu icin cok sayida if/switch oldugunda</li>
 *   <li>ATM, trafik isiklari, siparis yonetimi, oyun karakterleri</li>
 * </ul>
 * </p>
 *
 * STATE PATTERN - State Interface / Durum Arabirimi
 * Defines behavior contract for each order state.
 * Her siparis durumunun uygulamak zorunda oldugu islemleri tanimlar.
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public interface OrderState {

    /**
     * Siparisi onayla.
     * Confirm the order - valid only in PENDING state.
     *
     * @param context siparis baglami / order context
     */
    void confirm(OrderContext context);

    /**
     * Siparisi kargoya ver.
     * Ship the order - valid only in CONFIRMED state.
     *
     * @param context siparis baglami / order context
     */
    void ship(OrderContext context);

    /**
     * Siparisi teslim et.
     * Deliver the order - valid only in SHIPPED state.
     *
     * @param context siparis baglami / order context
     */
    void deliver(OrderContext context);

    /**
     * Siparisi iptal et.
     * Cancel the order - not valid in SHIPPED or later states.
     *
     * @param context siparis baglami / order context
     */
    void cancel(OrderContext context);

    /**
     * Durumun adini dondur.
     * Returns the human-readable name of this state.
     *
     * @return durum adi / state name
     */
    String getStateName();

    /**
     * Durumun aciklamasini dondur.
     * Returns a description of what this state means.
     *
     * @return durum aciklamasi / state description
     */
    String getStateDescription();
}
