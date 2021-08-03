package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Game {
    public static final String INVALID_INPUT_NAME_EXCEPTION = "쉼표를 기준으로 사람의 이름을 입력하세요.";

    public static ArrayList<Player> setPlayerName(String inputName) throws IOException {
        ArrayList<Player> players = new ArrayList<>();
        try {
            catchInvalidInputNameException(inputName);
            players.add(new Player("딜러"));
            StringTokenizer st = new StringTokenizer(inputName, ",");
            while (st.hasMoreTokens()) {
                players.add(new Player(st.nextToken()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setPlayerName(InputView.playerNameView());
        }
        return players;
    }

    public static void catchInvalidInputNameException(String inputName) throws Exception {
        for (int i = 0; i < inputName.length(); i++) {
            char c = inputName.charAt(i);
            if (c == ',') {
                continue;
            }
            if (c < 'A' || c > 'z') {
                throw new Exception(INVALID_INPUT_NAME_EXCEPTION);
            }
            if (c > 'Z' && c < 'a') {
                throw new Exception(INVALID_INPUT_NAME_EXCEPTION);
            }
        }
    }
}
