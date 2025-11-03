package lotto.presentation.ui;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public void closeConsole() {
        Console.close();
    }

    public String readPurchaseAmount() {
        System.out.println(InfoMessage.ASK_AMOUNT.getMessage());
        return Console.readLine();
    }

    public String readWinningNumber() {
        System.out.println(InfoMessage.ENTER_WINNING.getMessage());
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println(InfoMessage.ENTER_BONUS.getMessage());
        return Console.readLine();
    }
}
