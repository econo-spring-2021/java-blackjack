package domain;

import java.util.ArrayList;

public class Player {
    private String name;
    private final ArrayList<String> cards;
    private String result;


    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
