package domain;

import java.util.ArrayList;

public class Player {
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

    public int setResult(int dealerSum, int dealerWinCount) {
        int playerSum = cards.getCardsSum();
        if (playerSum >= 0 && playerSum < dealerSum) {
            result = "승";
            return 0;
        }
        result = "패";
        return 1;
    }

    public String getResult() {
        return result;
    }
}
