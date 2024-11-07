package store.model;

public class Order {
    private final String productName;
    private final int quantity;

    public Order(String productName, String quantity) {
        this.productName = productName;
        this.quantity = Integer.parseInt(quantity);
    }
}
