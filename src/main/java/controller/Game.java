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
    public static final String INVALID_BETTING_MONEY_INPUT = "베팅 금액을 숫자로 입력해주세요.";

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

    public static void setPlayersBettingMoney(Players players) throws IOException {
        for (int i = 1; i < players.getPlayersSize(); i++) {
            setOnePlayerBettingMoney(players, i);
        }
    }

    public static void setOnePlayerBettingMoney(Players players, int index) throws IOException {
        String inputBettingMoney = InputView.inputBettingMoneyView(players.getPlayerName(index));
        try {
            catchInvalidBettingMoneyInputException(inputBettingMoney);
            int bettingMoney = Integer.parseInt(inputBettingMoney);
            players.setPlayerBettingMoney(index, bettingMoney);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            setOnePlayerBettingMoney(players, index);
        }
    }

    public static void catchInvalidBettingMoneyInputException(String inputBettingMoney) throws Exception {
        for (int i = 0; i < inputBettingMoney.length(); i++) {
            char c = inputBettingMoney.charAt(i);
            if (c < '0' || c > '9') {
                throw new Exception(INVALID_BETTING_MONEY_INPUT);
            }
        }
    }

    public static void giveTwoCards(Cards cards, Players players) {
        InputView.giveTwoCardsView(players.getPlayers());
        players.getTwoCards(cards);
        InputView.playersTwoCardsView(players.getPlayers());
    }

    public static void askPlayersOneMoreCard(Cards cards, Players players) throws IOException {
        for (int i = 1; i < players.getPlayersSize(); i++) {
            String answer = getOnePlayerOneMoreCardWants(players.getPlayers().get(i));
            while ("y".equals(answer)) {
                players.addPlayerOneMoreCard(cards, i);
                answer = getOnePlayerOneMoreCardWants(players.getPlayers().get(i));
            }
            OutputView.playerCardView(players.getPlayers().get(i));
        }
    }

    public static String getOnePlayerOneMoreCardWants(Player player) {
        String answer = "";
        try {
            answer = InputView.oneMoreCardView(player);
            catchInvalidOneCardAskAnswer(answer);
        } catch (Exception | IOException e) {
            System.out.println(e.getMessage());
            getOnePlayerOneMoreCardWants(player);
        }
        return answer;
    }

    public static void catchInvalidOneCardAskAnswer(String answer) throws Exception {
        if (!"y".equals(answer) && !"n".equals(answer)) {
            throw new Exception(INVALID_ONE_CARD_ASK_ANSWER);
        }
    }

    public static void getDealerOneMoreCard(Cards cards, Players players) {
        boolean isDealerOneMoreCardNeeded = players.checkDealerOneMoreCardNeeded(cards);
        if (isDealerOneMoreCardNeeded) {
            InputView.dealerOneMoreCardView();
        }
    }

    public static void showPlayersResults(Players players) {
        OutputView.playersResultView(players.getPlayers());
    }

    public static void setWinOrLose(Players players) {
        int dealerWinCount = players.getDealerWinCount();
        OutputView.winOrLoseResultView(players.getPlayers(), dealerWinCount);
    }
}
