package blackjack.domain;

import java.util.List;

public class UserInfoDto {

    String name;
    List<Card> ownedCards;

    public UserInfoDto(String name, List<Card> ownedCards) {
        this.name = name;
        this.ownedCards = ownedCards;
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getOwnedCards() {
        return this.ownedCards;
    }
}
