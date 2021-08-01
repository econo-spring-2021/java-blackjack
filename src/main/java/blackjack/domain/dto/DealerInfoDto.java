package blackjack.domain.dto;

import blackjack.domain.Card;
import blackjack.domain.OwnedCards;

import java.util.List;

public class DealerInfoDto extends PlayerInfoDto {

    int winCount;
    int drawCount;
    int loseCount;

    public DealerInfoDto(String name, OwnedCards ownedCards, int winCount, int drawCount, int loseCount) {
        super(name, ownedCards);
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = loseCount;
    }

    public DealerInfoDto(String name, List<Card> cards) {
        super(name, cards);
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }
}
