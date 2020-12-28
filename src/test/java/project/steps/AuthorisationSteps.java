package project.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorisationSteps {
    @Step("Показано сообщение об отсутствии авторизаации у клиента")
    public void needAuthorisationNotification() {
        $x("//div[contains(text(), 'Вы не авторизованы')]").shouldBe(Condition.appears);
    }
}
