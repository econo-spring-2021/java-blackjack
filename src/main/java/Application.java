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

        Game.giveTwoCards(players, cards);
        Game.askPlayersOneMoreCard(players, cards);
        Game.getDealerOneMoreCard(players, cards);

        OutputView.playersResultView(players);
    }
}
