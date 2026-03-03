package com.designpatterns.behavioral.iterator;

import com.designpatterns.model.Product;

/**
 * ITERATOR PATTERN - Yineleyici Deseni
 *
 * TANIM: Bir koleksiyonun elemanlarinaa ic yapisini aciga cikarmadan sirayla erisim saglar.
 *
 * PROBLEM: Urun katalogu farkli sekillerde gezmek istiyoruz:
 * - Tum urunler, kategoriye gore, fiyat araligi, stokta olanlar.
 * Her gezme mantigini Catalog sinifina koyarsak God Class olur.
 *
 * JAVA'DA ITERATOR: java.util.Iterator, Iterable (for-each destegi)
 * Biz custom Iterator'lar yaziyoruz (ek filtreleme icin).
 *
 * UML DIAGRAM:
 * +------------------------+
 * |  <<interface>>         |
 * |  ProductIterator       |
 * +------------------------+
 * |+hasNext(): boolean     |
 * |+next(): Product        |
 * |+reset(): void          |
 * +----------+-------------+
 *            | implements
 *    +--------+-------------------------------+
 * AllProductsIter  CategoryIter  PriceRangeIter  InStockIter
 *
 * GERCEK HAYAT ANALOGISI:
 * Kitaplik. Kitaplari alfabetik, konuya gore, yayin yilina gore gezebilirsiniz.
 * Kitaplik (koleksiyon) ayni, gezme sekli (iterator) degisiyor.
 *
 * AVANTAJLAR:
 * + Koleksiyon implementasyonundan bagimsiz iterasyon
 * + Ayni koleksiyon icin birden fazla gezme yontemi
 * + Single Responsibility
 *
 * DEZAVANTAJLAR:
 * - Basit koleksiyonlar icin overkill
 */
public interface ProductIterator {

    boolean hasNext();

    Product next();

    void reset();

    int getTotalItems();

    String getIteratorType();
}
