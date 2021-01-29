package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import helpers.ApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class ApiTests {
    ApiHelper apiHelper = new ApiHelper();

    @Test
    public void addToWishListTest() {
        Selenide.open("http://demowebshop.tricentis.com");
        // вытаскиваем куку из селенида, проверяя что вишлист = 0
        Set<Cookie> cookies = WebDriverRunner.getWebDriver().manage().getCookies();
        apiHelper.saveAuthorisationCookieFromSet(cookies);
        String text = Selenide.$x("//span[@class='wishlist-qty']").getText();
        Assertions.assertEquals("(0)",text);
        // трижды добавляем через апи с перехваченной кукой итем в вишлист
        apiHelper.addItemToWishList();
        apiHelper.addItemToWishList();
        apiHelper.addItemToWishList();
        Selenide.refresh();
        // проверяем, что в вишлисте стало 3 товара через счетчик
        text = Selenide.$x("//span[@class='wishlist-qty']").getText();
        Assertions.assertEquals("(3)",text);
    }
}
