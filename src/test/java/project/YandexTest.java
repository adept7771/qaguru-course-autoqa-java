package project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class YandexTest {
  String keyWord = "selenide";

  @Test
  void yandexTest()  {
    open("https://yandex.ru");
    $x("//input[@aria-label='Запрос']").setValue("Selenide").pressEnter();
    int assertionsMatches = 0;
    for(SelenideElement element : $$x("//div[@class='organic__url-text']")){
      if(element.text().toLowerCase().contains(keyWord)){
        assertionsMatches += 1;
      }
    }
    Assertions.assertTrue(assertionsMatches >= 5,
            "Matches for search is: " + assertionsMatches + " it's less then expected (5)");
  }

  @Test
  void searchGoogle() {
    open("https://google.com/");
    $(byName("q")).val(keyWord).pressEnter();
    $("#search").shouldHave(Condition.text("selenide.org"));
  }
}
