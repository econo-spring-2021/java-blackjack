package blackjack.domain;

import blackjack.domain.dto.UserInfoDto;


public class User extends Player {

    int bet;
    GameResult gameResult;

    public User(String name) {
        super();
        this.name = name;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= Player_LIMIT_CARD_VALUE;
    }

    public void judgeResult(boolean dealerBlackjack, boolean dealerBurst, int dealerScore) {

        if (dealerBlackjack) {
            gameResult = getGameResultOnDealerBlackjack();
            return;
        }

        if (dealerBurst) {
            gameResult = getGameResultOnDealerBurst();
            return;
        }

        if (isBurst) {
            gameResult = GameResult.LOSE;
            return;
        }

        gameResult = getGameResultByScore(ownedCards.getScore(), dealerScore);
    }

    private GameResult getGameResultOnDealerBlackjack() {
        if (isBlackjack) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }

    private GameResult getGameResultOnDealerBurst() {
        return GameResult.WIN;
    }

    private GameResult getGameResultByScore(int userScore, int dealerScore) {
        if (dealerScore == userScore) {
            return GameResult.DRAW;
        }

        if (dealerScore < userScore) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }
}
