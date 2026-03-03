package com.designpatterns.behavioral.observer;

public interface StockSubject {

    void addObserver(StockObserver observer);

    void removeObserver(StockObserver observer);

    void notifyObservers(StockEvent event);
}
