package com.designpatterns.creational.singleton;

/**
 * SINGLETON PATTERN - Tek Nesne Deseni
 *
 * TANIM: Bir sınıfın yalnızca bir instance'ına sahip olmasını sağlar ve
 * bu instance'a global bir erişim noktası sunar.
 *
 * PROBLEM: Uygulama genelinde paylaşılan bir kaynağa (veritabanı bağlantısı,
 * configuration, log dosyası) erişimi kontrol etmek istiyoruz.
 *
 * COZUM: Sınıf kendi instance'ını oluşturur ve dışarıya statik bir
 * erişim metodu sunar.
 *
 * GERCEK HAYAT ANALOGISI:
 * Bir ülkede sadece bir cumhurbaşkanı olabilir. Yeni cumhurbaşkanı
 * istendiğinde, mevcut cumhurbaşkanına referans döner.
 *
 * NE ZAMAN KULLANILIR:
 * - Uygulama genelinde tek bir instance gerektiğinde
 * - Paylaşılan kaynakları kontrol etmek için (DB pool, Thread pool)
 * - Global konfigürasyon yönetimi
 * - Logging servisleri
 *
 * UML DIAGRAM:
 * ┌──────────────────────────────┐
 * │      DatabaseConnection      │
 * ├──────────────────────────────┤
 * │ - instance: DatabaseConnection│ (static, volatile)
 * │ - connectionString: String   │
 * │ - maxConnections: int        │
 * ├──────────────────────────────┤
 * │ - DatabaseConnection()       │ (private constructor)
 * │ + getInstance(): DatabaseConnection │ (static)
 * │ + getConnection(): String    │
 * │ + releaseConnection(): void  │
 * │ + executeQuery(): String     │
 * └──────────────────────────────┘
 *
 * AVANTAJLAR:
 * + Tek instance garantisi
 * + Global erişim noktası
 * + Lazy initialization (gerektiğinde oluşturulur)
 * + Kaynak tasarrufu
 *
 * DEZAVANTAJLAR:
 * - Unit test yazmayı zorlaştırır (mock yapmak zor)
 * - Single Responsibility Principle ihlali olabilir
 * - Multi-threaded ortamda dikkat gerektirir
 * - Global state sorunlarına yol açabilir
 *
 * THREAD SAFETY:
 * Double-checked locking + volatile kullanımı:
 * - İlk null check: synchronized bloğuna girmemek için (performans)
 * - synchronized blok: Thread-safe instance oluşturma
 * - İkinci null check: Race condition önleme
 * - volatile: CPU cache'leme sorunlarını önler
 *
 * ALTERNATIFLER:
 * - Eager initialization: private static final instance = new DatabaseConnection()
 * - Enum Singleton: En güvenli yol (Joshua Bloch önerisi)
 * - Bill Pugh Singleton: Static inner class kullanımı
 *
 * ---
 *
 * SINGLETON PATTERN - Single Object Pattern
 *
 * DEFINITION: Ensures a class has only one instance and provides
 * a global access point to that instance.
 *
 * PROBLEM: We want to control access to a shared resource
 * (database connection, configuration, log file) across the application.
 *
 * SOLUTION: The class creates its own instance and exposes a static
 * access method to the outside.
 */
public class DatabaseConnection {

    // volatile keyword: Değişkenin her thread tarafından ana bellekten okunmasını sağlar.
    // CPU cache'leme nedeniyle farklı thread'lerin eski değerleri görmesini önler.
    private static volatile DatabaseConnection instance;

    private final String connectionString = "jdbc:mysql://shopease-db:3306/shopease";
    private final int maxConnections = 10;
    private int activeConnections = 0;
    private int totalQueriesExecuted = 0;

