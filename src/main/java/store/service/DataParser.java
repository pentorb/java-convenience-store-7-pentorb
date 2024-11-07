package store.service;

import store.model.Product;
import store.model.Promotion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final int DATA_HEADER = 1;
    private static final String DELIMITER = ",";
    private static final String PATTERN_OF_DATE = "yyyy-MM-dd";
    private static final String NO_DATA = "null";
    private static final String EMPTY_PROMOTION = "";

    public static List<Product> parseProducts(List<String> rawData) {
        return rawData.stream()
                .skip(DATA_HEADER)
                .map(data -> {
                    String[] parts = data.split(DELIMITER);
                    if(parts[3].equals(NO_DATA)){
                        return new Product(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), EMPTY_PROMOTION);
                    }
                    return new Product(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3]);
                })
                .collect(Collectors.toList());
    }

    public static List<Promotion> parsePromotions(List<String> rawData) {
        return rawData.stream()
                .skip(DATA_HEADER)
                .map(data -> {
                    String[] parts = data.split(DELIMITER);
                    return new Promotion(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parseDate(parts[3]), parseDate(parts[4]));
                })
                .collect(Collectors.toList());
    }

    private static Date parseDate(String input){
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_OF_DATE);

        try {
            return formatter.parse(input);
        } catch (ParseException exception) {
            return null;
        }
    }
}
