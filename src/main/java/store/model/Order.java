package store.model;

public class Order {
    private final String productName;
    private final int quantity;

    public Order(String productName, String quantity) {
        this.productName = productName;
        this.quantity = Integer.parseInt(quantity);
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
