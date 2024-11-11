package store.model;

import java.text.DecimalFormat;

public class SalesRecord {
    private static final String STRING_FORMAT_OF_SALES_RECORD = "%s\t\t%d \t%s\n";
    private static final String FORMAT_OF_MONEY = "###,###";

    private String ProductName;
    private int countOfProduct;
    private int price;

    public SalesRecord(String ProductName, int countOfProduct, int price) {
        this.ProductName = ProductName;
        this.countOfProduct = countOfProduct;
        this.price = price;
    }

    public int getCountOfProduct() {
        return countOfProduct;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(FORMAT_OF_MONEY);
        String styledPrice = decimalFormat.format(price);
        return String.format(STRING_FORMAT_OF_SALES_RECORD, ProductName, countOfProduct, styledPrice);
    }
}
