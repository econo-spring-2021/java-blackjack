package blackjack.domain;

public abstract class Player {
    public static final int Player_LIMIT_CARD_VALUE = 21;

    String name;
    OwnedCards ownedCards;
    boolean isBlackjack;
    boolean isBurst;
    int income;

    public Player() {
        this.ownedCards = new OwnedCards();
        this.income = 0;
    }

    public String getName() {
        return name;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }

    public boolean getIsBurst() { return isBurst; }

    public int getIncome() { return income; }

    public void addCard(Card card) {
        ownedCards.addCard(card);
    }

    abstract boolean isPossibleToGetMoreCard();

    public void judgeBlackjack() {
        isBlackjack = ownedCards.isBlackjack();
    }

    public void judgeBurst() {
        isBurst = ownedCards.isBurst();
    }

    public void earnBet(int bet) {
        income += bet;
    }

    public void loseBet(int bet) {
        income -= bet;
    }

    public void earnBlackjackBet(int bet) {
        income += Math.floor(bet * 1.5);
    }

    public void loseBlackjackBet(int bet) {
        income -= Math.floor(bet * 1.5);
    }
}
