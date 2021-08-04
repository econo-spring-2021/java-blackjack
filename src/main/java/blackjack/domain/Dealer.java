package blackjack.domain;

import blackjack.domain.dto.PlayerInfoDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_REVEAL_CARD_COUNT = 1;
    public static final int DEALER_MORE_CARD_COUNT = 1;
    public static final int DEALER_MORE_CARD_STANDARD_VALUE = 16;

    private int gotMoreCardCount = 0;

    public Dealer()
    {
        super();
        this.name = DEALER_NAME;
    }


    public PlayerInfoDto toDto() {
        return new PlayerInfoDto(name, ownedCards, income);
    }

    public PlayerInfoDto toRevealDto() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.getCard(i));
        }

        return new PlayerInfoDto(name, revealCards, income);
    }

    public void addCard(Card card) {
        gotMoreCardCount++;

        super.addCard(card);
    }

    public boolean isPossibleToGetMoreCard() {
        if (gotMoreCardCount < DEALER_MORE_CARD_COUNT &&
                ownedCards.getScore() <= DEALER_MORE_CARD_STANDARD_VALUE) {
            return true;
        }

        return false;
    }
}
