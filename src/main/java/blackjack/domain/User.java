package blackjack.domain;

import blackjack.domain.dto.UserInfoDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;


public class User extends Player{
    public static final int USER_LIMIT_CARD_VALUE = 21;

    boolean isDrawer = false;
    boolean isWinner = false;

    public User(String name) {
        super();
        this.name = name;
    }

    public UserInfoDto toDto() {
        return new UserInfoDto(name, ownedCards, isDrawer, isWinner);
    }

    public boolean isPossibleToGetMoreCard() {
        return ownedCards.getCardsValueSum() <= USER_LIMIT_CARD_VALUE;
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
