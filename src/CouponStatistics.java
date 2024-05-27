import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CouponStatistics {

    public static void main(String[] args) {
        // Kontrola argumentů
        if (args.length < 1) {
            System.out.println("Usage: java CouponStatistics <country> [company]");
            return;
        }

        String country = args[0];
        String company = args.length > 1 ? args[1] : null;
        String inputFilePath = "C:\\Users\\Albert\\Desktop\\Geo.csv";

        Map<String, Integer> regionCouponCount = new HashMap<>();
        int totalCoupons = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Rozdělíme řádky pomocí středníku jako oddělovače
                String[] fields = line.split(";");
                if (fields.length < 13) continue;

                String fileCountry = fields[12];
                String region = fields[11];
                String fileCompany = fields[4];

                int coupons = 1;

                if (!fileCountry.equalsIgnoreCase(country)) continue;
                if (company != null && !fileCompany.equalsIgnoreCase(company)) continue;

                regionCouponCount.put(region, regionCouponCount.getOrDefault(region, 0) + coupons);
                totalCoupons += coupons;
            }

            System.out.println("Statistics for country: " + country + (company != null ? " and company: " + company : ""));
            for (Map.Entry<String, Integer> entry : regionCouponCount.entrySet()) {
                String region = entry.getKey();
                int coupons = entry.getValue();
                double percentage = (coupons / (double) totalCoupons) * 100;
                System.out.printf("Region: %s, Coupons: %d, Percentage: %.2f%%\n", region, coupons, percentage);
            }

        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
            e.printStackTrace();
        }
    }
}