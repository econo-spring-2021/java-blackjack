package blackjack.domain;


public class CardPack {
    public static final int CARD_SHAPE_COUNT = 4;
    public static final int CARD_VALUE_COUNT = 13;

    boolean[][] cardIsSelected = new boolean[CARD_SHAPE_COUNT][CARD_VALUE_COUNT];

    // 분해 가능??
    public Card getRandomCard() {
        int randomShapeIdx;
        int randomValueIdx;
        boolean isSelected = false;
        do {
            randomShapeIdx = (int) Math.floor(Math.random() * 4);
            randomValueIdx = (int) Math.floor(Math.random() * 13);
            isSelected = checkIsSelected(randomShapeIdx, randomValueIdx);
        } while (isSelected);

        cardIsSelected[randomShapeIdx][randomValueIdx] = true;
        CardShape randomShape = CardShape.values()[randomShapeIdx];
        int randomValue = getCardValueFromIdx(randomValueIdx);
        return new Card(randomShape, randomValue);
    }

    public boolean checkIsSelected(int shapeIdx, int valueIdx) {
        return cardIsSelected[shapeIdx][valueIdx];
    }

    private int getCardValueFromIdx(int idx) {
        idx++;
        if (idx >= 11) return 10;
        else return idx;
    }
}
