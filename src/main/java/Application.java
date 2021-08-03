import controller.Game;
import domain.Cards;
import domain.Players;
import view.InputView;
import view.OutputView;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Players players = new Players(Game.setPlayerName(InputView.playerNameView()));
        Cards cards = new Cards(Cards.setCards());

        players.giveTwoCards(cards);
        players.askPlayersOneMoreCard(cards);
        players.getDealerOneMoreCard(cards);

        players.showPlayersResults();
        players.setWinOrLose();
    }
}
