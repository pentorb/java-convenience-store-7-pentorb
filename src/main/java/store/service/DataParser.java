package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.model.Product;
import store.model.Promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DataParser {
    private static final int DATA_HEADER = 1;
    private static final String DELIMITER = ",";
    private static final String NO_DATA = "null";
    private static final String EMPTY_PROMOTION = "";
    private static final int ADDITIONAL_DAY = 1;

    public static List<Product> parseProducts(List<String> rawData) {
        return rawData.stream()
                .skip(DATA_HEADER)
                .map(data -> {
                    String[] parts = data.split(DELIMITER);
                    if (parts[3].equals(NO_DATA)) {
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
                    if(checkPromotionValidity(parts[3], parts[4])) {
                        return new Promotion(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    private static boolean checkPromotionValidity(String startDate, String endDate) {
        LocalDateTime today = DateTimes.now();
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate)
                .plusDays(ADDITIONAL_DAY);

        if (today.isAfter(parsedStartDate.atStartOfDay()) && today.isBefore(parsedEndDate.atStartOfDay())) {
            return true;
        }
        return false;
    }
}
