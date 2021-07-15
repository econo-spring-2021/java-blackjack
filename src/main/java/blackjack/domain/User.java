package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int USER_LIMIT_CARD_VALUE = 21;

    String name;
    List<Card> ownedCards = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getOwnedCards() {
        return this.ownedCards;
    }

    public void addCard(Card card) {
        ownedCards.add(card);
    }

    public boolean isPossibleToGetMoreCard() {
        int cardsValueSum = 0;
        for (Card card : ownedCards) {
            cardsValueSum += card.getValue();
        }

        return cardsValueSum <= 21;
    }
}
