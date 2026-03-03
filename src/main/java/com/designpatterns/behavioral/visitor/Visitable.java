package com.designpatterns.behavioral.visitor;

/**
 * VISITABLE - Ziyaret Edilebilir Element Arayuzu
 *
 * Visitor Pattern'in Element rolunu tanimlar.
 * Her ConcreteElement bu arayuzu implement ederek
 * uygun visitor.visitXxx(this) cagrisini yapar.
 *
 * Double Dispatch burada gerceklesiyor:
 * 1. client: product.accept(visitor)  -> hangi product tipine bakilir
 * 2. impl:   visitor.visitXxx(this)   -> hangi visitor tipine bakilir
 * Boylece hem product hem visitor tipine gore dogru metod cagirilir.
 */
public interface Visitable {

    /**
     * Ziyaretciyi kabul eder ve ilgili visit metodunu cagirarak
     * double dispatch mekanizmasini baslatir.
     *
     * @param visitor Ziyaret edecek visitor nesnesi
     * @return Visitor tarafindan hesaplanan deger (indirim/vergi vb.)
     */
    double accept(ProductVisitor visitor);
}
