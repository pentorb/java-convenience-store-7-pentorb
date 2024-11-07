package store.model;

public class Product {
    private final String name;
    private final int price;
    private final String promotion;
    private int quantity;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }
}
