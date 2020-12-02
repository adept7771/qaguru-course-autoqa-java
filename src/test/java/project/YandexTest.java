package project;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class YandexTest {

  @Test
  void yandexTest() throws InterruptedException {
    open("https://yandex.ru");
    $x("//input[@aria-label='Запрос']").click();
    $x("//input[@aria-label='Запрос']").setValue("Selenide");
    $x("//button[@type='submit']").click();
    int assertionsMatches = 0;
    for(SelenideElement element : $$x("//div[@class='organic__url-text']")){
      if(element.text().toLowerCase().contains("selenide")){
        assertionsMatches += 1;
      }
    }
    Assertions.assertTrue(assertionsMatches >= 5,
            "Matches for search is: " + assertionsMatches + " it's less then expected (5)");
  }
}
