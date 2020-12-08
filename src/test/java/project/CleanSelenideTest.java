package project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CleanSelenideTest {

    @BeforeEach
    void setup(){
        Configuration.startMaximized = true;
    }

    @Test
    void cleanSelenideTest() {
        int randomNum = new Random().nextInt();

        open("https://github.com/");

        // create issue
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

        // check issue
        $x("//span[@data-content='Issues']").click();
        $$x("//a[@data-hovercard-type='issue']")
                .find(Condition.text("Issue number " + randomNum)).click();
        $x("//span[@class=\"js-issue-title\"]")
                .shouldHave(Condition.text("Issue number " + randomNum));
        $x("//a[@class=\"author text-bold link-gray\"]")
                .shouldHave(Condition.text(Password.username));
        $$x("//div[@class='js-issue-labels labels css-truncate']")
                .find(Condition.text("bug")).shouldBe(Condition.appear);

        // clean after test
        $x("//span[@data-content='Issues']").click();
        $x("//input[@aria-label=\"Select all issues\"]").click();
        $x("//summary[@class=\"btn-link select-menu-button\" and contains(text(), 'Mark as')]").click();
        $x("//div[@class=\"select-menu-item-text\" and text()='Closed']").click();
    }


}
