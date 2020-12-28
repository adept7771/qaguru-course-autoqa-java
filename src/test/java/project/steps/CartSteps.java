package project.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CartSteps {
    @Step("Добавляем первый товар на странице в корзину")
    public void addFirstItemToCart() {
        $x("//div[text()='В корзину']").click();
    }

    @Step("Проверяем счетчик корзины")
    public void checkCartIcon(String textInIconToCheck) {
        $x("//a[@data-widget='cart']//span[contains(@class, 'f-caption--bold')]")
                .shouldHave(Condition.matchText(textInIconToCheck));
    }


}
