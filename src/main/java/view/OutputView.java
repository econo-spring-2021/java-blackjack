package view;

import domain.Player;

import java.util.ArrayList;

public class OutputView {
    public static final String CARD = "카드: ";
    public static final String RESULT = " - 결과: ";
    public static final String FINAL_PROFIT_RESULT_VIEW = "## 최종 수익";
    public static final String COLON = ": ";
    public static final String WIN = "승 ";
    public static final String LOSE = "패";
    public static final String COMMA = ", ";

    public static void playerCardView(Player player) {
        StringBuilder view = new StringBuilder();
        view.append(player.getName()).append(CARD);
        for (int i = 0; i < player.getPlayerCards().getCardsList().size(); i++) {
            view.append(player.getPlayerCards().getCardsList().get(i).getCardName());
            view = addComma(i, player, view);
        }
        System.out.println(view.toString());
    }

    public static StringBuilder addComma(int i, Player player, StringBuilder view) {
        if (i != player.getPlayerCards().getCardsList().size() - 1) {
            view.append(COMMA);
        }
        return view;
    }

    public static void playersResultView(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            onePlayerResultView(players.get(i));
        }
        System.out.println();
    }

    public static void onePlayerResultView(Player player) {
        StringBuilder view = new StringBuilder();
        view.append(player.getName()).append(CARD);
        for (int i = 0; i < player.getPlayerCards().getCardsList().size(); i++) {
            view.append(player.getPlayerCards().getCardsList().get(i).getCardName());
            view = addComma(i, player, view);
        }
        view.append(RESULT).append(player.getPlayerCards().getCardsSum());
        System.out.println(view.toString());
    }

    public static void finalProfitView(ArrayList<Player> players) {
        System.out.println(FINAL_PROFIT_RESULT_VIEW);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            sb.append(players.get(i).getName()).append(COLON).append(players.get(i).getBettingMoney()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
