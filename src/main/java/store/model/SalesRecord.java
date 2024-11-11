package store.model;

public class SalesRecord {
    private String ProductName;
    private int countOfProduct;
    private int price;

    public SalesRecord(String ProductName, int countOfProduct, int price) {
        this.ProductName = ProductName;
        this.countOfProduct = countOfProduct;
        this.price = price;
    }
}
