package com.designpatterns.creational.prototype;

import com.designpatterns.model.Product;

/**
 * PROTOTYPE PATTERN - Prototip Deseni
 *
 * TANIM: Oluşturmak istediğiniz nesnenin türünü belirlemek için
 * mevcut bir nesneyi kopyalar (clone). Pahalı nesne oluşturma
 * işlemlerini tekrarlamaktan kaçınır.
 *
 * PROBLEM: Bir ürünü varyant olarak kopyalamak istiyoruz.
 * Örneğin: iPhone 15 Pro'yu temel alarak iPhone 15 Pro Max oluşturmak.
 * Veya bir ürün şablonundan çok sayıda ürün üretmek.
 *
 * SHALLOW COPY vs DEEP COPY:
 * Shallow Copy: Primitive alanlar kopyalanır, referans tipler paylaşılır
 *   → Bir nesneyi değiştirmek diğerini de etkiler (tehlikeli!)
 * Deep Copy: Tüm nesneler yeni bellek alanına kopyalanır
 *   → Tamamen bağımsız iki nesne
 *
 * ÖRNEKLE ANLATIM:
 * Shallow Copy:
 *   prototip → { name: "Laptop", specs: [referans A] }
 *   klone    → { name: "Laptop", specs: [referans A] } // AYNI referans!
 *   klone.specs.add("RAM") → prototip.specs de değişir!
 *
 * Deep Copy (bu projede kullanılan):
 *   prototip → { name: "Laptop", specs: [referans A] }
 *   klone    → { name: "Laptop", specs: [referans B] } // YENİ referans!
 *   klone.specs.add("RAM") → prototip.specs değişmez (güvenli)
 *
 * GERCEK HAYAT ANALOGISI:
 * Biyolojide hücre bölünmesi. Bir hücre kendinin tam bir kopyasını
 * oluşturur. DNA (veri) kopyalanır, tamamen yeni bir hücre oluşur.
 * Veya: Bir word şablonu alıp üzerine çalışmak gibi.
 *
 * JAVA'DA CLONE:
 * Java'nın Cloneable interface'i shallow copy yapar.
 * Gerçek bir Prototype için kendi clone() metodumuzu yazmalıyız.
 * Bu projede custom clone kullanıyoruz.
 *
 * UML DIAGRAM:
 * ┌──────────────────────────────┐
 * │   <<interface>>              │
 * │   ProductPrototype           │
 * ├──────────────────────────────┤
 * │ + clone(): ProductPrototype  │
 * │ + getProduct(): Product      │
 * └──────────────┬───────────────┘
 *                │ implements
 *       ┌────────┴─────────┐
 * ┌─────┴────────┐ ┌───────┴──────────┐
 * │PhysicalProduct│ │DigitalProduct    │
 * │Prototype      │ │Prototype         │
 * └───────────────┘ └──────────────────┘
 *
 *           ProductRegistry (uses clones)
 *
 * NE ZAMAN KULLANILIR:
 * - Nesne oluşturma pahalıysa (DB sorgusu, ağ çağrısı)
 * - Birbirine çok benzer nesneler oluşturmak için
 * - Runtime'da nesne tipini belirlemek gerektiğinde
 * - Nesne state'ini kayıt altına almak için (Memento alternatifi)
 *
 * AVANTAJLAR:
 * + Pahalı nesne oluşturmadan kaçınır
 * + Çalışma zamanında nesne oluşturma esnekliği
 * + Karmaşık nesne durumlarını kolayca kopyala
 *
 * DEZAVANTAJLAR:
 * - Deep copy karmaşık olabilir (circular reference)
 * - Her sınıfın clone metodunu implement etmesi gerekir
 *
 * ---
 *
 * PROTOTYPE PATTERN - Prototype Pattern
 *
 * DEFINITION: Specifies the kinds of objects to create using a prototypical instance,
 * and creates new objects by copying this prototype. Avoids expensive object creation.
 *
 * PROBLEM: We want to copy a product as a variant.
 * For example: Create iPhone 15 Pro Max based on iPhone 15 Pro.
 * Or produce many products from a product template.
 *
 * REAL WORLD ANALOGY:
 * Cell division in biology. A cell creates a complete copy of itself.
 * DNA (data) is copied, a completely new cell is formed.
 * Or: Like taking a word template and working on top of it.
 */
public interface ProductPrototype {

    /**
     * Bu prototip nesnesinin derin kopyasını oluşturur ve döndürür.
     * Dönen nesne, orijinal nesneden tamamen bağımsızdır (deep copy).
     *
     * @return Klonlanmış yeni ProductPrototype nesnesi
     */
    ProductPrototype clonePrototype();

    /**
     * Prototip içindeki Product nesnesini döndürür.
     *
     * @return Product nesnesi
     */
    Product getProduct();

    /**
     * Prototip ve ürün bilgilerini konsola yazdırır.
     * Debug ve demo amaçlıdır.
     */
    void displayInfo();
}
