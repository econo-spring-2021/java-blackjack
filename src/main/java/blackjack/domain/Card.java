package blackjack.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && shape == card.shape && delimeter.equals(card.delimeter);
    }

    public int returnOneIfAceElseReturnZero() {
        if (value == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
