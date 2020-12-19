package project;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotationStepsTest {

    @Test
    @DisplayName("Аннотированный позитивный тест")
    @Feature("Issues")
    @Story("Ассерт тру - позитивный")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner("Dmitry Potapov")
    @Severity(SeverityLevel.CRITICAL)
    void positiveTest() {
        Assertions.assertTrue(true);
    }

    @Test
    @DisplayName("Аннотированный негативный тест")
    @Feature("Issues")
    @Story("Ассерт тру - негативный")
    @Link(url = "https://testing.github.com", name = "Тестинг")
    @Owner("Bill Gates")
    @Severity(SeverityLevel.BLOCKER)
    void negativeTest() {
        Assertions.fail();
    }
}
