package blackjack.domain;

public class Card {
    CardShape shape;
    int value;

    public Card(CardShape shape, int value) {
        this.shape = shape;
        this.value = value;
    }

    public CardShape getShape() { return shape; }

    public int getValue() { return value; }
}
