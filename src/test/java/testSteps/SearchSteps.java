package testSteps;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class SearchSteps {
    @Step("Ввести в поиск искомую фразу")
    public void searchInSearchInput(String phraseToSearch) {
        $x("//input[@id='store_nav_search_term']").val(phraseToSearch).pressEnter();
    }

    @Step("Проверить поисковую выдачу хотя бы на 1 совпадение")
    public void checkSearchResultsForOneMatch(String phraseToSearch) {
        Configuration.timeout = 10000;
        for (SelenideElement selenideElement : $$x("//span[@class='title']")) {
            if (selenideElement.getText().toLowerCase(Locale.ROOT)
                    .contains(phraseToSearch.toLowerCase(Locale.ROOT))) {
                Assertions.assertTrue(true);
                return;
            }
        }
        Assertions.fail("В найденных элементах не присутсвует слово " + phraseToSearch);
    }
}
