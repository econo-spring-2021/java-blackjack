package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    Game game;

    public GameController() {
        this.game = new Game();
    }

    public GameController(Game game) {
        this.game = game;
    }

    public void play() {
        generatePerson();

        game.distributeInitCard();
        revealInitCard(game.getUserInfoDtos(), game.getDealerRevealCards());

        game.playersGetMoreCard();

        showPlayerCardState();
    }

    public void generatePerson() {
        OutputView.askUserNames();
        List<String> UserNames = InputView.getUserNames();

        for (String userName : UserNames) {
            game.addUser(new User(userName));
        }
    }

    public void revealInitCard(List<PlayerInfoDto> userInfoDtos, PlayerInfoDto dealerRevealInfoDto) {
        OutputView.announceDistribuyingInitCard(dealerRevealInfoDto.getName(),
                userInfoDtos.stream().map(dto -> dto.getName()).collect(Collectors.toList()));

        for (PlayerInfoDto dto : userInfoDtos) {
            OutputView.printPlayersOwnedCards(dto.getName(), dto.getOwnedCards());
        }
        OutputView.printPlayersOwnedCards(dealerRevealInfoDto.getName(), dealerRevealInfoDto.getOwnedCards());
    }

    public void showPlayerCardState() {
        List<PlayerInfoDto> userInfoDtos = game.getUserInfoDtos();
        for (PlayerInfoDto userInfoDto : userInfoDtos) {
            OutputView.printPlayersOwnedCardWithScore(
                    userInfoDto.getName(), userInfoDto.getOwnedCards());
        }

        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        OutputView.printPlayersOwnedCardWithScore(
                dealerInfoDto.getName(), dealerInfoDto.getOwnedCards());
    }
}
