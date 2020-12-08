package project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class AnnotationStepsTest {

    @BeforeEach
    void setup(){
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Аннотированный тест")
    @Feature("Issues")
    @Story("Юзер оздает ишью с тэгом")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner("Dmitry Potapov")
    @Severity(SeverityLevel.CRITICAL)
    void annotationStepsSelenideTest() {
        final BaseSteps steps = new BaseSteps();
        int randomNum = new Random().nextInt();
        steps.openGitHub();
        steps.createIssue(randomNum);
        steps.checkIssue(randomNum);
        steps.cleanAfterTest();
    }

    public static class BaseSteps {
        @Step("Открываем главную страницу гитхаба")
        public void openGitHub() {
            open("https://github.com/");
        }

        @Step("Создаем ишью")
        public void createIssue(int randomNum) {
            $(Selectors.byText("Sign in")).click();
            $x("//input[@name='login']").val(Password.username);
            $x("//input[@name='password']").val(Password.password);
            $x("//input[@value='Sign in']").click();
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
