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
        steps.checkGitHubInterfaceExistencePositive();
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
