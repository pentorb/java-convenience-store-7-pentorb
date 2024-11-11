package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ITEM_DIRECTION = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String MEMBERSHIP_DIRECTION = "멤버십 할인을 받으시겠습니까? (Y/N)";

    public String readItem() {
        System.out.println(ITEM_DIRECTION);
        return Console.readLine();
    }

    public String readMembership() {
        System.out.println(MEMBERSHIP_DIRECTION);
        return Console.readLine();
    }
}
