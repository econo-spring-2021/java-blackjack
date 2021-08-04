package blackjack.domain.dto;

import blackjack.domain.GameResult;
import blackjack.domain.OwnedCards;

public class UserInfoDto extends PlayerInfoDto {

    GameResult gameResult;

    public UserInfoDto(String name, OwnedCards ownedCards, GameResult gameResult) {
        super(name, ownedCards);
        this.gameResult = gameResult;
    }

    public GameResult getGameResult() { return gameResult; }
}
