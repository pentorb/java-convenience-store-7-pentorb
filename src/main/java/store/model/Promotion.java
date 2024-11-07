package store.model;

import java.util.Date;

public class Promotion {
    private final String name;
    private final int buyCount;
    private final int getCount;
    private final Date startDate;
    private final Date endDate;

    public Promotion(String name, int buyCount, int getCount, Date startDate, Date endDate) {
        this.name = name;
        this.buyCount = buyCount;
        this.getCount = getCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
