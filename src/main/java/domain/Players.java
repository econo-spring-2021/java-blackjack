package domain;

import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;

    public Players(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
