package project.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class NavigationSteps {

    @Step("Открываем главную страницу озона")
    public void openStartPage() {
        open("https://ozon.ru/");
    }

    @Step("Открыть каталог")
    public void openCatalogue() {
        $x("//div[@class='ui-n1' and text()='Каталог']").click();
    }

    @Step("Перейти в категорию Смартфоны")
    public void goToSmartphones() {
        $x("//a[contains(@href, 'smartfony')]").click();
    }

    @Step("Должен загрузиться заголовок Смартфоны")
    public void checkH1Smartphones() {
        $x("//h1[contains(text(), 'Смартфоны')]").shouldBe(Condition.visible);
    }

    @Step("Переход в меню выбора города")
    public void citiesChangeMenuEnter() {
        $x("(//div[@class='ui-n1'])[1]").click();
    }

    @Step("Проверка, что все города в списке уникальны")
    public void citiesMenuUniqueCheck() {
        $x("(//a[@class='a7'])[1]").shouldBe(Condition.visible);
        ElementsCollection elementsCollection = $$x("//a[@class='a7']");
        for (int i = 0; i < elementsCollection.size(); i++) {
            int matches = 0;
            SelenideElement currentElement = elementsCollection.get(0);
            for (SelenideElement tmpElement : elementsCollection) {
                if (tmpElement.getText().equals(currentElement.getText())) {
                    matches += 1;
                }
                if (matches > 1) {
                    Assertions.fail("Город " + currentElement.getText() + " повторяется в списке более 1 раза");
                }
            }
        }
        Assertions.assertTrue(true);
    }


}
