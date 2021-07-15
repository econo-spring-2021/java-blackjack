package blackjack.domain;

import java.util.List;

public class DealerCardDto {

    List<Card> ownedCards;

    public DealerCardDto(List<Card> ownedCards) {
        this.ownedCards = ownedCards;
    }

    public List<Card> getOwnedCards() {
        return this.ownedCards;
    }
}
