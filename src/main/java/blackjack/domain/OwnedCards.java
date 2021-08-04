package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class OwnedCards {
    List<Card> cards;

    public OwnedCards() {
        cards = new ArrayList<>();
    }

    public OwnedCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

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

    public boolean isBlackjack() {
        return getScore() == Player.Player_LIMIT_CARD_VALUE;
    }

    public boolean isBurst() {
        return getScore() > Player.Player_LIMIT_CARD_VALUE;
    }

    public int getScore() {
        int score = getCardsValueSum();
        score += getAdditionalAceScore(score);

        return score;
    }

    public int getCardsValueSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getGrade();
        }

        return sum;
    }

    public int getAdditionalAceScore(int currentScore) {
        int remainingValueUpToMax = Player.Player_LIMIT_CARD_VALUE - currentScore;
        if (remainingValueUpToMax <= 0) {
            return 0;
        }

        int additionalAceGrade = CardGrade.ACE_MAX_GRADE - CardGrade.ACE_MIN_GRADE;
        int possiblePointToAdd = remainingValueUpToMax / additionalAceGrade;
        int additionalAceCount = Math.min(possiblePointToAdd, getAceCount());

        return additionalAceCount * additionalAceGrade;
    }

    public int getAceCount() {
        int count = 0;
        for (Card card : cards) {
            if (card.isAce())
                count++;
        }

        return count;
    }
}
