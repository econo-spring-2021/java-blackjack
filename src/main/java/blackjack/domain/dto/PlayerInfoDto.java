package blackjack.domain.dto;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.OwnedCards;
import blackjack.domain.Player;

import java.util.List;

public class PlayerInfoDto {

    String name;
    OwnedCards ownedCards;
    int income;

    public PlayerInfoDto() {
    }

    public PlayerInfoDto(Dealer dealer) {
        this.name = dealer.getName();
        this.ownedCards = dealer.getOwnedCards();
        this.income = dealer.getIncome();
    }

    public PlayerInfoDto(String name, List<Card> cards, int income) {
        this.name = name;
        this.ownedCards = new OwnedCards(cards);
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public OwnedCards getOwnedCards() {
        return ownedCards;
    }

    public int getIncome() {
        return income;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Player player = (Player) o;

        if (!name.equals(player.getName())) {
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
