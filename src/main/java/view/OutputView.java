package view;

import domain.Player;
import domain.Players;

public class OutputView {
    public static final String CARD = "카드: ";
    public static final String RESULT = " - 결과: ";
    public static final String WIN_OR_LOSE_RESULT_VIEW = "## 최종 승패";
    public static final String COLON = ": ";
    public static final String WIN = "승 ";
    public static final String LOSE = "패";
    public static final String COMMA = ", ";

    public static void playerCardView(Player player) {
        StringBuilder view = new StringBuilder();
        view.append(player.getName()).append(CARD);
        for (int i = 0; i < player.getCards().getCards().size(); i++) {
            view.append(player.getCards().getCards().get(i).getCardName());
            if (i != player.getCards().getCards().size() - 1) {
                view.append(", ");
            }
        }
        System.out.println(view.toString());
    }

    public static void playersResultView(Players players) {
        for (int i = 0; i < players.getPlayers().size(); i++) {
            onePlayerResultView(players.getPlayers().get(i));
        }
        System.out.println();
    }

    public static void onePlayerResultView(Player player) {
        StringBuilder view = new StringBuilder();
        view.append(player.getName()).append(CARD);
        for (int i = 0; i < player.getCards().getCards().size(); i++) {
            view.append(player.getCards().getCards().get(i).getCardName());
            if (i != player.getCards().getCards().size() - 1) {
                view.append(COMMA);
            }
        }
        view.append(RESULT).append(player.getCards().getCardsSum());
        System.out.println(view.toString());
    }

    public static void winOrLoseResultView(Players players, int dealerWinCount) {
        System.out.println(WIN_OR_LOSE_RESULT_VIEW);
        StringBuilder sb = new StringBuilder();
        sb.append(players.getPlayers().get(0).getName()).append(COLON).append(dealerWinCount).append(WIN).append(players.getPlayers().size() - 1 - dealerWinCount).append(LOSE).append("\n");
        for (int i = 1; i < players.getPlayers().size(); i++) {
            sb.append(players.getPlayers().get(i).getName()).append(COLON).append(players.getPlayers().get(i).getResult()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
