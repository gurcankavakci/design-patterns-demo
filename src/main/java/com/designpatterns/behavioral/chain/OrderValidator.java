package com.designpatterns.behavioral.chain;

/**
 * CHAIN OF RESPONSIBILITY PATTERN - Sorumluluk Zinciri Deseni
 *
 * ============================================================
 * TANIM (Intent):
 * ============================================================
 * Isteği işleyecek nesneyi birden fazla nesneye sans tanıyarak,
 * bu nesneleri zincir seklinde bağlar. Istek zincir boyunca ilerler
 * ta ki bir nesne onu isleyene kadar.
 *
 * <p>Definition (EN): Avoid coupling the sender of a request to its receiver
 * by giving more than one object a chance to handle the request. Chain the
 * receiving objects and pass the request along the chain until an object
 * handles it.</p>
 *
 * ============================================================
 * PROBLEM (Motivasyon):
 * ============================================================
 * Siparis doğrulama birden fazla adım içeriyor:
 * <ol>
 *   <li>Stok kontrolü - ürünler stokta var mı?</li>
 *   <li>Ödeme yöntemi doğrulama - desteklenen bir yöntem mi?</li>
 *   <li>Yas kısıtlaması kontrolü - yaşa uygun ürün mü?</li>
 *   <li>Dolandırıcılık tespiti - şüpheli işlem var mı?</li>
 * </ol>
 *
 * <p>Bunları tek sınıfa koyarsak God Object olur. Ayrı ayrı çağırırsak
 * sıra yönetimi zorlaşır. Zincir ile her validator sadece kendi işini yapar.</p>
 *
 * ============================================================
 * ROLLER (Participants):
 * ============================================================
 * <ul>
 *   <li><b>Handler (OrderValidator)</b>: abstract handler interface/class.
 *       nextValidator referansı ve passToNext() mekanizması burada.</li>
 *   <li><b>ConcreteHandler</b>: StockValidator, PaymentValidator, AgeRestrictionValidator,
 *       FraudDetectionValidator. Istegi isler veya sonrakine iletir.</li>
 *   <li><b>Client</b>: Zinciri oluşturur, ilk handler'a isteği gönderir.</li>
 * </ul>
 *
 * ============================================================
 * PIPELINE vs CHAIN:
 * ============================================================
 * <pre>
 * Chain   : Ilk isleyebilen isler, zincir DURUR.
 *           Ornek: Yetkili kullanıcı bulundu -> diger adımlara gerek yok.
 *
 * Pipeline: Her node MUTLAKA isler, sonrakine ILETIR.
 *           Ornek: Tüm validasyonlar çalışır, hepsi rapor üretir.
 *           -> Bu örnekte Pipeline kullanıyoruz.
 * </pre>
 *
 * ============================================================
 * UML DIAGRAM:
 * ============================================================
 * <pre>
 * ┌─────────────────────────────────────────────────────────┐
 * │  Client                                                  │
 * └──────────────────────────┬──────────────────────────────┘
 *                            │ sends to first handler
 *    ┌──────────────┐   ┌────┴─────────┐   ┌──────────────┐
 *    │StockValidator│→→→│PaymentValid. │→→→│FraudDetector │→→→ ...
 *    └──────────────┘   └──────────────┘   └──────────────┘
 *     each sets nextValidator
 *
 *  ┌──────────────────────────────────────┐
 *  │         <<abstract>>                  │
 *  │         OrderValidator                │
 *  ├──────────────────────────────────────┤
 *  │ - nextValidator: OrderValidator       │
 *  ├──────────────────────────────────────┤
 *  │ + setNext(v): OrderValidator          │
 *  │ # passToNext(req): boolean            │
 *  │ + validate(req): boolean   <<abstract>│
 *  │ + getValidatorName(): String <<abstract│
 *  └──────────────────────────────────────┘
 *               △ extends
 *      ┌────────┼────────┬───────────────┐
 *  ┌───┴──┐  ┌──┴────┐  ┌┴──────────┐  ┌┴──────────────┐
 *  │Stock │  │Payment│  │AgeRestrict│  │FraudDetection │
 *  │Valid.│  │Valid. │  │Validator  │  │Validator      │
 *  └──────┘  └───────┘  └───────────┘  └───────────────┘
 * </pre>
 *
 * ============================================================
 * GERCEK HAYAT ANALOGISI:
 * ============================================================
 * <p>İse alım süreci: CV inceleme → Telefon görüsmesi → Teknik mülakat →
 * IK görüsmesi → Teklif. Her asama bir handler'dır. Aday (istek) zincir
 * boyunca ilerler. Herhangi bir asamada elenilebilir.</p>
 *
 * <p>Diger örnekler:</p>
 * <ul>
 *   <li>Müsteri destek seviyeleri (Level 1 → Level 2 → Level 3)</li>
 *   <li>Onay hiyerarsisi (Calısan → Müdür → Direktör → CEO)</li>
 *   <li>Web framework middleware zinciri (auth → logging → routing)</li>
 * </ul>
 *
 * ============================================================
 * AVANTAJLAR:
 * ============================================================
 * <ul>
 *   <li>+ Gönderici ve alıcı arasındaki coupling azalır</li>
 *   <li>+ Zincire yeni handler eklemek/çıkarmak kolay (Open/Closed Principle)</li>
 *   <li>+ Single Responsibility: Her handler tek bir is yapar</li>
 *   <li>+ Runtime'da zincir sırası değistirilebilir</li>
 * </ul>
 *
 * ============================================================
 * DEZAVANTAJLAR:
 * ============================================================
 * <ul>
 *   <li>- Tüm zincirin islendiği garantisi yok (chain sona ulasmayabilir)</li>
 *   <li>- Debug zor: hangi handler'da işlendiğini takip etmek güç</li>
 *   <li>- Performans: uzun zincir yavaşlatabilir</li>
 * </ul>
 *
 * ============================================================
 * NE ZAMAN KULLANILIR:
 * ============================================================
 * <ul>
 *   <li>Birden fazla nesnenin aynı isteği isleyebileceği durumlar</li>
 *   <li>Request handler'ın runtime'da belirlenmesi gereken durumlar</li>
 *   <li>Birbirine bağlı islem adımları (pipeline)</li>
 *   <li>Middleware, interceptor zinciri ihtiyaçları</li>
 * </ul>
 */
