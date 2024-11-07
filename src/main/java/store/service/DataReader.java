package store.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataReader {
    private static final String LOCATION_OF_PRODUCTS = "./src/main/resources/products.md";
    private static final String LOCATION_OF_PROMOTIONS = "./src/main/resources/promotions.md";

    public static List<String> readProducts() {
        return readFile(LOCATION_OF_PRODUCTS);
    }

    public static List<String> readPromotions() {
        return readFile(LOCATION_OF_PROMOTIONS);
    }

    private static List<String> readFile(String location) {
        try {
            List<String> data = new ArrayList<>();
            FileReader fileReader = new FileReader(location);
            Scanner scan = new Scanner(fileReader);
            while (scan.hasNextLine()) {
                data.add(scan.nextLine());
            }
            scan.close();
            return data;
        } catch (FileNotFoundException exception) {
            return null;
        }
    }
}
