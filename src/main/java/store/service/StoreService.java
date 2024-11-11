package store.service;

import store.model.*;

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
    private List<Gift> gifts;
    private Discount discount;

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

    public void buyProducts() {
        initiateDiscount();

        for (Order order : orders) {
            store.buyProduct(order);
            applyPromotionDiscount(order);
        }
    }

    private void initiateDiscount() {
        this.discount = new Discount();
        gifts = new ArrayList<>();
    }

    private void applyPromotionDiscount(Order order) {
        Product targetProduct = store.findProductByName(order.getProductName());
        Promotion targetPromotion = store.findPromotionByName(order.getProductName());

        int giftCount = targetPromotion.gainGiftCount(order);
        discount.addPromotionDiscount(giftCount * targetProduct.getPrice());
        gifts.add(new Gift(targetProduct.getName(), giftCount));
    }

    public Store getStore() {
        return store;
    }
}
