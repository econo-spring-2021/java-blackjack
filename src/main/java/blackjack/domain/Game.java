package blackjack.domain;

import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int INIT_CARD_COUNT = 2;

    CardPack cardPack;
    List<User> users;
    Dealer dealer;

    public Game() {
        this.cardPack = new CardPack();
        this.users = new ArrayList<>();
        this.dealer = new Dealer();
    }

    public Game(List<User> users) {
        this.cardPack = new CardPack();
        this.users = users;
        this.dealer = new Dealer();
    }

    public Game(Dealer dealer) {
        this.cardPack = new CardPack();
        this.users = new ArrayList<>();
        this.dealer = dealer;
    }

    public List<User> getUsers() {
        return users;
    }

    public Dealer getDealer() {
        return dealer;
    }


    public List<UserInfoDto> getUserInfoDtos() {
        return users.stream().map(user -> new UserInfoDto(user.getName(), user.getOwnedCards()))
                .collect(Collectors.toList());
    }

    public DealerInfoDto getDealerInfoDto() {
        return dealer.getDealerInfoDto();
    }

    public DealerInfoDto getDealerRevealInfoDto() {
        return dealer.getDealerRevealInfoDto();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void distributeInitCard() {
        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            giveInitCardToUser(userIdx);
        }
        giveInitCardToDealer();
    }

    private void giveInitCardToUser(int userIdx) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            giveCardToUser(userIdx, cardPack.getRandomCard());
        }
    }

    private void giveCardToUser(int userIdx, Card card) {
        users.get(userIdx).addCard(card);
    }

    private void giveCardToUser(User user, Card card) {
        user.addCard(card);
    }


    private void giveInitCardToDealer() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            giveCardToDealer(cardPack.getRandomCard());
        }
    }

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public void playersGetMoreCard() {
        for (User user : users) {
            userGetMoreCardTillUnable(user);
        }

        dealerGetMoreCardTillUnable(dealer);
    }

    private void userGetMoreCardTillUnable(User user) {
        while (isAbleToGetMoreCard(user)) {
            giveCardToUser(user, cardPack.getRandomCard());
        }
    }

    private void dealerGetMoreCardTillUnable(Dealer dealer) {
        while (isAbleToGetMoreCard(dealer)) {
            OutputView.announcingDealerOneMoreCard();

            giveCardToDealer(cardPack.getRandomCard());
        }
    }

    private boolean isAbleToGetMoreCard(User user) {
        boolean isPossible = user.isPossibleToGetMoreCard();
        if (!isPossible) {
            return false;
        }

        OutputView.askGetMoreCard(user.getName());
        boolean didWanted = InputView.getYesOrNo().equals("y");
        if (!didWanted) {
            return false;
        }

        return true;
    }

    private boolean isAbleToGetMoreCard(Dealer dealer) {
        if (dealer.isPossibleToGetMoreCard()) {
            return true;
        }

        return false;
    }
}
