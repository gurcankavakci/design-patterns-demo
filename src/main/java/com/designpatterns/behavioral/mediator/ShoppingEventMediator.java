package com.designpatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * CONCRETE MEDIATOR - Somut Arabulucu
 *
 * <p>Tüm koordinasyon mantığı burada toplanır.
 * Kayıtlı bileşenler arasında olay yönlendirmesi yapar;
 * gönderen bileşen hariç tüm diğer bileşenlere bildirimi iletir.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ShoppingEventMediator implements ShoppingMediator {

    /** Kayıtlı tüm bileşenler */
    private final List<ShoppingColleague> colleagues = new ArrayList<>();

    /**
     * Bileşeni sisteme kaydeder ve mediator referansını atar.
     *
     * @param colleague Kaydedilecek bileşen
     */
    @Override
    public void register(ShoppingColleague colleague) {
        colleagues.add(colleague);
        colleague.setMediator(this);
        System.out.println("  [Mediator] Kayıt: " + colleague.getName());
    }

    /**
     * Gönderen bileşenden gelen olayı diğer tüm bileşenlere iletir.
     * Gönderen bileşene iletilmez (kendi olayını kendisi almasın).
     *
     * @param sender Olayı gönderen bileşen
     * @param event  Olay adı
     * @param data   Olayla birlikte gönderilen veri
     */
    @Override
    public void notify(ShoppingColleague sender, String event, Object data) {
        System.out.println("  [Mediator] Koordine ediliyor: '" + event
                + "' | Gönderen: " + sender.getName());
        for (ShoppingColleague colleague : colleagues) {
            if (colleague != sender) {
                colleague.receiveEvent(event, data);
            }
        }
    }
}
