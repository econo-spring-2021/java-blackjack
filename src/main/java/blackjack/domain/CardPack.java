package blackjack.domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPack {
    public static final int CARD_SHAPE_COUNT = 4;
    public static final int CARD_VALUE_COUNT = 13;

    List<Card> cards = new ArrayList<>();
    int selectedIndex = -1;

    private static CardPack instance = new CardPack();

    private CardPack() {
        initCardPack();
        shuffleCardPack();
    }

    private void initCardPack() {
        for (int i = 0; i < CARD_SHAPE_COUNT; i++) {
            for (int j = 0; j < CARD_VALUE_COUNT; j++) {
                cards.add(new Card(CardShape.values()[i], CardGrade.values()[j]));
            }
        }
    }

    private void shuffleCardPack() {
            Collections.shuffle(cards);
    }

    public static CardPack getInstance() {
        return instance;
    }

    public static void resetCardPack() {
        instance = new CardPack();
    }

    public Card getRandomCard() {
        selectedIndex++;
        return cards.get(selectedIndex);
    }
}
