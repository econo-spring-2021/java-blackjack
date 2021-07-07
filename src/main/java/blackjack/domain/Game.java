package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int INIT_CARD_COUNT = 2;

    CardPack cardPack = new CardPack();
    List<User> users = new ArrayList<>();
    Dealer dealer = new Dealer();

    public List<User> getUsers() {
        return this.users;
    }

    public int getUsersCount() {
        return this.users.size();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void addUser(User user) {
        users.add(user);
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
}
