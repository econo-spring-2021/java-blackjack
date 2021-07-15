package blackjack.domain;

import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_REVEAL_CARD_COUNT = 1;
    public static final int DEALER_ONEMORE_CARD_STANDARD_VALUE = 16;

    String name = DEALER_NAME;
    OwnedCards ownedCards;

    public Dealer() {
        this.ownedCards = new OwnedCards();
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }

    public PlayerInfoDto getDealerInfoDto() {
        return new PlayerInfoDto(name, ownedCards);
    }

    public PlayerInfoDto getDealerRevealInfoDto() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.getCard(i));
        }

        return new PlayerInfoDto(name, revealCards);
    }

    public void addCard(Card card) {
        ownedCards.addCard(card);
    }

    public void getOneMoreCardIfPossible(CardPack cardPack) {
        if (ownedCards.getCardsValueSum() <= DEALER_ONEMORE_CARD_STANDARD_VALUE) {
            OutputView.announcingDealerOneMoreCard(name);

            addCard(cardPack.getRandomCard());
        }
    }
}
