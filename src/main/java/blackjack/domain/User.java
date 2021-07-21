package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;


public class User extends Player{
    public static final int USER_LIMIT_CARD_VALUE = 21;

    public User(String name) {
        super();
        this.name = name;
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= USER_LIMIT_CARD_VALUE;
    }
}
