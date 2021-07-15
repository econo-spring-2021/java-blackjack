package blackjack.domain;

public class Card {
    CardShape shape;
    String delimeter;
    int value;

    public Card(CardShape shape, String delimeter, int value) {
        this.shape = shape;
        this.delimeter = delimeter;
        this.value = value;
    }

    public CardShape getShape() { return shape; }

    public String getDelimeter() { return delimeter; }

    public int getValue() { return value; }
}
