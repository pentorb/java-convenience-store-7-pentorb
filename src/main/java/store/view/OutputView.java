package store.view;

import store.model.Store;

public class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.";
    private static final String INVENTORY_MESSAGE = "현재 보유하고 있는 상품입니다.\n";

    public void printWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printInventory(Store store) {
        System.out.println(INVENTORY_MESSAGE);
        System.out.println(store.convertInventoryToString());
    }
}
