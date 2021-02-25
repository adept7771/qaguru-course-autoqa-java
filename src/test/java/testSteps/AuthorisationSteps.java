package testSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorisationSteps {
    @Step("Wrong credentials alert is showed")
    public void checkWrongCredsNotification() {
        $x("//div[@id='error_display']").shouldBe(Condition.appears);
    }

    @Step("Go to login page")
    public void goToLoginPage() {
        $x("//a[@class='global_action_link' and text()='login']").click();
    }

    @Step("Enter in service with authorisation data")
    public void loginWithData(String login, String password) {
        $x("//input[@id='input_username']").val(login);
        $x("//input[@id='input_password']").val(password).pressEnter();
    }
}
