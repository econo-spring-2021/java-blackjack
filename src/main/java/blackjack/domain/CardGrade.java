package blackjack.domain;

public enum CardGrade {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JUMP("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private String delimiter;
    private int value;

    CardGrade(String delimiter, int value) {
        this.delimiter = delimiter;
        this.value = value;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public int getValue() {
        return value;
    }
}
