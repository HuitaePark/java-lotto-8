package lotto.presentation.ui;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String inputText() {
        return Console.readLine();
    }

    public void closeConsole() {
        Console.close();
    }
}
