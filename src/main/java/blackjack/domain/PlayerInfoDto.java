package blackjack.domain;

import java.util.List;

public class PlayerInfoDto {

    String name;
    OwnedCards ownedCards;

    public PlayerInfoDto() {
    }

    public PlayerInfoDto(String name, OwnedCards ownedCards) {
        this.name = name;
        this.ownedCards = ownedCards;
    }

    public PlayerInfoDto(String name, List<Card> cards) {
        this.name = name;
        this.ownedCards = new OwnedCards(cards);
    }

    public String getName() {
        return name;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }
}
