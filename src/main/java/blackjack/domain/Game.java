package blackjack.domain;

import blackjack.domain.dto.PlayerInfoDto;
import blackjack.domain.dto.UserInfoDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static final int INIT_CARD_COUNT = 2;
    
    List<User> users;
    Dealer dealer;

    public Game() {
        this.users = new ArrayList<>();
        this.dealer = new Dealer();
    }

    public Game(List<User> users) {
        this.users = users;
        this.dealer = new Dealer();
    }

    public Game(Dealer dealer) {
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
        return users.stream().map(UserInfoDto::new).collect(Collectors.toList());
    }

    public PlayerInfoDto getDealerInfoDto() {
        return new PlayerInfoDto(dealer);
    }

    public PlayerInfoDto getDealerRevealInfoDto() {
        return dealer.toRevealDto();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void getBetFromUsers() {
        for (User user : users) {
            OutputView.askUserBet(user.getName());

            int bet = InputView.getUserBet();
            user.setBet(bet);
        }
    }

    public void distributeInitCard() {
        for (int userIdx = 0; userIdx < users.size(); userIdx++) {
            giveInitCardToUser(userIdx);
        }
        giveInitCardToDealer();
    }

    public void judgePlayerBlackjack() {
        users.forEach(User::judgeBlackjack);
//        for (User user : users) {
//            user.judgeBlackjack();
//        }
        dealer.judgeBlackjack();
    }

    private void giveInitCardToUser(int userIdx) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            giveCardToUser(userIdx, CardPack.getInstance().getRandomCard());
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
            dealer.addCard(CardPack.getInstance().getRandomCard());
        }
    }

    public void playersGetMoreCard() {
        for (User user : users) {
            userGetMoreCardTillUnable(user);
        }

        dealerGetMoreCardTillUnable();
    }

    private void userGetMoreCardTillUnable(User user) {
        while (isAbleToGetMoreCard(user)) {

            giveCardToUser(user, CardPack.getInstance().getRandomCard());
            OutputView.printPlayersOwnedCards(user.getName(), user.getOwnedCards());
        }
    }

    private void dealerGetMoreCardTillUnable() {
        while (dealer.isPossibleToGetMoreCard()) {
            OutputView.announcingDealerOneMoreCard();

            dealer.addMoreCard(CardPack.getInstance().getRandomCard());
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

    public void judgePlayerBurst() {
        users.forEach(User::judgeBurst);
//        for (User user : users) {
//            user.judgeBurst();
//        }
        dealer.judgeBurst();
    }

    public void judgeUsersGameResult() {
        boolean dealerBlackjack = dealer.isBlackjack();
        boolean dealerBurst = dealer.getIsBurst();
        int dealerScore = dealer.getOwnedCards().getScore();
        for (User user : users) {
            user.judgeResult(dealerBlackjack, dealerBurst, dealerScore);
        }
    }

    public void judgePlayersIncome() {
        for (User user : users) {
            if (user.gameResult == GameResult.WIN) {
                judgePlayerIncomeOnUserWin(user);
                continue;
            }

            if (user.gameResult == GameResult.LOSE) {
                judgePlayerIncomeOnUserLose(user);
            }
        }
    }

    private void judgePlayerIncomeOnUserWin(User user) {
        int userBet = user.getBet();

        if (user.isBlackjack()) {
            user.earnBlackjackBet(userBet);
            dealer.loseBlackjackBet(userBet);
            return;
        }

        user.earnBet(userBet);
        dealer.loseBet(userBet);
    }

    private void judgePlayerIncomeOnUserLose(User user) {
        int userBet = user.getBet();

        user.loseBet(userBet);
        dealer.earnBet(userBet);
    }

}
