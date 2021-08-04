package blackjack.controller;

import blackjack.domain.*;
import blackjack.domain.dto.DealerInfoDto;
import blackjack.domain.dto.PlayerInfoDto;
import blackjack.domain.dto.UserInfoDto;
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
        game.getBetFromUsers();

        game.distributeInitCard();
        game.judgePlayerBlackjack();
        revealInitialCards(game.getUserInfoDtos(), game.getDealerRevealInfoDto());

        game.playersGetMoreCard();

        game.judgePlayerBurst();
        showPlayerCardState();
        showGameResult();
    }

    public void generatePerson() {
        OutputView.askUserNames();
        List<String> UserNames = InputView.getUserNames();

        for (String userName : UserNames) {
            game.addUser(new User(userName));
        }
    }

    public void revealInitialCards(List<UserInfoDto> userInfoDtos, DealerInfoDto dealerRevealInfoDto) {
        OutputView.announceDistribuyingInitCard(dealerRevealInfoDto.getName(),
                userInfoDtos.stream().map(dto -> dto.getName()).collect(Collectors.toList()));

        for (PlayerInfoDto dto : userInfoDtos) {
            OutputView.printPlayersOwnedCards(dto.getName(), dto.getOwnedCards());
        }
        OutputView.printPlayersOwnedCards(dealerRevealInfoDto.getName(), dealerRevealInfoDto.getOwnedCards());
    }

    public void showPlayerCardState() {
        PlayerInfoDto dealerInfoDto = game.getDealerInfoDto();
        OutputView.printPlayersOwnedCardWithScore(
                dealerInfoDto.getName(), dealerInfoDto.getOwnedCards());

        List<UserInfoDto> userInfoDtos = game.getUserInfoDtos();
        for (PlayerInfoDto userInfoDto : userInfoDtos) {
            OutputView.printPlayersOwnedCardWithScore(
                    userInfoDto.getName(), userInfoDto.getOwnedCards());
        }
    }

    public void showGameResult() {
        game.judgeUsersResult();
        List<UserInfoDto> userInfoDtos = game.getUserInfoDtos();

        game.judgeDealerResult(userInfoDtos);
        DealerInfoDto dealerInfoDto = game.getDealerInfoDto();

        OutputView.printGameResult(dealerInfoDto, userInfoDtos);
    }
}
