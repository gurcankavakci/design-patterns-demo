package com.designpatterns.behavioral.template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TEMPLATE METHOD PATTERN - Sablon Metot Deseni
 *
 * TANIM: Bir operasyonun iskeletini ust sinifta tanimlar, bazi adimlari
 * alt siniflara birakir. Algoritma yapisi degismez, adimlar degisir.
 *
 * PROBLEM: Satis, stok, musteri raporlarinda benzer akis var:
 * Baslik -> Veri Topla -> Analiz -> Format -> Gonder
 * Her rapor bu adimlari farkli uygular.
 *
 * HOLLYWOOD PRiNSiBi: "Don't call us, we'll call you"
 * Ust sinif alt sinifi cagir (Inversion of Control).
 *
 * HOOK METOTLAR:
 * Alt sinifin override EDEBiLECEGi (zorunlu degil) metotlar.
 * Default davranis saglanir, alt sinif isterse override eder.
 *
 * STRATEGY vs TEMPLATE METHOD:
 * Strategy: Composition - tum algoritma degisir, runtime'da
 * Template Method: Inheritance - iskelet sabit, adimlar compile-time
 *
 * UML DIAGRAM:
 * +----------------------------------------------+
 * | <<abstract>> ReportTemplate                   |
 * +----------------------------------------------+
 * | + generateReport(): void  <- FINAL (template) |
 * | # abstract collectData(): void                |
 * | # abstract analyzeData(): void               |
 * | # abstract formatReport(): String            |
 * | # abstract getReportTitle(): String          |
 * | # hook shouldSendEmail(): boolean             |
 * | # hook includeCharts(): boolean               |
 * | - final printHeader(): void                  |
 * | - final sendReport(): void                   |
 * +--------------+-------------------------------+
 *                | extends
 *    +-----------+----------------+
 * SalesReport  InventoryReport  CustomerReport
 *
 * GERCEK HAYAT ANALOGISI:
 * Ise alim sureci: CV -> Mulakat -> Teklif -> Baslangic (iskelet sabit)
 * Her departman mulakatı farkli yapar (teknik/IK/operasyon)
 *
 * AVANTAJLAR:
 * + Kod tekrarini azaltir (DRY)
 * + Algoritma iskeletini korur
 * + Hook ile alt sinif esnekligi
 *
 * DEZAVANTAJLAR:
 * - Inheritance bagimliligi
 * - Template degistirmek tum alt siniflari etkiler
 */
public abstract class ReportTemplate {

    protected String generatedBy = "ShopEase Analytics Engine";
    protected LocalDateTime generatedAt;

    // TEMPLATE METHOD - final, alt sinif override EDEMEZ
    public final void generateReport() {
        generatedAt = LocalDateTime.now();
        printHeader();            // final
        collectData();            // abstract - alt sinif implement eder
        analyzeData();            // abstract
        String content = formatReport(); // abstract
        printContent(content);    // final
        if (includeCharts()) generateCharts(); // hook
        if (shouldSendEmail()) sendReport(content); // hook
        System.out.println("Rapor tamamlandi: " + getReportTitle());
    }

    // ABSTRACT METHODS - alt sinif MUTLAKA implement etmeli
    protected abstract void collectData();
    protected abstract void analyzeData();
    protected abstract String formatReport();
    protected abstract String getReportTitle();
    protected abstract String getReportType();

    // FINAL METHODS - degistirilemez
    private final void printHeader() {
        String line = "=".repeat(60);
        System.out.println("\n" + line);
        System.out.printf("  [RAPOR] %s%n", getReportTitle());
        System.out.printf("  Olusturan: %s%n", generatedBy);
        System.out.printf("  Tarih: %s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        System.out.println(line);
    }

    private final void printContent(String content) {
        System.out.println(content);
        System.out.println("=".repeat(60));
    }

    private final void sendReport(String content) {
        System.out.println("  [E-posta] " + getReportTitle() + " raporu gonderildi -> admin@shopease.com");
    }

    // HOOK METHODS - alt sinif override edebilir, default deger var
    protected boolean shouldSendEmail() { return false; }

    protected boolean includeCharts() { return false; }

    protected void generateCharts() {
        System.out.println("  [Grafik] " + getReportTitle() + " icin grafikler olusturuluyor...");
    }
}
