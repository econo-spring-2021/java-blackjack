package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int INIT_CARD_COUNT = 2;

    CardPack cardPack = new CardPack();
    List<User> users = new ArrayList<>();
    Dealer dealer = new Dealer();

    //
    public List<User> getUsers() {
        return this.users;
    }

    public Dealer getDealer() {
        return this.dealer;
    }
    //

    public List<PlayerInfoDto> getUserInfoDtos() {
        List<PlayerInfoDto> playerInfoDtos = new ArrayList<>();
        for (User user : users) {
            PlayerInfoDto dto = new PlayerInfoDto(user.getName(), user.getOwnedCards());
            playerInfoDtos.add(dto);
        }

        return playerInfoDtos;
    }

    public PlayerInfoDto getDealerInfoDto() {
        return dealer.getDealerInfoDto();
    }

    public PlayerInfoDto getDealerRevealCards() {
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

    public void giveInitCardToUser(int userIdx) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            giveCardToUser(userIdx, cardPack.getRandomCard());
        }
    }

    public void giveCardToUser(int userIdx, Card card) {
        users.get(userIdx).addCard(card);
    }

    public void giveInitCardToDealer() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            giveCardToDealer(cardPack.getRandomCard());
        }
    }

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public void playersGetMoreCard() {
        for (User user : users) {
            user.getMoreCardTillUnableOrDeny(cardPack);
        }
        dealer.getOneMoreCardIfPossible(cardPack);
    }
}
