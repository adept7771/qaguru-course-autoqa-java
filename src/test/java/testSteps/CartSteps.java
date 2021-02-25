package testSteps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class CartSteps {
    @Step("Add first game at page to cart")
    public void addFirstItemToCart() {
        $x("//span[@class='title']").click();
        $x("//div[@class='btn_addtocart']").click();
    }

    @Step("Check cart icon counter")
    public void checkCartIcon(String textInIconToCheck) {
        $x("//span[@id='cart_item_count_value']")
                .shouldHave(Condition.text(textInIconToCheck));
    }


}
