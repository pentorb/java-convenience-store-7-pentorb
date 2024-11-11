package store.model;

import java.text.DecimalFormat;

public class Discount {
    private static final double PROPORTION_OF_MEMBERSHIP_DISCOUNT = 0.3;
    private static final String FORMAT_OF_DISCOUNT = "행사할인\t\t\t-%s\n멤버십할인\t\t\t-%s\n";
    private static final String FORMAT_OF_MONEY = "###,###";

    private int promotionDiscount;
    private int membershipDiscount;

    public void addPromotionDiscount(int promotionDiscount) {
        this.promotionDiscount += promotionDiscount;
    }

    public void applyMembershipDiscount(int price) {
        this.membershipDiscount = (int) (price * PROPORTION_OF_MEMBERSHIP_DISCOUNT);
    }

    public int addTotalDiscount() {
        return promotionDiscount + membershipDiscount;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_OF_MONEY);
        String styledPromotionDiscount = decimalFormat.format(promotionDiscount);
        String styledMembershipDiscount = decimalFormat.format(membershipDiscount);
        return String.format(FORMAT_OF_DISCOUNT, styledPromotionDiscount, styledMembershipDiscount);
    }
}
