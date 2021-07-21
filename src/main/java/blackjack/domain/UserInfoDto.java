package blackjack.domain;

public class UserInfoDto extends PlayerInfoDto {

    boolean isDrawer = false;
    boolean isWinner = false;

    public UserInfoDto(String name, OwnedCards ownedCards) {
        super(name, ownedCards);
    }

    public boolean getIsDrawer() { return isDrawer; }

    public boolean getIsWinner() { return isWinner; }



    public void judgeResult(int opponentScore) {
        int score = ownedCards.getScore();
        if (opponentScore == score) {
            isDrawer = true;
        } else if (opponentScore < score) {
            isWinner = true;
        }
    }

    public int returnOneIfDrawerElseReturnZero() {
        if(isDrawer) {
            return 1;
        } else {
            return 0;
        }
    }

    public int returnOneIfWinnerElseReturnZero() {
        if(isWinner) {
            return 1;
        } else {
            return 0;
        }
    }
}
