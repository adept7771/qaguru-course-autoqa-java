package project;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YandexTest {
  @Test
  void successTest() {
    assertEquals(2 * 2, 4);
  }

  @Test
  void negativeTest() {
    assertEquals(2 * 2, 5);
  }
}
