package testSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CartSteps {
    @Step("Добавляем первый товар на странице результатов в корзину")
    public void addFirstItemToCart() {
        $x("//span[@class='title']").click();
        $x("//div[@class='btn_addtocart']").click();
    }

    @Step("Проверяем счетчик корзины")
    public void checkCartIcon(String textInIconToCheck) {
        $x("//span[@id='cart_item_count_value']")
                .shouldHave(Condition.text(textInIconToCheck));
    }


}
