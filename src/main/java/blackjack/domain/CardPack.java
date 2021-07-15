package blackjack.domain;


import java.util.List;

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
        String randomeDelimeter = getDelimeterFromIdx(randomValueIdx);
        int randomValue = getCardValueFromIdx(randomValueIdx);
        return new Card(randomShape, randomeDelimeter, randomValue);
    }

    public boolean checkIsSelected(int shapeIdx, int valueIdx) {
        return cardIsSelected[shapeIdx][valueIdx];
    }

    public static String getDelimeterFromIdx(int idx) {
        idx++;
        if (idx == 1) {
            return "A";
        } else if (idx == 11) {
            return "J";
        } else if (idx == 12) {
            return "Q";
        } else if (idx == 13) {
            return "K";
        } else {
            return Integer.toString(idx);
        }
    }

    public static int getCardValueFromIdx(int idx) {
        idx++;
        if (idx >= 11) return 10;
        else return idx;
    }

    public static int getTotalScore(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            score += card.getValue();
        }

        int aceCount = getAceCount(cards);
        score += getMaxAddtitionAceScore(User.USER_LIMIT_CARD_VALUE - score, aceCount);

        if (score > User.USER_LIMIT_CARD_VALUE) {
            return 0;
        } else {
            return score;
        }
    }

    public static int getMaxAddtitionAceScore(int restScore, int aceCount) {
        if (restScore <= 0) {
            return 0;
        }

        int possibleCointToAdd = restScore / 9;
        int addedAceCount = Math.min(possibleCointToAdd, aceCount);
        int addedAceValue = addedAceCount * 9;

        return addedAceValue;
    }

    public static int getAceCount(List<Card> cards) {
        int count = 0;
        for (Card card : cards) {
            count += card.returnOneIfAceElseReturnZero();
        }

        return count;
    }
}
