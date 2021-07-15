package blackjack.domain;

import java.util.List;

public class DealerInfoDto extends PlayerInfoDto {

    int winCount = 0;
    int loseCount = 0;
    int drawCount = 0;

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public DealerInfoDto(String name, OwnedCards ownedCards) {
        super(name, ownedCards);
    }

    public DealerInfoDto(String name, List<Card> cards) {
        super(name, cards);
    }

    public void setGameResult(int winCount, int loseCount, int drawCount) {
        this.winCount = winCount;
        this.loseCount = loseCount;
        this.drawCount = drawCount;
    }
}
