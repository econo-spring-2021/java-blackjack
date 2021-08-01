package blackjack.domain;

public abstract class Player {

    String name;
    OwnedCards ownedCards;

    public Player() {
        this.ownedCards = new OwnedCards();
    }

    public String getName() {
        return name;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }

    public void addCard(Card card) {
        ownedCards.addCard(card);
    }

    abstract boolean isPossibleToGetMoreCard();
}
