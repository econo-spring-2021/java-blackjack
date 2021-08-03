package blackjack.domain.dto;

import blackjack.domain.OwnedCards;

public class UserInfoDto extends PlayerInfoDto {

    boolean isDrawer;
    boolean isWinner;

    public UserInfoDto(String name, OwnedCards ownedCards, boolean isDrawer, boolean isWinner) {
        super(name, ownedCards);
        this.isDrawer = isDrawer;
        this.isWinner = isWinner;
    }

    public boolean getIsDrawer() { return isDrawer; }

    public boolean getIsWinner() { return isWinner; }
}
