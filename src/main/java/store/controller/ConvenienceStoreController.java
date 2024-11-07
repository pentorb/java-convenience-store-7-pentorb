package store.controller;

import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;


public class ConvenienceStoreController {
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
    }

    private void loadInventory() {
        outputView.printWelcome();
        outputView.printInventory(storeService.getStore());
    }

    private void readOrder() {
        String input = inputView.readItem();
        storeService.receiveOrder(input);
    }
}
