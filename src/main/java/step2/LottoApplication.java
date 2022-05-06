package step2;

import step2.domain.*;
import step2.view.InputView;
import step2.view.OutPutView;
import step2.view.dto.GameResultDto;
import step2.view.dto.PurchaseCountDto;
import step2.view.dto.PurchaseListDto;
import step2.view.dto.ReturnRateDto;

public class LottoApplication {

    public static void main(String[] args) {

        InputView inputView = new InputView();
        OutPutView outPutView = new OutPutView();

        int purchaseAmount = inputView.askPurchaseAmount();
        PurchaseMoney purchaseMoney = new PurchaseMoney(purchaseAmount);
        outPutView.show(new PurchaseCountDto(purchaseMoney).toString());

        PurchaseList purchaseList = new PurchaseList(purchaseMoney);
        outPutView.show(new PurchaseListDto(purchaseList).toString());

        String winnerInput = inputView.askWinnerInput();
        Winner winner = new Winner(winnerInput);
        inputView.close();

        LottoGame lottoGame = new LottoGame(purchaseList, winner);
        GameResult gameResult = lottoGame.calculateGameResult();
        ReturnRate returnRate = gameResult.calculateReturnRate(purchaseMoney);

        GameResultDto gameResultDto = new GameResultDto(gameResult);
        ReturnRateDto returnRateDto = new ReturnRateDto(returnRate);

        outPutView.showResultLine();
        outPutView.show(gameResultDto.toString());
        outPutView.show(returnRateDto.toString());
        outPutView.showEnd();

    }
}
