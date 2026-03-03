package com.designpatterns.behavioral.mediator;

/**
 * COLLEAGUE - Arabulucu aracılığıyla iletişim kuran bileşen.
 *
 * <p>Her Colleague sadece mediator'ı bilir, diğer colleague'ları bilmez.
 * Bu sayede bileşenler arası doğrudan bağımlılık ortadan kalkar.
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public abstract class ShoppingColleague {

    /** Bu bileşenin iletişim kuracağı arabulucu */
    protected ShoppingMediator mediator;

    /** Bileşenin görünen adı (loglama için) */
    protected final String name;

    /**
     * @param name Bileşen adı
     */
    public ShoppingColleague(String name) {
        this.name = name;
    }

    /**
     * Mediator'ı bileşene atar. Genellikle {@link ShoppingMediator#register} tarafından çağrılır.
     *
     * @param mediator Atanacak mediator
     */
    public void setMediator(ShoppingMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * @return Bileşenin adı
     */
    public String getName() {
        return name;
    }

    /**
     * Mediator'dan gelen olayı işler.
     *
     * @param event Olay adı
     * @param data  Olayla birlikte gelen veri
     */
    public abstract void receiveEvent(String event, Object data);
}
