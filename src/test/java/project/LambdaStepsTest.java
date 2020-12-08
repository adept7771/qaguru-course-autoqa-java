package project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {

    @BeforeEach
    void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void lambdaStepsSelenideTest() {
        int randomNum = new Random().nextInt();
        Allure.feature("Issues");
        Allure.story("Моя любимая история");
        Allure.parameter("Repository", "Моя любимая репа");

        step("Открываем главную страницу гитхаба", () -> {
            open("https://github.com/");
        });

        step("Производим логин", (step) -> {
            step.parameter("name", "Секретик :3");
            $(Selectors.byText("Sign in")).click();
            $x("//input[@name='login']").val(Password.username);
            $x("//input[@name='password']").val(Password.password);
            $x("//input[@value='Sign in']").click();
        });

        step("Создаем ишью", (step) -> {
            $(Selectors.withText("adept7771/qaguru-course")).click();
            $x("//span[@data-content='Issues']").click();
            $x("//span[text()='New issue']").click();
            $x("//input[@name='issue[title]']").val("Issue number " + randomNum);
            $x("//textarea[@name='issue[body]']").val("Issue description for " + randomNum);
            $x("//summary[@data-hotkey=\"l\"]").click();
            $x("//span[@class=\"name\" and text()='bug']").click();
            $x("//summary[@data-hotkey=\"l\"]").click();

            $(Selectors.byText("Submit new issue")).click();
        });

        step("Проверяем ишью", () -> {
            $x("//span[@data-content='Issues']").click();
            $$x("//a[@data-hovercard-type='issue']")
                    .find(Condition.text("Issue number " + randomNum)).click();
            $x("//span[@class=\"js-issue-title\"]")
                    .shouldHave(Condition.text("Issue number " + randomNum));
            $x("//a[@class=\"author text-bold link-gray\"]")
                    .shouldHave(Condition.text(Password.username));
            $$x("//div[@class='js-issue-labels labels css-truncate']")
                    .find(Condition.text("bug")).shouldBe(Condition.appear);
        });

        step("Очистка после теста", () -> {
            $x("//span[@data-content='Issues']").click();
            $x("//input[@aria-label=\"Select all issues\"]").click();
            $x("//summary[@class=\"btn-link select-menu-button\" and contains(text(), 'Mark as')]").click();
            $x("//div[@class=\"select-menu-item-text\" and text()='Closed']").click();
        });
    }
}
