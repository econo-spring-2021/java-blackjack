package blackjack.view;

import blackjack.domain.*;
import blackjack.domain.dto.PlayerInfoDto;
import blackjack.domain.dto.UserInfoDto;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String SPACE = " ";
    private static final String ASKING_USER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";
    private static final String ASKING_USER_BET_MESSAGE = "%s의 배팅 금액은?\n";
    private static final String DISTRIBUTING_INIT_CARD_MESSAGE = "%s와 %s에게 각각 %d장의 카드를 나누었습니다.\n";
    private static final String ASKING_GET_MORECARD_MESSAGE = "는 한장의 카드를 더 받겠습니까? (예는 y, 아니요는 n)";
    private static final String DEALER_ONEMORE_CARD_MESSAGE = "는 16이하라 한장의 카드를 더 받았습니다.";

    private static final String GAME_RESULT_TITLE = "## 최종 승패";
    private static final String GAME_RESULT_MESSAGE = "%s: %d\n";

    public static void askUserNames() {
        System.out.println(ASKING_USER_NAMES_MESSAGE);
    }

    public static void askUserBet(String name) {
        System.out.printf(ASKING_USER_BET_MESSAGE, name);
    }

    public static void announceDistribuyingInitCard(String dealerName, List<String> usersName) {
        String userNames = String.join(",", usersName);

        System.out.println(SPACE);
        System.out.printf(DISTRIBUTING_INIT_CARD_MESSAGE, dealerName, userNames, Game.INIT_CARD_COUNT);
    }

    public static void printPlayersOwnedCardWithScore(String name, OwnedCards ownedCards) {
        printPlayersOwnedCards(name, ownedCards);
        System.out.println(" => 결과: " + ownedCards.getScore());
    }

    public static void printPlayersOwnedCards(String name, OwnedCards cards) {
        String message = name + "카드: ";
        message += String.join(",", parseCardListToStringList(cards.getCards()));
        message += "\n";

        System.out.print(message);
    }

    public static List<String> parseCardListToStringList(List<Card> cards) {
        List<String> cardStrings = new ArrayList<>();
        for (Card card : cards) {
            cardStrings.add(card.getDelimiter() + card.getShape().getKoreanName());
        }

        return cardStrings;
    }

    public static void askGetMoreCard(String username) {
        System.out.println(username + ASKING_GET_MORECARD_MESSAGE);
    }

    public static void announcingDealerOneMoreCard() {
        System.out.println(SPACE);
        System.out.println(Dealer.DEALER_NAME + DEALER_ONEMORE_CARD_MESSAGE);
    }

    public static void printGameResult(PlayerInfoDto dealerInfoDto, List<UserInfoDto> userInfoDtos) {
        System.out.println(SPACE);
        System.out.println("## 최종 수익");
        System.out.printf(GAME_RESULT_MESSAGE, dealerInfoDto.getName(), dealerInfoDto.getIncome());

        for (UserInfoDto userInfoDto : userInfoDtos) {
            System.out.printf(GAME_RESULT_MESSAGE, userInfoDto.getName(), userInfoDto.getIncome());
        }

    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
