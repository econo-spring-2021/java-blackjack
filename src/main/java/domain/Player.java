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

    public void checkBlackJackFirst(int dealerCardSum) {
        if (dealerCardSum == BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            bettingMoney = 0;
            return;
        }
        if (dealerCardSum != BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            bettingMoney += bettingMoney / 2;
            return;
        }
    }

    public void calculateBettingMoney(int dealerCardSum) {
        cardsSum = playerCards.getCardsSum();
        if (dealerCardSum > BLACKJACK_NUMBER) {
            return;
        }
        if (dealerCardSum == BLACKJACK_NUMBER && cardsSum == BLACKJACK_NUMBER) {
            bettingMoney = 0;
            return;
        }
        if (cardsSum > BLACKJACK_NUMBER) {
            bettingMoney = -bettingMoney;
            return;
        }
        if (cardsSum < dealerCardSum) {
            bettingMoney = -bettingMoney;
            return;
        }
    }

    public int getCardsSum() {
        return cardsSum;
    }

    public void setBettingMoney(int bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
