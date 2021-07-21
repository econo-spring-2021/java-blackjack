package blackjack.domain;

public class Card {
    CardShape shape;
    CardGrade grade;

    public Card(CardShape shape, CardGrade grade) {
        this.shape = shape;
        this.grade = grade;
    }

    public CardShape getShape() { return shape; }

    public String getDelimiter() { return grade.getDelimiter(); }

    public int getGrade() {
        return grade.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return (grade == card.grade && shape == card.shape);
    }

    public int returnOneIfAceElseReturnZero() {
        if (grade.getDelimiter().equals("A"))
            return 1;
        else
            return 0;
    }
}
