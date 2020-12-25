package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import helpers.ServiceRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class AnnotationStepsTest extends ServiceRunner {

    final String owner = "Dmitry Potapov";

    @Test
    @DisplayName("Аннотированный позитивный тест")
    @Feature("Issues")
    @Story("Юзер создает ишью с тэгом")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void annotationPositiveStepsSelenideTest() {
        final BaseSteps steps = new BaseSteps();
        int randomNum = new Random().nextInt();
        steps.openGitHub();
        steps.checkGitHubInterfaceExistencePositive();
    }

    @Test
    @DisplayName("Аннотированный негативный тест")
    @Feature("Issues")
    @Story("Юзер создает ишью с тэгом")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner(owner)
    @Severity(SeverityLevel.BLOCKER)
    void annotationNegativeStepsSelenideTest() {
        final BaseSteps steps = new BaseSteps();
        int randomNum = new Random().nextInt();
        steps.openGitHub();
        steps.checkGitHubInterfaceExistenceNegative();
    }

    public static class BaseSteps {
        @Step("Открываем главную страницу гитхаба")
        public void openGitHub() {
            open("https://github.com/");
        }

        @Step("Проверяем наличие управляющих кнопок на странице (позитивное)")
        public void checkGitHubInterfaceExistencePositive() {
            $(Selectors.byText("Sign in")).shouldBe(Condition.appears);
            $x("//input[@name='user_email']").shouldBe(Condition.appears);
        }

        @Step("Проверяем наличие управляющих кнопок на странице (негативное)")
        public void checkGitHubInterfaceExistenceNegative() {
            $(Selectors.byText("Sign in")).shouldBe(Condition.appears);
            $x("//input[@name='user_email_wrong']").shouldBe(Condition.appears);
        }
    }
}
