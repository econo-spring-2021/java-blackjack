package blackjack;

import blackjack.controller.GameController;
import blackjack.domain.Game;

public class Application {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.play();
    }
}
