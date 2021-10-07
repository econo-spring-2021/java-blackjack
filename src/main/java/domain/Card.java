package domain;

public class Card {
    public enum Type {
        클로버, 하트, 다이아몬드, 스페이드
    }

    private final Type type;
    private final String number;

    public Card(Type type, String number) {
        this.type = type;
        this.number = number;
    }

    public String getCardName() {
        return number + type;
    }

    public String getNumber() {
        return number;
    }
}
