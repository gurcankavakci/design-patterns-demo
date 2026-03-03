package com.designpatterns.behavioral.chain;

import com.designpatterns.model.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * STOK DOĞRULAYICI - Stock Validator
 *
 * <p>Chain of Responsibility zincirinin ilk halkası.
 * Siparişteki tüm ürünlerin stokta yeterli miktarda olup olmadığını kontrol eder.</p>
 *
 * <p>Stok veritabanını simüle eden bir HashMap kullanır.
 * Gerçek uygulamada bu, bir StockRepository veya StockService olurdu.</p>
 *
 * <p><b>Sorumluluk:</b> Yalnızca stok kontrolü. Başka hiçbir şeyle ilgilenmez.</p>
 */
public class StockValidator extends OrderValidator {

    /** Simüle edilmis stok veritabanı: productId -> stok miktarı */
    private Map<String, Integer> stockDatabase;

    /**
     * StockValidator constructor'ı.
     * Örnek stok verileriyle başlatır.
     */
    public StockValidator() {
        this.stockDatabase = new HashMap<>();
        // Simüle edilmis stok veritabanı
        stockDatabase.put("LAPTOP-001", 10);
        stockDatabase.put("PHONE-001", 50);
        stockDatabase.put("HEADSET-001", 5);
        stockDatabase.put("TSHIRT-001", 100);
        stockDatabase.put("BOOK-001", 30);
        stockDatabase.put("TABLET-001", 8);
        stockDatabase.put("CAMERA-001", 3);
    }

    @Override
    public String getValidatorName() {
        return "Stok Doğrulayıcı";
    }

    /**
     * Siparişteki tüm ürünlerin stok durumunu kontrol eder.
     *
     * <p>Her ürün için:</p>
     * <ul>
     *   <li>Stok veritabanından mevcut miktarı alır</li>
     *   <li>İstenen miktarla karşılaştırır</li>
     *   <li>Yetersizse hata mesajı ekler ve onayı reddeder</li>
     * </ul>
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Zincirde bir sonraki validator'ın sonucu
     */
    @Override
    public boolean validate(OrderRequest request) {
        System.out.println("=== [" + getValidatorName() + "] Kontrol ediliyor...");

        boolean stockOk = true;

        for (Map.Entry<Product, Integer> entry : request.getOrder().getItems().entrySet()) {
            Product product = entry.getKey();
            int requestedQuantity = entry.getValue();
            int available = stockDatabase.getOrDefault(product.getId(), 0);

            if (available < requestedQuantity) {
                request.addMessage("STOK HATASI: " + product.getName()
                        + " stokta yok (mevcut: " + available
                        + ", istenen: " + requestedQuantity + ")");
                request.setApproved(false);
                stockOk = false;
                System.out.println("  HATA: Yetersiz stok: " + product.getName()
                        + " (mevcut: " + available + ", istenen: " + requestedQuantity + ")");
            } else {
                System.out.println("  Stok yeterli: " + product.getName()
                        + " (mevcut: " + available + ", istenen: " + requestedQuantity + ")");
            }
        }

        if (stockOk) {
            System.out.println("  [" + getValidatorName() + "] Tum stok kontrolleri gecti.");
        } else {
            System.out.println("  [" + getValidatorName() + "] Stok kontrolu BASARISIZ.");
        }

        return passToNext(request);
    }

    /**
     * Stok veritabanını günceller (test için).
     *
     * @param productId Ürün kimliği
     * @param quantity  Yeni stok miktarı
     */
    public void updateStock(String productId, int quantity) {
        stockDatabase.put(productId, quantity);
    }

    /**
     * Bir ürünün stok miktarını döndürür.
     *
     * @param productId Ürün kimliği
     * @return Stok miktarı
     */
    public int getStock(String productId) {
        return stockDatabase.getOrDefault(productId, 0);
    }
}
