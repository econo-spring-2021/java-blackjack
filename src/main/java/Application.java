import controller.Game;
import domain.Cards;
import domain.Players;
import view.InputView;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Players players = new Players(Game.setPlayerName(InputView.playerNameView()));
        Cards cards = new Cards(Cards.setCards());

        Game.setPlayersBettingMoney(players);
        Game.giveTwoCards(cards, players);
        Game.askPlayersOneMoreCard(cards, players);
        Game.getDealerOneMoreCard(cards, players);
        Game.showPlayersResults(players);
        Game.setWinOrLose(players);
    }
}
