package blackjack.domain;

import java.util.List;

public class DealerCardDto {

    OwnedCards ownedCards;

    public DealerCardDto(List<Card> ownedCards) {
        ownedCards = ownedCards;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }
}
