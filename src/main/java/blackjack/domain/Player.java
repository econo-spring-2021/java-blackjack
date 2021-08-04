package blackjack.domain;

public abstract class Player {
    public static final int Player_LIMIT_CARD_VALUE = 21;

    String name;
    OwnedCards ownedCards;
    boolean isBlackjack;
    boolean isBurst;
    int money;

    public Player() {
        this.ownedCards = new OwnedCards();
    }

    public String getName() {
        return name;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }

    public boolean getIsBlackjack() {
        return isBlackjack;
    }

    public boolean getIsBurst() { return isBurst; }

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

}
