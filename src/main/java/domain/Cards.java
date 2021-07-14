package domain;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    public static final int CARD_NUMBER_START = 2;
    public static final int CARD_NUMBER_END = 9;
    public static final int BLACKJACK_NUMBER = 21;
    public static final int CARD_A_OPTION_Point = 11;

    private final ArrayList<Card> cards;
    private int cardsSum;

    public Cards(ArrayList<Card> cards) {
        this.cards = cards;
        this.cardsSum = 0;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public static ArrayList<Card> setCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = CARD_NUMBER_START; i <= CARD_NUMBER_END; i++) {
            for (Card.Type type : Card.Type.values()) {
                cards.add(new Card(type, String.valueOf(i)));
            }
        }
        for (CardLetterNumber number : CardLetterNumber.values()) {
            for (Card.Type type : Card.Type.values()) {
                cards.add(new Card(type, String.valueOf(number)));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public static Card giveCard(ArrayList<Card> cards) {
        return cards.remove(0);
    }

    public int getCardsSum() {
        for (int i = 0; i < cards.size(); i++) {
            cardsSum += getCardPointNumber(cards.get(i).getNumber());
        }
        return cardsSum;
    }

    public int getCardPointNumber(String number) {
        if (number.equals(CardLetterNumber.J) || number.equals(CardLetterNumber.Q) || number.equals(CardLetterNumber.K)) {
            return CardLetterNumber.J.getNumber();
        }
        if (number.equals(CardLetterNumber.A)) {
            if (cardsSum + CARD_A_OPTION_Point <= BLACKJACK_NUMBER) {
                return CARD_A_OPTION_Point;
            }
            return CardLetterNumber.A.getNumber();
        }
        return Integer.parseInt(number);
    }

}
