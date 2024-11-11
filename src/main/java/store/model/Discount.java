package store.model;

public class Discount {
    private int promotionDiscount;
    private int membershipDiscount;

    public void addPromotionDiscount(int promotionDiscount) {
        this.promotionDiscount += promotionDiscount;
    }
}
