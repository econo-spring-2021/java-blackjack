package view;

import domain.Player;
import domain.Players;

public class OutputView {
    public static final String CARD = "카드: ";
    public static final String RESULT = " - 결과: ";

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

    public static void onePlayerResultView(Player player){
        StringBuilder view = new StringBuilder();
        view.append(player.getName()).append(CARD);
        for (int i = 0; i < player.getCards().getCards().size(); i++) {
            view.append(player.getCards().getCards().get(i).getCardName());
            if (i != player.getCards().getCards().size() - 1) {
                view.append(", ");
            }
        }
        view.append(RESULT).append(player.getCards().getCardsSum());
        System.out.println(view.toString());
    }

    public static void winOrLoseResultView(Players players){

    }
}
