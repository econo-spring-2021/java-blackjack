package domain;

import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    public static final int CARD_NUMBER_START = 2;
    public static final int CARD_NUMBER_END = 9;

    private final ArrayList<Card> cards;

    public Cards(ArrayList<Card> cards) {
        this.cards = cards;
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

}
