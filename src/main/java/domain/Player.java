package domain;

import java.util.ArrayList;

public class Player {
    public static final String WIN = "승";
    public static final String LOSE = "패";
    public static final int BLACKJACK_NUMBER = 21;

    private String name;
    private final Cards playerCards;
    private String result;

    public Player(String name) {
        this.name = name;
        this.playerCards = new Cards(new ArrayList<>());
    }

    public Cards getPlayerCards() {
        return playerCards;
    }

    public String getName() {
        return name;
    }

    public void setResult(int dealerSum) {
        int playerScore = BLACKJACK_NUMBER - playerCards.getCardsSum();
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
