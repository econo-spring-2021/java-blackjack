package blackjack.domain;

import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_REVEAL_CARD_COUNT = 1;
    public static final int DEALER_ONEMORE_CARD_STANDARD_VALUE = 16;

    public Dealer()
    {
        super();
        this.name = DEALER_NAME;
    }


    public DealerInfoDto getDealerInfoDto() {
        return new DealerInfoDto(name, ownedCards);
    }

    public DealerInfoDto getDealerRevealInfoDto() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.getCard(i));
        }

        return new DealerInfoDto(name, revealCards);
    }

    public boolean isPossibleToGetMoreCard() {
        if (ownedCards.getCardsValueSum() <= DEALER_ONEMORE_CARD_STANDARD_VALUE) {
            return true;
        }

        return false;
    }

//    public void getOneMoreCardIfPossible(CardPack cardPack) {
//        if (ownedCards.getCardsValueSum() <= DEALER_ONEMORE_CARD_STANDARD_VALUE) {
//            OutputView.announcingDealerOneMoreCard(name);
//
//            addCard(cardPack.getRandomCard());
//        }
//    }
}
