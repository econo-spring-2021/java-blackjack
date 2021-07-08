package view;


import domain.Player;
import domain.Players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    public static final String PLAYER_NAME_VIEW = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    public static final String GIVE_TWO_CARD_VIEW = "에게 각각 2장의 카드를 나누었습니다.";
    public static final String DEALER = "딜러";

    public static String playerNameView() throws IOException {
        System.out.println(PLAYER_NAME_VIEW);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void giveTwoCardsView(Players players) {
        String view = DEALER;
        view += "와 ";
        for (int i = 1; i < players.getPlayers().size(); i++) {
            view += players.getPlayers().get(i).getName();
            if (i != players.getPlayers().size() - 1) {
                view += ", ";
            }
        }
        view += GIVE_TWO_CARD_VIEW;
        System.out.println(view);
    }

    public static void playersTwoCardsView(Players players) {
        System.out.println(DEALER + ": " + players.getPlayers().get(0).getCards().getCards().get(0).getCardName());
        for (int i = 1; i < players.getPlayers().size(); i++) {
            Player player = players.getPlayers().get(i);
            StringBuilder view = new StringBuilder();
            view.append(player.getName() + "카드: ").append(player.getCards().getCards().get(0).getCardName()).append(", ").append(player.getCards().getCards().get(1).getCardName());
            System.out.println(view.toString());
        }
    }
}
