package controller;

import domain.Player;
import view.InputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Game {

    public static ArrayList<Player> setPlayerName(String inputName) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        try {
            catchInvalidInputNameException(inputName);
            StringTokenizer st = new StringTokenizer(inputName, ",");

            while (st.hasMoreTokens()) {
                players.add(new Player(st.nextToken()));
            }
        } catch (InvalidInputNameException e) {
            System.out.println(e.getMessage());
            setPlayerName(InputView.playerNameView());
        }
        return players;
    }

    public static void catchInvalidInputNameException(String inputName) throws InvalidInputNameException {
        for (int i = 0; i < inputName.length(); i++) {
            char c = inputName.charAt(i);
            if (c == ',') {
                continue;
            }
            if (c < 'A' || c > 'z') {
                throw new InvalidInputNameException();
            }
            if (c > 'Z' && c < 'a') {
                throw new InvalidInputNameException();
            }
        }
    }
}
