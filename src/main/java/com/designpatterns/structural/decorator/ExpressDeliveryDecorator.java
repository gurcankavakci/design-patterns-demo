package com.designpatterns.structural.decorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * EXPRESS DELIVERY DECORATOR - Ekspres Teslimat Dekoratörü (ConcreteDecorator)
 *
 * <p>{@link ProductDecorator}'dan türeyen somut dekoratör.
 * Ürüne aynı gün ekspres teslimat özelliği ekler.
 *
 * <p>Ek ücret 45 TL olup ağırlığa da etki etmez (sadece teslimat seçeneğidir).
 * Teslimat tarihi otomatik olarak bugünkü tarih üzerinden hesaplanır.
 *
 * <p><b>KULLANIM ÖRNEĞI:</b>
 * <pre>
 * ShopProduct product = new BasicProduct(rawProduct);
 * product = new ExpressDeliveryDecorator(product); // Bugün teslimat +45 TL
 * </pre>
 *
 * @author ShopEase Dev Team
 * @version 1.0
 */
public class ExpressDeliveryDecorator extends ProductDecorator {

    /** Ekspres teslimat ücreti (TL) */
    private static final double EXPRESS_COST = 45.0;

    /** Teslimat tarihi ve saat bilgisi */
    private final String deliveryDate;

    /**
     * Ekspres teslimat dekoratörü oluşturur.
     * Teslimat tarihi constructor'da bugünkü tarih olarak belirlenir.
     *
     * @param product Wrap edilecek ürün
     */
    public ExpressDeliveryDecorator(ShopProduct product) {
        super(product);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.deliveryDate = today + " - bugün 18:00'e kadar";
    }

    /**
     * Ürün adına ekspres etiketini ekler.
     *
     * @return "[EKSPRES]" eklenmiş ürün adı
     */
    @Override
    public String getName() {
        return wrappedProduct.getName() + " [EKSPRES]";
    }

    /**
     * Baz fiyata ekspres teslimat ücretini ekler.
     *
     * @return Baz fiyat + 45 TL
     */
    @Override
    public double getPrice() {
        return wrappedProduct.getPrice() + EXPRESS_COST;
    }

    /**
     * Açıklamaya ekspres teslimat bilgisini ekler.
     *
     * @return Genişletilmiş açıklama
     */
    @Override
    public String getDescription() {
        return wrappedProduct.getDescription() + " | Ekspres teslimat: " + deliveryDate;
    }

    /**
     * Teslimat seçeneği ağırlığı değiştirmez.
     *
     * @return Baz ağırlık (değişmez)
     */
    @Override
    public double getWeight() {
        return wrappedProduct.getWeight();
    }

    /**
     * Ürün bilgilerini ve ekspres teslimat detaylarını gösterir.
     */
    @Override
    public void displayProductInfo() {
        super.displayProductInfo();
        System.out.printf("  + Ekspres Teslimat: %s (+%.2f TL)%n", deliveryDate, EXPRESS_COST);
    }

    public String getDeliveryDate() { return deliveryDate; }
    public double getExpressCost() { return EXPRESS_COST; }
}
