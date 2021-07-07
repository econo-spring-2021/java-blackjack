package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Game;
import blackjack.domain.UserInfoDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputView {

    private static final String ASKING_USER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String DISTRIBUTING_INIT_CARD_MESSAGE = "딜러와 %s에게 각각 %d장의 카드를 나누었습니다.\n";
    private static final String DEALER_NAME = "딜러";

    public static void askUserNames() {
        System.out.println(ASKING_USER_NAMES_MESSAGE);
    }

    public static void revealInitCard(List<UserInfoDto> userInfoDtos, List<Card> dealerRevealCards) {
        List<String> userNames = new ArrayList<>();
        for (UserInfoDto dto : userInfoDtos) {
            userNames.add(dto.getName());
        }

        announceDistributingInitCard(userNames);
        for (UserInfoDto dto : userInfoDtos) {
            printPlayersOwnedCards(true, dto.getName(), dto.getOwnedCards());
        }
        printPlayersOwnedCards(false, DEALER_NAME, dealerRevealCards);
    }

    public static void announceDistributingInitCard(List<String> users) {
        String userNames = String.join(",", users);
        System.out.printf(DISTRIBUTING_INIT_CARD_MESSAGE, userNames, Game.INIT_CARD_COUNT);
    }

    public static void printPlayersOwnedCards(boolean isUser, String name, List<Card> cards) {
        String message = name;
        if (isUser) message += "카드";
        message += ": ";
        message += String.join(",", parseCardListToStringList(cards));
        message += "\n";

        System.out.print(message);
    }

    public static List<String> parseCardListToStringList(List<Card> cards) {
        List<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(card.getValue() + card.getShape().getKoreanName());
        }

        return cardStrings;
    }
}
