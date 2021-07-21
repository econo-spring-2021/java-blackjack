package blackjack.domain;

import blackjack.domain.dto.DealerInfoDto;
import blackjack.domain.dto.UserInfoDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player{
    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_REVEAL_CARD_COUNT = 1;
    public static final int DEALER_MORE_CARD_COUNT = 1;
    public static final int DEALER_MORE_CARD_STANDARD_VALUE = 16;

    private int gotMoreCardCount = 0;
    private int winCount;
    private int drawCount;
    private int lostCount;

    public Dealer()
    {
        super();
        this.name = DEALER_NAME;
    }


    public DealerInfoDto toDto() {
        return new DealerInfoDto(name, ownedCards, winCount, drawCount, lostCount);
    }

    public DealerInfoDto toRevealDto() {
        List<Card> revealCards = new ArrayList<>();
        for (int i = 0; i < DEALER_REVEAL_CARD_COUNT; i++) {
            revealCards.add(ownedCards.getCard(i));
        }

        return new DealerInfoDto(name, revealCards);
    }

    public void addCard(Card card) {
        gotMoreCardCount++;

        super.addCard(card);
    }

    public boolean isPossibleToGetMoreCard() {
        if (gotMoreCardCount < DEALER_MORE_CARD_COUNT &&
                ownedCards.getCardsValueSum() <= DEALER_MORE_CARD_STANDARD_VALUE) {
            return true;
        }

        return false;
    }

    public void judgeDealerResult(List<UserInfoDto> userInfoDtos) {
        calculateDealerLoseCount(userInfoDtos);
        calculateDealerDrawCount(userInfoDtos);
        winCount = userInfoDtos.size() - lostCount - drawCount;
    }

    private void calculateDealerDrawCount(List<UserInfoDto> userInfoDtos) {
        int drawCount = 0;
        for (UserInfoDto userInfoDto : userInfoDtos) {
            drawCount += userInfoDto.returnOneIfDrawerElseReturnZero();
        }

        this.drawCount = drawCount;
    }

    private void calculateDealerLoseCount(List<UserInfoDto> userInfoDtos) {
        int lostCount = 0;
        for (UserInfoDto userInfoDto : userInfoDtos) {
            lostCount += userInfoDto.returnOneIfWinnerElseReturnZero();
        }

        this.lostCount = lostCount;
    }
}
