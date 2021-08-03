package view;


import domain.Player;
import domain.Players;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InputView {
    public static final String PLAYER_NAME_VIEW = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    public static final String GIVE_TWO_CARD_VIEW = "에게 각각 2장의 카드를 나누었습니다.";
    public static final String DEALER = "딜러";
    public static final String ONE_MORE_CARD_VIEW = "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)";
    public static final String CARD = "카드: ";
    public static final String DEALER_ONE_MORE_CARD_VIEW = "딜러는 16이하라 한 장의 카드를 더 받았습니다.";
    public static final String WITH = "와 ";
    public static final String COMMA = ", ";

    public static String playerNameView() throws IOException {
        System.out.println(PLAYER_NAME_VIEW);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void giveTwoCardsView(ArrayList<Player> players) {
        String view = DEALER;
        view += WITH;
        for (int i = 1; i < players.size(); i++) {
            view += players.get(i).getName();
            if (i != players.size() - 1) {
                view += COMMA;
            }
        }
        view += GIVE_TWO_CARD_VIEW;
        view += "\n";
        System.out.println(view);
    }

    public static void playersTwoCardsView(ArrayList<Player> players) {
        System.out.println(DEALER + ": " + players.get(0).getCards().getCards().get(0).getCardName());
        for (int i = 1; i < players.size(); i++) {
            Player player = players.get(i);
            StringBuilder view = new StringBuilder();
            view.append(player.getName() + CARD).append(player.getCards().getCards().get(0).getCardName()).append(COMMA).append(player.getCards().getCards().get(1).getCardName());
            System.out.println(view.toString());
        }
        System.out.println();
    }

    public static String oneMoreCardView(Player player) throws IOException {
        System.out.println(player.getName() + ONE_MORE_CARD_VIEW);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ask = br.readLine();
        return ask;
    }

    public static void dealerOneMoreCardView() {
        System.out.println(DEALER_ONE_MORE_CARD_VIEW);
        System.out.println();
    }
}
