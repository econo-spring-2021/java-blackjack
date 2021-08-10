package domain;

import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;

    public static final int DEALER_CARDS_MINIMUM_SUM = 16;
    public static final int BLACKJACK_NUMBER = 21;

    public Players(ArrayList<Player> players) {
        this.players = players;
    }

    public void getTwoCards(Cards cards) {
        for (Player player : players) {
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
        }
    }

    public void addPlayerOneMoreCard(Cards cards, int i) {
        players.get(i).getCards().getCards().add(cards.giveCard(cards.getCards()));
    }

    public boolean checkDealerOneMoreCardNeeded(Cards cards) {
        Player dealer = players.get(0);
        if (dealer.getCards().getCardsSum() <= DEALER_CARDS_MINIMUM_SUM) {
            dealer.getCards().getCards().add(cards.giveCard(cards.getCards()));
            return true;
        }
        return false;
    }

    public int getDealerWinCount() {
        int dealerScore = BLACKJACK_NUMBER - players.get(0).getCards().getCardsSum();
        int dealerWinCount = 0;
        for (int i = 1; i < players.size(); i++) {
            players.get(i).setResult(dealerScore);
            dealerWinCount += players.get(i).getDealerWinCount();
        }
        return dealerWinCount;
    }

    public int getPlayersSize() {
        return players.size();
    }

    //Test용, View용
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
