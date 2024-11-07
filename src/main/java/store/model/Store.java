package store.model;

import java.util.List;

public class Store {
    private static final String LINE_SEPARATOR = "\n";
    
    private final List<Product> inventory;
    private final List<Promotion> promotions;

    public Store(List<Product> products, List<Promotion> promotions) {
        inventory = products;
        this.promotions = promotions;
    }

    public String convertInventoryToString() {
        String result = "";

        for (Product product : inventory) {
            result += product.toString() + LINE_SEPARATOR;
        }
        return result;
    }
}