    /**
     * Private constructor - Dışarıdan nesne oluşturulmasını engeller.
     * Bağlantı havuzunu başlatma işlemini simüle eder.
     */
    private DatabaseConnection() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   Veritabanı bağlantı havuzu başlatılıyor...  ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("  Bağlantı dizesi : " + connectionString);
        System.out.println("  Maksimum bağlantı: " + maxConnections);
        System.out.println("  Havuz durumu     : HAZIR");
        System.out.println("  [Singleton] Bu mesaj yalnızca BİR KEZ görünmeli!");
        System.out.println();
    }

    /**
     * Double-Checked Locking ile thread-safe Singleton erişimi.
     *
     * Neden iki kez null kontrolü?
     * 1. Birinci kontrol (synchronized dışında): Performans için.
     *    Instance zaten varsa synchronized bloğuna girme maliyetinden kaçınır.
     * 2. synchronized blok: Aynı anda yalnızca bir thread girebilir.
     * 3. İkinci kontrol (synchronized içinde): Race condition önleme.
     *    İlk kontrolden sonra başka bir thread instance oluşturmuş olabilir.
     *
     * @return DatabaseConnection tek instance
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {                      // İlk kontrol - synchronized dışında (performans)
            synchronized (DatabaseConnection.class) { // Kritik bölge - sadece bir thread girer
                if (instance == null) {              // İkinci kontrol - race condition önleme
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Bağlantı havuzundan bir bağlantı alır.
     * Maksimum bağlantı sınırını kontrol eder.
     *
     * @return Bağlantı kimliği string'i
     */
    public synchronized String getConnection() {
        if (activeConnections >= maxConnections) {
            System.out.println("  [UYARI] Bağlantı havuzu dolu! Maksimum " + maxConnections + " bağlantı aşıldı.");
            return null;
        }
        activeConnections++;
        String connectionId = "CONN-" + System.currentTimeMillis() + "-" + activeConnections;
        System.out.println("  [DB] Bağlantı alındı (aktif: " + activeConnections + "/" + maxConnections + ") → " + connectionId);
        return connectionId;
    }

    /**
     * Kullanılan bağlantıyı havuza geri bırakır.
     *
     * @param conn Geri bırakılacak bağlantı kimliği
     */
    public synchronized void releaseConnection(String conn) {
        if (conn == null) {
            System.out.println("  [UYARI] Geçersiz bağlantı serbest bırakılamaz.");
            return;
        }
        if (activeConnections > 0) {
            activeConnections--;
            totalQueriesExecuted++;
        }
        System.out.println("  [DB] Bağlantı serbest bırakıldı → " + conn +
                " (aktif: " + activeConnections + "/" + maxConnections + ")");
    }

    /**
     * Verilen SQL sorgusunu simüle ederek çalıştırır.
     *
     * @param sql Çalıştırılacak SQL sorgusu
     * @return Sorgu sonucu (simüle edilmiş)
     */
    public String executeQuery(String sql) {
        String conn = getConnection();
        if (conn == null) {
            return "HATA: Bağlantı alınamadı";
        }
        System.out.println("  [DB] Sorgu çalıştırıldı: " + sql);
        String result = "ResultSet{rows=5, columns=3, query=" + sql.substring(0, Math.min(sql.length(), 30)) + "...}";
        releaseConnection(conn);
        return result;
    }

    /**
     * Bağlantı havuzu istatistiklerini döndürür.
     *
     * @return İstatistik bilgisi string olarak
     */
    public String getStats() {
        return String.format(
                "DatabaseConnection İstatistikleri → " +
                "Aktif Bağlantı: %d/%d | " +
                "Toplam Sorgu: %d | " +
                "Bağlantı: %s",
                activeConnections, maxConnections,
                totalQueriesExecuted,
                connectionString
        );
    }

    /**
     * Bağlantı dizesini döndürür.
     *
     * @return JDBC bağlantı dizesi
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Aktif bağlantı sayısını döndürür.
     *
     * @return Aktif bağlantı sayısı
     */
    public int getActiveConnections() {
        return activeConnections;
    }

    /**
     * Toplam çalıştırılan sorgu sayısını döndürür.
     *
     * @return Toplam sorgu sayısı
     */
    public int getTotalQueriesExecuted() {
        return totalQueriesExecuted;
    }

    /**
     * Maksimum bağlantı sayısını döndürür.
     *
     * @return Maksimum bağlantı kapasitesi
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "connectionString='" + connectionString + '\'' +
                ", maxConnections=" + maxConnections +
                ", activeConnections=" + activeConnections +
                ", totalQueriesExecuted=" + totalQueriesExecuted +
                '}';
    }
}
