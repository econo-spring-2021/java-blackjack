package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public static final int DEALER_REVEAL_CARD_COUNT = 1;

    List<Card> ownedCards = new ArrayList<>();

    public List<Card> getOwnedCards() {
        return this.ownedCards;
    }

    public void addCard(Card card) {
        ownedCards.add(card);
    }

    public List<Card> getRevealCards() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.get(0));
        }

        return revealCards;
    }
}
