package store.model;

public class Gift {
    private static final String STRING_FORMAT_OF_GIFT = "%s\t\t%d\n";
    private final String name;
    private final int count;

    public Gift(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT_OF_GIFT, name, count);
    }
}
