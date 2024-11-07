package store;

import store.controller.ConvenienceStoreController;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        StoreService storeService = new StoreService();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        ConvenienceStoreController convenienceStoreController = new ConvenienceStoreController(storeService, inputView, outputView);
        convenienceStoreController.run();
    }
}
