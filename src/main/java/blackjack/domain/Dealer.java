package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    List<Card> ownedCards = new ArrayList<>();

    public List<Card> getOwnedCards() {
        return this.ownedCards;
    }

    public void addCard(Card card) {
        ownedCards.add(card);
    }

    public Card revealOneCard() {
        return ownedCards.get(0);
    }
}