public abstract class OrderValidator {

    /** Zincirdeki bir sonraki doğrulayıcı. */
    private OrderValidator nextValidator;

    /**
     * Zincire sonraki validator'ı bağlar.
     * Method chaining destekler: v1.setNext(v2).setNext(v3)
     *
     * @param validator Zincire eklenecek sonraki validator
     * @return Eklenen validator (chaining için)
     */
    public OrderValidator setNext(OrderValidator validator) {
        this.nextValidator = validator;
        return validator;
    }

    /**
     * İsteği doğrular.
     * Her ConcreteHandler bu metodu implement etmelidir.
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Onay durumu (true = onaylandı / false = reddedildi)
     */
    public abstract boolean validate(OrderRequest request);

    /**
     * Validator'ın adını döndürür (loglama için).
     *
     * @return Validator adı
     */
    public abstract String getValidatorName();

    /**
     * İsteği zincirdeki bir sonraki validator'a iletir.
     * Eğer sonraki validator yoksa mevcut onay durumunu döndürür.
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Sonraki validator'ın sonucu veya mevcut onay durumu
     */
    protected boolean passToNext(OrderRequest request) {
        if (nextValidator != null) {
            return nextValidator.validate(request);
        }
        // Zincirin sonu: mevcut onay durumunu döndür
        return request.isApproved();
    }

    /**
     * Zincirdeki sonraki validator'ı döndürür.
     *
     * @return Sonraki validator veya null
     */
    public OrderValidator getNextValidator() {
        return nextValidator;
    }
}
