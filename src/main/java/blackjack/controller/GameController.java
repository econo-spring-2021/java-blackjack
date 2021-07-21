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
        revealInitCard(game.getUserInfoDtos(), game.getDealerRevealInfoDto());

        game.playersGetMoreCard();

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

    public void revealInitCard(List<UserInfoDto> userInfoDtos, DealerInfoDto dealerRevealInfoDto) {
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
        DealerInfoDto dealerInfoDto = game.getDealerInfoDto();
        List<UserInfoDto> userInfoDtos = game.getUserInfoDtos();

        int dealerScore = dealerInfoDto.getOwnedCards().getScore();
        judgeUserResult(userInfoDtos, dealerScore);

        int dealerWinCount = calculateDealerWinCount(userInfoDtos);
        int dealerDrawCount = calculateDealerDrawCount(userInfoDtos);
        int dealerLostCount = userInfoDtos.size() - dealerWinCount - dealerDrawCount;
        dealerInfoDto.setGameResult(dealerWinCount, dealerLostCount, dealerDrawCount);
        OutputView.printGameResult(dealerInfoDto, userInfoDtos);
    }

    private void judgeUserResult(List<UserInfoDto> userInfoDtos, int dealerScore) {
        for (UserInfoDto userInfoDto : userInfoDtos) {
            userInfoDto.judgeResult(dealerScore);
        }
    }

    private int calculateDealerDrawCount(List<UserInfoDto> userInfoDtos) {
        int drawCount = 0;
        for (UserInfoDto userInfoDto : userInfoDtos) {
            drawCount += userInfoDto.returnOneIfDrawerElseReturnZero();
        }

        return drawCount;
    }

    private int calculateDealerWinCount(List<UserInfoDto> userInfoDtos) {
        int winCount = 0;
        for (UserInfoDto userInfoDto : userInfoDtos) {
            winCount += userInfoDto.returnOneIfWinnerElseReturnZero();
        }

        return winCount;
    }
}
