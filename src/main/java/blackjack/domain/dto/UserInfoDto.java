package blackjack.domain.dto;

import blackjack.domain.GameResult;
import blackjack.domain.OwnedCards;

public class UserInfoDto extends PlayerInfoDto {

    GameResult gameResult;

    public UserInfoDto(String name, OwnedCards ownedCards, GameResult gameResult, int income) {
        super(name, ownedCards, income);
        this.gameResult = gameResult;
    }

    public GameResult getGameResult() { return gameResult; }
}
