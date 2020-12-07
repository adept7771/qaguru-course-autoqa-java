package project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @Test
    void alfabankOldDepositsTest() {
        open("https://alfabank.ru/make-money/");
        $x("//button[text()='Архивные счета и депозиты']").click();
        $$x("//div[@data-widget-name='CatalogCard']")
                .shouldHave(CollectionCondition.size(5));
    }

    @Test
        // страхования не нашел. Выбрал другой пункт выпадающего меню "Накопительные счета"
    void siblingPrecedingClosestParentTest() {
        open("https://alfabank.ru/make-money/");
        $x("//a[@title='Вклады']").hover();
        $x("//div[text()='Накопительные счета']").click();
        // вариации на тему наследников:
        $x("//*[contains(text(),'Страхование вкладов')]").closest("button").click();
        $x("//span[text()='Описание']").parent().click();
        $(Selectors.withText("Открыть накопительный счёт"))
                .should(Condition.appear);
        $x("//button[@data-test-id='tabs-list-tabTitle-0']").sibling(1).click();
        $(Selectors.withText("Что такое вклад"))
                .should(Condition.appear);
        $x("//button[@data-test-id='tabs-list-tabTitle-2']").preceding(0).click();
        $(Selectors.withText("Федеральный закон от 23.12.2003"))
                .should(Condition.appear);
    }
}
