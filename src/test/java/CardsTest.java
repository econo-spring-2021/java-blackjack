import domain.Card;
import domain.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CardsTest {

    @DisplayName("setCards 테스트")
    @Test
    public void setCardsTest() {
        Cards cards = new Cards(Cards.setCards());
        assertThat(cards.getCardsList().get(0).getCardName()).isEqualTo("2클로버");
    }

    @DisplayName("addCard 테스트")
    @Test
    public void addCardTest() {
        ArrayList<Card> randomCards = new ArrayList<>();
        randomCards.add(new Card(Card.Type.클로버, "3"));
        randomCards.add(new Card(Card.Type.하트, "5"));
        Card card = randomCards.get(0);

        ArrayList<Card> cardsSet = new ArrayList<>();
        cardsSet.add(new Card(Card.Type.하트, "2"));
        Cards cards = new Cards(cardsSet);
        cards.addCard(randomCards);
        assertThat(cards.getCardsList().get(1)).isEqualTo(card);
    }

    @DisplayName("getCardsSum 테스트")
    @Test
    public void getCardsSumTest() {
        ArrayList<Card> cardsSet = new ArrayList<>();
        cardsSet.add(new Card(Card.Type.클로버, "2"));
        cardsSet.add(new Card(Card.Type.클로버, "3"));
        cardsSet.add(new Card(Card.Type.클로버, "4"));

        Cards cards = new Cards(cardsSet);
        assertThat(cards.getCardsSum()).isEqualTo(9);
    }

    @DisplayName("getCardPointNumber 테스트")
    @Test
    public void getCardPointNumberTest() {
        ArrayList<Card> cardsSet = new ArrayList<>();
        cardsSet.add(new Card(Card.Type.클로버, "2"));
        cardsSet.add(new Card(Card.Type.클로버, "J"));
        cardsSet.add(new Card(Card.Type.클로버, "A"));

        Cards cards = new Cards(cardsSet);
        assertThat(cards.getCardPointNumber(cards.getCardsList().get(0).getNumber())).isEqualTo(2);
        assertThat(cards.getCardPointNumber(cards.getCardsList().get(1).getNumber())).isEqualTo(10);
        assertThat(cards.getCardPointNumber(cards.getCardsList().get(2).getNumber())).isEqualTo(11);
    }
}
