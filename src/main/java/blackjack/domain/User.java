package blackjack.domain;

import blackjack.domain.dto.UserInfoDto;


public class User extends Player{

    int bet;
    boolean isDrawer;
    boolean isWinner;

    public User(String name) {
        super();
        this.name = name;
    }

    public UserInfoDto toDto() {
        return new UserInfoDto(name, ownedCards, isDrawer, isWinner);
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= Player_LIMIT_CARD_VALUE;
    }

    public void judgeResult(int opponentScore) {
        int score = ownedCards.getScore();
        if (opponentScore == score) {
            isDrawer = true;
        } else if (opponentScore < score) {
            isWinner = true;
        }
    }
}
