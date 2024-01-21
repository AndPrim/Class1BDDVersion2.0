package page;

import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public TransferPage() {
        $(byText("Пополнение карты")).shouldBe(visible);
    }
    public DashBoardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeTransfer(amountToTransfer, cardInfo);
        return new DashBoardPage();
    }
    public void makeTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        $("[data-test-id='amount'] input").setValue(amountToTransfer);
        $("[data-test-id='from'] input").setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }
    public void findErrorMessage(String expectedText) {
        $("[data-test-id='error-notification'] .notification__content")
                .shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
    }
}
