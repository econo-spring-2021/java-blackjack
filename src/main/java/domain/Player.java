package domain;

import java.util.ArrayList;

public class Player {
    public static final int BLACKJACK_NUMBER = 21;

    private String name;
    private final Cards playerCards;
    private int cardsSum;
    private int bettingMoney;

    public Player(String name) {
        this.name = name;
        this.playerCards = new Cards(new ArrayList<>());
        this.bettingMoney = 0;
    }

    public Cards getPlayerCards() {
        return playerCards;
    }

    public String getName() {
        return name;
    }

    public int calculateCardsSum() {
        cardsSum = playerCards.getCardsSum();
        return cardsSum;
    }

    public int checkBlackJackFirst(int dealerCardSum) {
        if (dealerCardSum == BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            int dealerMoney = bettingMoney;
            bettingMoney = 0;
            return dealerMoney;
        }
        if (dealerCardSum != BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            bettingMoney += bettingMoney / 2;
            return -bettingMoney;
        }
        return 0;
    }

    public int calculateBettingMoney(int dealerCardSum) {
        cardsSum = playerCards.getCardsSum();
        if (dealerCardSum > BLACKJACK_NUMBER) {
            return -bettingMoney;
        }
        if (dealerCardSum == BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            int dealerMoney = bettingMoney;
            bettingMoney = 0;
            return dealerMoney;
        }
        if (cardsSum > BLACKJACK_NUMBER) {
            int dealerMoney = bettingMoney;
            bettingMoney = -bettingMoney;
            return dealerMoney;
        }
        if (cardsSum < dealerCardSum) {
            int dealerMoney = bettingMoney;
            bettingMoney = -bettingMoney;
            return dealerMoney;
        }
        return -bettingMoney;
    }

    public void addDealerProfit(int money) {
        bettingMoney += money;
    }

    public int getCardsSum() {
        return cardsSum;
    }

    public void setBettingMoney(int bettingMoney) {
        this.bettingMoney += bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
