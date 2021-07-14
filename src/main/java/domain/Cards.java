package domain;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    public static final int CARD_NUMBER_START = 2;
    public static final int CARD_NUMBER_END = 9;
    public static final int BLACKJACK_NUMBER = 21;
    public static final int CARD_A_POINT_ELEVEN = 11;
    public static final int CARD_A_POINT_ONE = 1;
    public static final String K = "K";
    public static final String Q = "Q";
    public static final String J = "J";
    public static final String A = "A";
    public static final int KQJ_POINT_TEN = 10;


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

        for (Card.Type type : Card.Type.values()) {
            cards.add(new Card(type, K));
            cards.add(new Card(type, Q));
            cards.add(new Card(type, J));
            cards.add(new Card(type, A));
        }

        Collections.shuffle(cards);
        return cards;
    }

    public static Card giveCard(ArrayList<Card> cards) {
        return cards.remove(0);
    }

    public int getCardsSum() {
        cardsSum = 0;
        for (int i = 0; i < cards.size(); i++) {
            cardsSum += getCardPointNumber(cards.get(i).getNumber());
        }
        return cardsSum;
    }

    public int getCardPointNumber(String number) {
        if (number.equals(J) || number.equals(Q) || number.equals(K)) {
            return KQJ_POINT_TEN;
        }
        if (number.equals(A)) {
            if (cardsSum + CARD_A_POINT_ELEVEN <= BLACKJACK_NUMBER) {
                return CARD_A_POINT_ELEVEN;
            }
            return CARD_A_POINT_ONE;
        }
        return Integer.parseInt(number);
    }

}
