package project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class AnnotationStepsTest {

    @BeforeEach
    public void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@" + System.getProperty("remote.browser.url") + ":4444/wd/hub/";
        Configuration.startMaximized = true;
    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }

    @Test
    @DisplayName("Аннотированный позитивный тест")
    @Feature("Issues")
    @Story("Юзер создает ишью с тэгом")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner("Dmitry Potapov")
    @Severity(SeverityLevel.CRITICAL)
    void annotationPositiveStepsSelenideTest() {
        final BaseSteps steps = new BaseSteps();
        int randomNum = new Random().nextInt();
        steps.openGitHub();
        steps.loginToGitHub();
        steps.createValidIssue(randomNum);
        steps.checkIssue(randomNum);
        steps.cleanAfterTest();
    }

    @Test
    @DisplayName("Аннотированный негативный тест")
    @Feature("Issues")
    @Story("Юзер создает ишью с тэгом")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner("Bill Gates")
    @Severity(SeverityLevel.BLOCKER)
    void annotationNegativeStepsSelenideTest() {
        final BaseSteps steps = new BaseSteps();
        int randomNum = new Random().nextInt();
        steps.openGitHub();
        steps.loginToGitHub();
        steps.createInValidIssue(randomNum);
        steps.checkIssue(randomNum);
        steps.cleanAfterTest();
    }

    public static class BaseSteps {
        @Step("Открываем главную страницу гитхаба")
        public void openGitHub() {
            open("https://github.com/");
        }

        @Step("Логинимся")
        public void loginToGitHub() {
            $(Selectors.byText("Sign in")).click();
            $x("//input[@name='login']").val(Password.username);
            $x("//input[@name='password']").val(Password.password);
            $x("//input[@value='Sign in']").click();
        }

        @Step("Создаем валидное ишью")
        public void createValidIssue(int randomNum) {
            $(Selectors.withText("adept7771/qaguru-course")).click();
            $x("//span[@data-content='Issues']").click();
            $x("//span[text()='New issue']").click();
            $x("//input[@name='issue[title]']").val("Issue number " + randomNum);
            $x("//textarea[@name='issue[body]']").val("Issue description for " + randomNum);
            $x("//summary[@data-hotkey=\"l\"]").click();
            $x("//span[@class=\"name\" and text()='bug']").click();
            $x("//summary[@data-hotkey=\"l\"]").click();

            $(Selectors.byText("Submit new issue")).click();
        }

        @Step("Шаг падения теста на локаторе")
        public void createInValidIssue(int randomNum) {
            $(Selectors.withText("adept7771/qaguru-course")).click();
            $x("invalid_locator").click();
        }


        @Step("Проверяем ишью")
        public void checkIssue(int randomNum) {
            $x("//span[@data-content='Issues']").click();
            $$x("//a[@data-hovercard-type='issue']")
                    .find(Condition.text("Issue number " + randomNum)).click();
            $x("//span[@class=\"js-issue-title\"]")
                    .shouldHave(Condition.text("Issue number " + randomNum));
            $x("//a[@class=\"author text-bold link-gray\"]")
                    .shouldHave(Condition.text(Password.username));
            $$x("//div[@class='js-issue-labels labels css-truncate']")
                    .find(Condition.text("bug")).shouldBe(Condition.appear);
        }

        @Step("Очищаем репозиторий поле теста")
        public void cleanAfterTest() {
            $x("//span[@data-content='Issues']").click();
            $x("//input[@aria-label=\"Select all issues\"]").click();
            $x("//summary[@class=\"btn-link select-menu-button\" and contains(text(), 'Mark as')]").click();
            $x("//div[@class=\"select-menu-item-text\" and text()='Closed']").click();
        }
    }
}
