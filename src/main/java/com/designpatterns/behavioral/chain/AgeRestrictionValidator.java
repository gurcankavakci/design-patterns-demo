package com.designpatterns.behavioral.chain;

import com.designpatterns.model.Customer;
import com.designpatterns.model.Product;

import java.util.Map;

/**
 * YAŞ KISITLAMASI DOĞRULAYICI - Age Restriction Validator
 *
 * <p>Chain of Responsibility zincirinin üçüncü halkası.
 * Yaşa özel kategorilerdeki ürünler için müşterinin yaş uygunluğunu kontrol eder.</p>
 *
 * <p>Bazı ürün kategorileri yasal olarak yaş kısıtlamasına tabidir:
 * Alkol, tütün, silah aksesuarları vb. Bu validator bu kısıtlamaları uygular.</p>
 *
 * <p><b>Sorumluluk:</b> Yalnızca yaş kısıtlaması kontrolü.</p>
 */
public class AgeRestrictionValidator extends OrderValidator {

    /**
     * Yaş kısıtlamalı kategoriler ve minimum yaş gereksinimleri.
     * Key: Kategori adı, Value: Minimum yaş
     */
    private Map<String, Integer> ageRestrictedCategories = Map.of(
            "Alkol", 18,
            "Silah Aksesuarlari", 21,
            "Tutun", 18,
            "Kumar Gereçleri", 18
    );

    @Override
    public String getValidatorName() {
        return "Yaş Kısıtlama Doğrulayıcı";
    }

    /**
     * Siparişteki ürünlerin yaş kısıtlamalarını kontrol eder.
     *
     * <p>Her ürün için:</p>
     * <ol>
     *   <li>Kategorisi yaş kısıtlamalı mı kontrol eder</li>
     *   <li>Müşterinin yaşını minimum yaşla karşılaştırır</li>
     *   <li>Uygunsuzsa hata mesajı ekler ve onayı reddeder</li>
     * </ol>
     *
     * @param request Doğrulanacak sipariş isteği
     * @return Zincirde bir sonraki validator'ın sonucu
     */
    @Override
    public boolean validate(OrderRequest request) {
        System.out.println("=== [" + getValidatorName() + "] Kontrol ediliyor...");

        Customer customer = request.getCustomer();
        boolean ageOk = true;

        for (Map.Entry<Product, Integer> entry : request.getOrder().getItems().entrySet()) {
            Product product = entry.getKey();
            String category = product.getCategory();

            if (ageRestrictedCategories.containsKey(category)) {
                int requiredAge = ageRestrictedCategories.get(category);

                if (customer.getAge() < requiredAge) {
                    request.addMessage("YAS HATASI: " + customer.getName()
                            + " için " + category + " alımı yaş kısıtlı"
                            + " (gerekli: " + requiredAge + "+, mevcut: " + customer.getAge() + ")");
                    request.setApproved(false);
                    ageOk = false;
                    System.out.println("  HATA: " + customer.getName()
                            + " yas kısıtlı kategori: " + category
                            + " (gerekli: " + requiredAge + "+, mevcut: " + customer.getAge() + ")");
                } else {
                    System.out.println("  Yas uygun: " + customer.getAge()
                            + " >= " + requiredAge + " (" + category + ")");
                }
            }
            // Yaş kısıtlaması olmayan kategoriler için bildirim gerekmez
        }

        if (ageOk) {
            System.out.println("  [" + getValidatorName() + "] Tum yas kontrolleri gecti.");
        } else {
            System.out.println("  [" + getValidatorName() + "] Yas kontrolu BASARISIZ.");
        }

        return passToNext(request);
    }

    /**
     * Yaş kısıtlamalı kategorileri döndürür.
     *
     * @return Yaş kısıtlamalı kategoriler ve minimum yaşlar
     */
    public Map<String, Integer> getAgeRestrictedCategories() {
        return ageRestrictedCategories;
    }
}
