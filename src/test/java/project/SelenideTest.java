package project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

  @Test
  void alfabankOldDepositsTest()  {
    open("https://alfabank.ru/make-money/");
    $x("//button[text()='Архивные счета и депозиты']").click();
    $$x("//div[@data-widget-name='CatalogCard']")
            .shouldHave(CollectionCondition.size(5));
  }

  @Test // страхования не нашел. Выбрал другой пункт выпадающего меню "Альфа счет"
  void siblingPrecedingClosestParentTest()  {
    open("https://alfabank.ru/make-money/");
    $x("//a[@title='Вклады']").hover();
    $x("//div[@data-test-id='Main-Header-Main-DesktopLayout']")
            .closest("href=\"/make-money/savings-account/alfa/\"").click();
    $x("//h1[text()='Накопительный Альфа-Счёт']").shouldBe(Condition.appear);
    Selenide.back();

  }


}
