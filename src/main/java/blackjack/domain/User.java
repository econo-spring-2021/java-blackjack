package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

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

    public void getMoreCardTillUnableOrDeny(CardPack cardPack) {
        while (isPossibleToGetMoreCard() && askPlayerWillGetMoreCard()) {
            addCard(cardPack.getRandomCard());

            OutputView.printPlayersOwnedCards(name, ownedCards);
        }
    }

    public boolean askPlayerWillGetMoreCard() {
        OutputView.askGetMoreCard(name);
        String answer = InputView.getYesOrNo();
        if (answer.equals("y")){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPossibleToGetMoreCard() {
        int cardsValueSum = 0;
        for (Card card : ownedCards) {
            cardsValueSum += card.getValue();
        }

        return cardsValueSum <= USER_LIMIT_CARD_VALUE;
    }
}
