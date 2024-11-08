package store.service;

import store.model.Order;
import store.model.Product;
import store.model.Promotion;
import store.model.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StoreService {
    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";
    private static final String ESCAPED_BRACKETS_REGEX = "[\\[\\]]";
    private static final String REPLACED_STRING = "";

    private final Store store;
    private List<Order> orders;

    public StoreService() {
        this.store = openStore();
    }

    private Store openStore() {
        List<String> productsData = DataReader.readProducts();
        List<String> promotionsData = DataReader.readPromotions();
        List<Product> products = DataParser.parseProducts(productsData);
        List<Promotion> promotions = DataParser.parsePromotions(promotionsData);
        return new Store(products, promotions);
    }

    public void receiveOrder(String input) {
        if (input.contains(FIRST_DELIMITER)) {
            orders = parseMultipleOrders(input);
            return;
        }

        orders = new ArrayList<>();
        Order order = parseSingleOrder(input);
        orders.add(order);
    }

    private List<Order> parseMultipleOrders(String input) {
        return Arrays.stream(input.split(FIRST_DELIMITER))
                .map(this::parseSingleOrder)
                .collect(Collectors.toList());
    }

    private Order parseSingleOrder(String input) {
        String processedInput = input.replaceAll(ESCAPED_BRACKETS_REGEX, REPLACED_STRING);
        String[] parts = processedInput.split(SECOND_DELIMITER);
        return new Order(parts[0], parts[1]);
    }

    public Store getStore() {
        return store;
    }
}