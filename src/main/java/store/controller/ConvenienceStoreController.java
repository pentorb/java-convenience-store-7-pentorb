package store.controller;

import store.model.Receipt;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;


public class ConvenienceStoreController {
    private static final String AGREEMENT_COMMAND = "Y";

    private final StoreService storeService;
    private final InputView inputView;
    private final OutputView outputView;

    public ConvenienceStoreController(StoreService storeService, InputView inputView, OutputView outputView) {
        this.storeService = storeService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        loadInventory();
        readOrder();
        processOrders();
        decideMembershipDiscount();
        showReceipt();
    }

    private void loadInventory() {
        outputView.printWelcome();
        outputView.printInventory(storeService.getStore());
    }

    private void readOrder() {
        String input = inputView.readItem();
        storeService.receiveOrder(input);
    }

    private void decideMembershipDiscount() {
        String command = inputView.readMembership();
        if (command.equals(AGREEMENT_COMMAND)) {
            storeService.applyMembershipDiscount();
        }
    }

    private void processOrders() {
        storeService.buyProducts();
    }

    private void showReceipt() {
        outputView.printReceipt(storeService.writeReceipt());
    }
}
