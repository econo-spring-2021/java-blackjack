import controller.Game;
import domain.Card;
import domain.Cards;
import domain.Player;
import domain.Players;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private static Players players;
    private static Cards cards;
    private static Player dealer;
    private static Player asong;
    private static Player hero;

    @BeforeAll
    public static void setGame() throws IOException {
        players = new Players(Game.setPlayerName("asong,hero"));
        dealer = players.getPlayers().get(0);
        asong = players.getPlayers().get(1);
        hero = players.getPlayers().get(2);

        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(new Card(Card.Type.클로버, "2")); //딜러
        cardList.add(new Card(Card.Type.하트, "7")); //딜러
        cardList.add(new Card(Card.Type.다이아몬드, "8")); //asong
        cardList.add(new Card(Card.Type.클로버, "9")); //asong
        cardList.add(new Card(Card.Type.스페이드, "4")); //hero
        cardList.add(new Card(Card.Type.클로버, "K")); //hero
        cardList.add(new Card(Card.Type.스페이드, "2")); //딜러

        cards = new Cards(cardList);

        //플레이어에게 두 개의 카드 주기
        players.giveTwoCards(cards);
        //딜러 한 장의 카드 더 주기
        players.getDealerOneMoreCard(cards);
    }

    @DisplayName("giveTwoCards 테스트")
    @Test
    public void giveTwoCards() {
        assertThat(dealer.getCards().getCards().get(0).getCardName()).isEqualTo("2클로버");
        assertThat(dealer.getCards().getCards().get(1).getCardName()).isEqualTo("7하트");

        assertThat(asong.getCards().getCards().get(0).getCardName()).isEqualTo("8다이아몬드");
        assertThat(asong.getCards().getCards().get(1).getCardName()).isEqualTo("9클로버");

        assertThat(hero.getCards().getCards().get(0).getCardName()).isEqualTo("4스페이드");
        assertThat(hero.getCards().getCards().get(1).getCardName()).isEqualTo("K클로버");
    }

    @DisplayName("getDealerOneMoreCard 테스트")
    @Test
    public void getDealerOneMoreCardTest() {
        assertThat(dealer.getCards().getCards().get(2).getCardName()).isEqualTo("2스페이드");
    }

    @DisplayName("setWinOrLose 테스트")
    @Test
    public void setWinOrLoseTest() {
        players.setWinOrLose();
        assertThat(asong.getResult()).isEqualTo("승");
        assertThat(hero.getResult()).isEqualTo("승");
    }
}
