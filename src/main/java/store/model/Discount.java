package store.model;

public class Discount {
    private static final double PROPORTION_OF_MEMBERSHIP_DISCOUNT = 0.3;

    private int promotionDiscount;
    private int membershipDiscount;

    public void addPromotionDiscount(int promotionDiscount) {
        this.promotionDiscount += promotionDiscount;
    }

    public void applyMembershipDiscount(int price) {
        this.membershipDiscount = (int) (price * PROPORTION_OF_MEMBERSHIP_DISCOUNT);
    }
}
