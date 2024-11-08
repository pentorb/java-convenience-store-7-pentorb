package store.model;

public class Product {
    private static final String STRING_FORMAT_OF_PRODUCT_WITHOUT_PROMOTION = "- %s %d원 재고 없음 %s";
    private static final String STRING_FORMAT_OF_PRODUCT = "- %s %d원 %d개 %s";
    private static final int OUT_OF_STOCK = 0;

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

    @Override
    public String toString() {
        if (quantity == OUT_OF_STOCK) {
            return String.format(STRING_FORMAT_OF_PRODUCT_WITHOUT_PROMOTION, name, price, promotion);
        }
        return String.format(STRING_FORMAT_OF_PRODUCT, name, price, quantity, promotion);
    }

    public void decreaseQuantity(int number) {
        quantity -= number;
    }

    public String getName() {
        return name;
    }

    public String getPromotion() {
        return promotion;
    }

    public int getQuantity() {
        return quantity;
    }
}
