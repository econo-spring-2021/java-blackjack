package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnedCards {
    List<Card> cards;

    public OwnedCards() {
        cards = new ArrayList<>();
    }

    public OwnedCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getCard(int index) { return cards.get(index); }
    public List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedCards that = (OwnedCards) o;
        return cards.equals(that.cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getScore() {
        int score = getCardsValueSum();

        score += getAddtitionAceScore(score);

        if (score > User.USER_LIMIT_CARD_VALUE) {
            return 0;
        } else {
            return score;
        }
    }

    public int getCardsValueSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }

        return sum;
    }

    public int getAddtitionAceScore(int currentScore) {
        int remainingValueUpToMax = User.USER_LIMIT_CARD_VALUE - currentScore;
        if (remainingValueUpToMax <= 0) {
            return 0;
        }

        int possibleCointToAdd = remainingValueUpToMax / 9;
        int additionAceCount = Math.min(possibleCointToAdd, getAceCount());
        int additionAceValue = additionAceCount * 9;

        return additionAceValue;
    }

    public int getAceCount() {
        int count = 0;
        for (Card card : cards) {
            count += card.returnOneIfAceElseReturnZero();
        }

        return count;
    }
}
