package blackjack.domain;

public enum CardShape {
    DIAMOND("다이아몬드"), HEART("히트"), CLOVER("클로버"), SPADE("스페이드");

    private String koreanName;

    CardShape(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return this.koreanName;
    }
}
