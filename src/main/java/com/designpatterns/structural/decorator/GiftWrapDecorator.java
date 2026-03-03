package com.designpatterns.structural.decorator;

/**
 * GIFT WRAP DECORATOR - Hediye Paket Dekoratörü (ConcreteDecorator)
 *
 * <p>{@link ProductDecorator}'dan türeyen somut dekoratör.
 * Ürüne hediye paketleme özelliği ekler: fiyata paket ücreti eklenir,
 * ağırlığa paket kağıdı eklenir, açıklamaya hediye mesajı eklenir.
 *
 * <p><b>KULLANIM ÖRNEĞI:</b>
 * <pre>
 * ShopProduct product = new BasicProduct(rawProduct);
 * product = new GiftWrapDecorator(product, "Iyi yillar! :)");
 * System.out.println(product.getPrice()); // baz fiyat + 25.0 TL
 * </pre>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class GiftWrapDecorator extends ProductDecorator {

    /** Hediye mesajı */
    private final String giftMessage;

    /** Hediye paket ücreti (TL) */
    private static final double GIFT_WRAP_PRICE = 25.0;

    /** Paket kağıdı ağırlığı (kg) */
    private static final double WRAP_PAPER_WEIGHT = 0.2;

    /**
     * Hediye paket dekoratörü oluşturur.
     *
     * @param product     Wrap edilecek ürün
     * @param giftMessage Hediye kartına yazılacak mesaj
     */
    public GiftWrapDecorator(ShopProduct product, String giftMessage) {
        super(product);
        this.giftMessage = (giftMessage != null && !giftMessage.isEmpty())
                ? giftMessage
                : "Sevdiklerinize hediyeden...";
    }

    /**
     * Ürün adına hediye paket etiketi ekler.
     *
     * @return "(Hediye Paketli)" eklenmiş ürün adı
     */
    @Override
    public String getName() {
        return wrappedProduct.getName() + " (Hediye Paketli)";
    }

    /**
     * Baz fiyata hediye paket ücretini ekler.
     *
     * @return Baz fiyat + 25.0 TL paket ücreti
     */
    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + GIFT_WRAP_PRICE;
    }

    /**
     * Açıklamaya hediye mesajını ekler.
     *
     * @return Genişletilmiş açıklama
     */
    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() + " | Hediye paketi: " + giftMessage;
    }

    /**
     * Ağırlığa paket kağıdı ağırlığını ekler.
     *
     * @return Baz ağırlık + 0.2 kg
     */
    @Override
    public double getWeight() {
        return wrappedProduct.getWeight() + WRAP_PAPER_WEIGHT;
    }

    /**
     * Ürün bilgilerini ve hediye paket detaylarını gösterir.
     */
    @Override
    public void displayProductInfo() {
        super.displayProductInfo();
        System.out.printf("  + Hediye Paketi  : \"%s\" (+%.2f TL, +%.1f kg)%n",
                          giftMessage, GIFT_WRAP_PRICE, WRAP_PAPER_WEIGHT);
    }

    public String getGiftMessage() { return giftMessage; }
    public double getGiftWrapPrice() { return GIFT_WRAP_PRICE; }
}
