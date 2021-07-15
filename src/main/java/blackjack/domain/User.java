package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final int USER_LIMIT_CARD_VALUE = 21;

    String name;
    OwnedCards ownedCards;

    public User(String name) {
        this.name = name;
        this.ownedCards = new OwnedCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }

    public void addCard(Card card) {
        ownedCards.addCard(card);
    }

    public void getMoreCardTillUnableOrDeny(CardPack cardPack) {
        while (isPossibleToGetMoreCard() && askPlayerWillGetMoreCard()) {
            addCard(cardPack.getRandomCard());

            OutputView.printPlayersOwnedCards(name, getOwnedCards());
        }
    }

    public boolean askPlayerWillGetMoreCard() {
        OutputView.askGetMoreCard(name);
        String answer = InputView.getYesOrNo();
        if (answer.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= USER_LIMIT_CARD_VALUE;
    }
}
