package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Game {
    public static final String INVALID_INPUT_NAME_EXCEPTION = "쉼표를 기준으로 사람의 이름을 입력하세요.";
    public static final String INVALID_ONE_CARD_ASK_ANSWER = "예는 y, 아니오는 n으로 입력해주세요.";
    public static final int DEALER_CARDS_MINIMUM_SUM = 16;

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

    public static void giveTwoCards(Players players, Cards cards) {
        InputView.giveTwoCardsView(players);
        for (Player player : players.getPlayers()) {
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
        }
        InputView.playersTwoCardsView(players);
    }

    public static void askPlayersOneMoreCard(Players players, Cards cards) throws IOException {
        for (int i = 1; i < players.getPlayers().size(); i++) {
            String answer = askOnePlayerOneMoreCard(players.getPlayers().get(i));
            while (answer.equals("y")) {
                players.getPlayers().get(i).getCards().getCards().add(cards.giveCard(cards.getCards()));
                answer = askOnePlayerOneMoreCard(players.getPlayers().get(i));
            }
            OutputView.playerCardView(players.getPlayers().get(i));
        }
    }

    public static String askOnePlayerOneMoreCard(Player player) {
        String answer = "";
        try {
            answer = InputView.oneMoreCardView(player);
            catchInvalidOneCardAskAnswer(answer);
        } catch (Exception | IOException e) {
            System.out.println(e.getMessage());
            askOnePlayerOneMoreCard(player);
        }
        return answer;
    }

    public static void catchInvalidOneCardAskAnswer(String answer) throws Exception {
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new Exception(INVALID_ONE_CARD_ASK_ANSWER);
        }
    }

    public static void getDealerOneMoreCard(Players players, Cards cards) {
        Player dealer = players.getPlayers().get(0);
        if (dealer.getCards().getCardsSum() <= DEALER_CARDS_MINIMUM_SUM) {
            dealer.getCards().getCards().add(cards.giveCard(cards.getCards()));
            InputView.dealerOneMoreCardView();
        }
    }

}
