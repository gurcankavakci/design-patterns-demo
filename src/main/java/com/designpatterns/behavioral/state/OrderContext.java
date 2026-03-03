package com.designpatterns.behavioral.state;

import java.util.ArrayList;
import java.util.List;

/**
 * CONTEXT - Baglamsal Siparis Sinifi
 *
 * <p>STATE PATTERN'de Context rolunu ustlenir. Mevcut durumu tutar ve
 * tum islemleri aktif duruma (currentState) delege eder.</p>
 *
 * <p>Context knows about the State interface but not about concrete states.
 * State transitions happen inside ConcreteState classes.</p>
 *
 * <p>Ozellikler:
 * <ul>
 *   <li>Mevcut durumu (currentState) yonetir</li>
 *   <li>Durum gecis gecmisini (stateHistory) tutar</li>
 *   <li>Islemleri aktif duruma delege eder</li>
 * </ul>
 * </p>
 *
 * @author ShopEase Development Team
 * @version 1.0
 */
public class OrderContext {

    /** Mevcut siparis durumu / current order state */
    private OrderState currentState;

    /** Siparis ID'si / order identifier */
    private String orderId;

    /** Durum gecis gecmisi / state transition history */
    private List<String> stateHistory = new ArrayList<>();

    /**
     * OrderContext olusturur. Baslangic durumu PENDING'dir.
     * Creates an OrderContext starting in PENDING state.
     *
     * @param orderId siparis kimlik numarasi / order ID
     */
    public OrderContext(String orderId) {
        this.orderId = orderId;
        this.currentState = new PendingState();
        stateHistory.add("PENDING");
        System.out.println("[OrderContext] Siparis olusturuldu: " + orderId
                + " | Baslangic durumu: " + currentState.getStateName());
    }

    /**
     * Durumu degistirir ve gecmise ekler.
     * Changes the current state and records it in history.
     *
     * @param state yeni durum / new state to transition to
     */
    public void setState(OrderState state) {
        System.out.println("  [" + orderId + "] Durum degisti: "
                + currentState.getStateName() + " -> " + state.getStateName());
        this.currentState = state;
        stateHistory.add(state.getStateName());
    }

    /**
     * Siparisi onaylar - islem mevcut duruma delege edilir.
     * Confirms the order - delegated to current state.
     */
    public void confirm() {
        System.out.println("[" + orderId + "] confirm() islemi cagrildi...");
        currentState.confirm(this);
    }

    /**
     * Siparisi kargoya verir - islem mevcut duruma delege edilir.
     * Ships the order - delegated to current state.
     */
    public void ship() {
        System.out.println("[" + orderId + "] ship() islemi cagrildi...");
        currentState.ship(this);
    }

    /**
     * Siparisi teslim eder - islem mevcut duruma delege edilir.
     * Delivers the order - delegated to current state.
     */
    public void deliver() {
        System.out.println("[" + orderId + "] deliver() islemi cagrildi...");
        currentState.deliver(this);
    }

    /**
     * Siparisi iptal eder - islem mevcut duruma delege edilir.
     * Cancels the order - delegated to current state.
     */
    public void cancel() {
        System.out.println("[" + orderId + "] cancel() islemi cagrildi...");
        currentState.cancel(this);
    }

    /**
     * Mevcut durumu dondurur.
     *
     * @return aktif durum / current state
     */
    public OrderState getCurrentState() {
        return currentState;
    }

    /**
     * Siparis ID'sini dondurur.
     *
     * @return siparis ID / order ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Durum gecis gecmisini dondurur.
     * Returns the complete state transition history.
     *
     * @return durum gecmisi listesi / state history list
     */
    public List<String> getStateHistory() {
        return stateHistory;
    }

    /**
     * Mevcut durumu ekrana yazdirir.
     * Displays current state information to console.
     */
    public void displayCurrentState() {
        System.out.println("------------------------------");
        System.out.println("Siparis ID   : " + orderId);
        System.out.println("Mevcut Durum : " + currentState.getStateName());
        System.out.println("Aciklama     : " + currentState.getStateDescription());
        System.out.println("Durum Gecmisi: " + String.join(" -> ", stateHistory));
        System.out.println("------------------------------");
    }
}
