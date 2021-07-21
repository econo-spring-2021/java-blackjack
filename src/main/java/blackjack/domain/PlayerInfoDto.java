package blackjack.domain;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Player player = (Player) o;

        if (!name.equals(player.name)) {
            return false;
        }

        List<Card> thisOwnedCards = ownedCards.getCards();
        List<Card> thatOwnedCards = player.getOwnedCards().getCards();
        for (int i = 0; i < thisOwnedCards.size(); i++) {
            if (!thisOwnedCards.get(i).equals(thatOwnedCards.get(i))) {
                return false;
            }
        }

        return true;
    }
}
