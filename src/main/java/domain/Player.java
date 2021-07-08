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
}
