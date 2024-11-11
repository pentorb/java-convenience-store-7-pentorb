package store.model;

import store.service.StoreService;

import java.text.DecimalFormat;
import java.util.List;

public class Receipt {
    private static final String RECEIPT_HEADER = "==============W 편의점================\n상품명\t\t수량\t금액\n";
    private static final String GIFT_HEADER = "=============증\t정===============\n";
    private static final String DIVIDER = "====================================\n";
    private static final String FORMAT_OF_TOTAL = "총구매액\t\t%d\t%s\n";
    private static final String FORMAT_OF_MONEY = "###,###";
    private static final String FORMAT_OF_NET_PRICE = "내실돈\t\t\t %s\n";

    private final DecimalFormat decimalFormat;
    private String receipt;
    private int totalCount;
    private int totalPrice;

    public Receipt() {
        this.receipt = RECEIPT_HEADER;
        decimalFormat = new DecimalFormat(FORMAT_OF_MONEY);
    }

    public void addRecords(List<SalesRecord> salesRecords) {
        for (SalesRecord salesRecord : salesRecords) {
            receipt += salesRecord.toString();
        }
    }

    public void addGifts(List<Gift> gifts) {
        receipt += GIFT_HEADER;
        for (Gift gift : gifts) {
            receipt += gift.toString();
        }
    }

    public void addDivider() {
        receipt += DIVIDER;
    }

    public void addTotal(List<SalesRecord> salesRecords) {
        totalCount = 0;
        totalPrice = 0;

        for (SalesRecord salesRecord : salesRecords) {
            totalCount += salesRecord.getCountOfProduct();
            totalPrice += salesRecord.getPrice();
        }

        String styledPrice = decimalFormat.format(totalPrice);
        receipt += String.format(FORMAT_OF_TOTAL, totalCount, styledPrice);
    }

    public void addDiscount(Discount discount) {
        receipt += discount.toString();
    }

    public void addNetPrice(Discount discount) {
        String styledDiscount = decimalFormat.format(totalPrice - discount.addTotalDiscount());
        receipt += String.format(FORMAT_OF_NET_PRICE, styledDiscount);
    }

    public String getReceipt() {
        return receipt;
    }
}
