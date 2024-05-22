import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilterCSV {

    public static void main(String[] args) {
        // Cesta k CSV souboru
        String inputFilePath = "C:\\Users\\Albert\\Desktop\\Geo.csv";
        String outputFilePath = "C:\\Users\\Albert\\Desktop\\GeoFilter.csv";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CZ")) {
                    writer.write(line + ",13000000\n");
                } else if (line.contains("SK")) {
                    writer.write(line + ",5500000\n");
                } else {
                    writer.write(line + "\n");
                }
            }
            System.out.println("Filtrování a kopírování bylo úspěšně dokončeno.");
        } catch (IOException e) {
            System.out.println("Chyba při čtení/zápisu souboru: " + e.getMessage());
            e.printStackTrace();
        }
    }
}