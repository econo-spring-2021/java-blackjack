package blackjack.domain;

import blackjack.domain.dto.UserInfoDto;


public class User extends Player{

    int bet;
    GameResult gameResult;

    public User(String name) {
        super();
        this.name = name;
    }

    public UserInfoDto toDto() {
        return new UserInfoDto(name, ownedCards, gameResult);
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= Player_LIMIT_CARD_VALUE;
    }

    public void judgeResult(boolean dealerBlackjack, boolean dealerBurst, int dealerScore) {

        if (dealerBlackjack) {
            judgeResultOnDealerBlackjack();
        } else if (dealerBurst) {
            judgeResultOnDealerBurst();
        } else {
            judgeResultByScore(ownedCards.getScore(), dealerScore);
        }
    }

    private void judgeResultOnDealerBlackjack() {
        if (super.isBlackjack) {
            gameResult = GameResult.DRAW;
            return;
        }

        gameResult = GameResult.LOSE;
    }

    private void judgeResultOnDealerBurst() {
        if (super.isBurst) {
            gameResult = GameResult.DRAW;
            return;
        }

        gameResult = GameResult.WIN;
    }

    private void judgeResultByScore(int userScore, int dealerScore) {
        if (dealerScore == userScore) {
            gameResult = GameResult.DRAW;
        } else if (dealerScore < userScore) {
            gameResult = GameResult.WIN;
        } else {
            gameResult = GameResult.LOSE;
        }
    }
}
