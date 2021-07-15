package domain;

import java.util.ArrayList;

public class Player {
    public static final String WIN = "승";
    public static final String LOSE = "패";
    public static final int BLACKJACK_NUMBER = 21;

    private String name;
    private final Cards cards;
    private String result;

    public Player(String name) {
        this.name = name;
        this.cards = new Cards(new ArrayList<>());
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setResult(int dealerSum) {
        int playerScore = BLACKJACK_NUMBER - cards.getCardsSum();
        if (playerScore >= 0 && playerScore <= dealerSum) {
            result = WIN;
            return;
        }
        result = LOSE;
    }

    public int getDealerWinCount() {
        if (result.equals(WIN)) {
            return 0;
        }
        return 1;
    }

    public String getResult() {
        return result;
    }
}
