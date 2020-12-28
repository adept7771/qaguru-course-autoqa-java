package project.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class SearchSteps {
    @Step("Ввести в поиск искомую фразу")
    public void searchInSearchInput(String phraseToSearch) {
        $x("//input[@name='search']").val(phraseToSearch).pressEnter();
    }

    @Step("Проверить поисковую выдачу хотя бы на 1 совпадение")
    public void checkSearchResultsForOneMatch(String phraseToSearch) {
        $x("//h2[contains(text(), 'Рекомендации для вас')]").click();
        for (SelenideElement selenideElement : $$x("//a[contains(@class, 'tile-hover-target')]")) {
            System.out.println(selenideElement.getText());
            if (selenideElement.getText().toLowerCase(Locale.ROOT)
                    .contains(phraseToSearch.toLowerCase(Locale.ROOT))) {
                Assertions.assertTrue(true);
                return;
            }
        }
        Assertions.fail("В найденных элементах не присутсвует слово " + phraseToSearch);
    }
}
