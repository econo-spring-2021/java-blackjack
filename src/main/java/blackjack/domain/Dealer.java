package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public static final int DEALER_REVEAL_CARD_COUNT = 1;

    OwnedCards ownedCards;

    public Dealer() {
        this.ownedCards = new OwnedCards();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }

    public void addCard(Card card) {
        ownedCards.addCard(card);
    }

    public DealerCardDto getRevealCards() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.getCard(i));
        }

        return new DealerCardDto(revealCards);
    }

    public void getMoreCardIfPossible() {

    }

    public void getCardsSum() {

    }
}
