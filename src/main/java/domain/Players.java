package domain;

import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;

    public static final int DEALER_CARDS_MINIMUM_SUM = 16;
    public static final int BLACKJACK_NUMBER = 21;

    public Players(ArrayList<Player> players) {
        this.players = players;
    }

    public void getTwoCards(Cards cardSet) {
        for (Player player : players) {
            player.getPlayerCards().addCard(cardSet.getCardsList());
            player.getPlayerCards().addCard(cardSet.getCardsList());
        }
    }

    public void addPlayerOneMoreCard(Cards cardSet, int i) {
        players.get(i).getPlayerCards().addCard(cardSet.getCardsList());
    }

    public boolean checkDealerOneMoreCardNeeded(Cards cardSet) {
        Player dealer = players.get(0);
        if (dealer.getPlayerCards().getCardsSum() <= DEALER_CARDS_MINIMUM_SUM) {
            dealer.getPlayerCards().addCard(cardSet.getCardsList());
            return true;
        }
        return false;
    }

    /*
    public int getDealerWinCount() {
        int dealerScore = BLACKJACK_NUMBER - players.get(0).getPlayerCards().getCardsSum();
        int dealerWinCount = 0;
        for (int i = 1; i < players.size(); i++) {
            players.get(i).setCardsSum(dealerScore);
            //dealerWinCount += players.get(i).getDealerWinCount();
        }
        return dealerWinCount;
    }*/

    public int getPlayersSize() {
        return players.size();
    }

    public void setPlayerBettingMoney(int index, int bettingMoney) {
        players.get(index).setBettingMoney(bettingMoney);
    }

    public String getPlayerName(int index) {
        return players.get(index).getName();
    }

    public int checkPlayerCardsSum(int index) {
        return players.get(index).calculateCardsSum();
    }

    public void checkPlayerBlackJack(int dealerCardSum, int index) {
        int dealerMoney = players.get(index).checkBlackJackFirst(dealerCardSum);
        players.get(0).addDealerProfit(dealerMoney);
    }

    public void checkPlayerProfit(int dealerCardSum, int index) {
        int dealerMoney = players.get(index).calculateBettingMoney(dealerCardSum);
        players.get(0).addDealerProfit(dealerMoney);
    }

    //Test용, View용
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
