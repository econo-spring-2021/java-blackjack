package domain;

import controller.Exception;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.ArrayList;

public class Players {
    private final ArrayList<Player> players;

    public static final String INVALID_ONE_CARD_ASK_ANSWER = "예는 y, 아니오는 n으로 입력해주세요.";
    public static final int DEALER_CARDS_MINIMUM_SUM = 16;
    public static final int BLACKJACK_NUMBER = 21;

    public Players(ArrayList<Player> players) {
        this.players = players;
    }

    public void giveTwoCards(Cards cards) {
        InputView.giveTwoCardsView(players);
        for (Player player : players) {
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
            player.getCards().getCards().add(cards.giveCard(cards.getCards()));
        }
        InputView.playersTwoCardsView(players);
    }

    public void askPlayersOneMoreCard(Cards cards) throws IOException {
        for (int i = 1; i < players.size(); i++) {
            String answer = askOnePlayerOneMoreCard(players.get(i));
            while (answer.equals("y")) {
                players.get(i).getCards().getCards().add(cards.giveCard(cards.getCards()));
                answer = askOnePlayerOneMoreCard(players.get(i));
            }
            OutputView.playerCardView(players.get(i));
        }
    }

    public String askOnePlayerOneMoreCard(Player player) {
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

    public void catchInvalidOneCardAskAnswer(String answer) throws Exception {
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new Exception(INVALID_ONE_CARD_ASK_ANSWER);
        }
    }

    public void getDealerOneMoreCard(Cards cards) {
        Player dealer = players.get(0);
        if (dealer.getCards().getCardsSum() <= DEALER_CARDS_MINIMUM_SUM) {
            dealer.getCards().getCards().add(cards.giveCard(cards.getCards()));
            InputView.dealerOneMoreCardView();
        }
    }

    public void showPlayersResults() {
        OutputView.playersResultView(players);
    }

    public void setWinOrLose() {
        int dealerScore = BLACKJACK_NUMBER - players.get(0).getCards().getCardsSum();
        int dealerWinCount = 0;
        for (int i = 1; i < players.size(); i++) {
            players.get(i).setResult(dealerScore);
            dealerWinCount += players.get(i).getDealerWinCount();
        }
        OutputView.winOrLoseResultView(players, dealerWinCount);
    }

    //Test용
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
