package blackjack.domain.dto;

import blackjack.domain.GameResult;
import blackjack.domain.User;

public class UserInfoDto extends PlayerInfoDto {

    GameResult gameResult;

    public UserInfoDto(User user) {
        this.name = user.getName();
        this.ownedCards = user.getOwnedCards();
        this.income = user.getIncome();
        this.gameResult = user.getGameResult();
    }

    public GameResult getGameResult() { return gameResult; }
}
