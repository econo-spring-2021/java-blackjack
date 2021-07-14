import controller.Game;
import domain.Cards;
import domain.Players;
import view.InputView;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Players players = new Players(Game.setPlayerName(InputView.playerNameView()));
        Cards cards = new Cards(Cards.setCards());

        InputView.giveTwoCardsView(players);
        Game.giveTwoCards(players, cards);
        InputView.playersTwoCardsView(players);

        Game.askPlayersOneMoreCard(players, cards);

    }
}
