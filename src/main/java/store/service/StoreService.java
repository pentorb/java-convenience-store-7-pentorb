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
    private static final String EMPTY_PROMOTION = "";

    private final Store store;
    private List<Order> orders;
    private List<Gift> gifts;
    private Discount discount;
    private List<SalesRecord> salesRecords;
    private Receipt receipt;

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
            addSalesRecord(order);
            applyPromotionDiscount(order);
        }
    }

    private void initiateDiscount() {
        this.discount = new Discount();
        gifts = new ArrayList<>();
        salesRecords = new ArrayList<>();
    }

    private void addSalesRecord(Order order) {
        String targetName = order.getProductName();
        int targetQuantity = order.getQuantity();
        Product target = store.findProductByName(targetName);
        int targetPrice = targetQuantity * target.getPrice();

        salesRecords.add(new SalesRecord(targetName, targetQuantity, targetPrice));
    }

    private void applyPromotionDiscount(Order order) {
        Product targetProduct = store.findProductByName(order.getProductName());
        Promotion targetPromotion = store.findPromotionByName(order.getProductName());
        if(targetPromotion == null) {
            return;
        }

        int giftCount = targetPromotion.gainGiftCount(order);
        discount.addPromotionDiscount(giftCount * targetProduct.getPrice());
        gifts.add(new Gift(targetProduct.getName(), giftCount));
    }

    public void applyMembershipDiscount() {
        int validPrice = 0;

        for (Order order : orders) {
            Product targetProduct = store.findProductByName(order.getProductName());
            if(targetProduct.getPromotion().equals(EMPTY_PROMOTION)){
                validPrice += targetProduct.getPrice();
            }
        }
        discount.applyMembershipDiscount(validPrice);
    }

    public Receipt writeReceipt() {
        receipt = new Receipt();
        receipt.addRecords(salesRecords);
        receipt.addGifts(gifts);
        receipt.addDivider();
        receipt.addTotal(salesRecords);
        receipt.addDiscount(discount);
        receipt.addNetPrice(discount);
        return receipt;
    }

    public Store getStore() {
        return store;
    }
}
