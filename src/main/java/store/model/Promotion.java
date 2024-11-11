package store.model;

public class Promotion {
    private final String name;
    private final int buyCount;
    private final int getCount;

    public Promotion(String name, int buyCount, int getCount) {
        this.name = name;
        this.buyCount = buyCount;
        this.getCount = getCount;
    }

    public String getName() {
        return name;
    }

    public int gainGiftCount(Order order) {
        if(order.getQuantity() >= buyCount && order.getQuantity() % buyCount == 0) {
            return (order.getQuantity() / (buyCount + getCount)) * getCount;
        }
        return 0;
    }
}
