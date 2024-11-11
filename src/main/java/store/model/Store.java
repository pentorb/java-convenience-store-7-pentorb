package store.model;

import java.util.List;

public class Store {
    private static final String LINE_SEPARATOR = "\n";
    private static final String EMPTY_PROMOTION = "";
    private static final int OUT_OF_STOCK = 0;

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

    public Product findProductByName(String name) {
        for (Product product : inventory) {
            if (product.getName().equals(name) && !product.getPromotion().equals(EMPTY_PROMOTION) && product.getQuantity() != OUT_OF_STOCK) {
                return product;
            } else if (product.getName().equals(name) && product.getPromotion().equals(EMPTY_PROMOTION)) {
                return product;
            }
        }
        return null;
    }

    public Promotion findPromotionByName(String name) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equals(name)) {
                return promotion;
            }
        }
        return null;
    }

    public void buyProduct(Order order) {
        Product targetProduct = findProductByName(order.getProductName());
        targetProduct.decreaseQuantity(order.getQuantity());
    }
}
