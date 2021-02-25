package testSteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class NavigationSteps {

    @Step("Open main page")
    public void openStartPage() {
        open("https://store.steampowered.com/");
    }

    @Step("Go to install Steam")
    public void installSteam() {
        $x("//a[contains(text(), 'Install Steam')]").click();
    }

    @Step("Check that download link is appeared")
    public void checkSteamCanBeInstalled() {
        $x("//a[@class='about_install_steam_link']").shouldBe(Condition.appears);
    }

    @Step("Enter language menu")
    public void languagesMenuEnter() {
        $x("//span[@id='language_pulldown']").click();
    }

    @Step("Check all languages is unique")
    public void languageMenuUniqueCheck() {
        $x("//a[@class='popup_menu_item tight']").shouldBe(Condition.visible);
        ElementsCollection elementsCollection = $$x("//a[@class='popup_menu_item tight']");
        ArrayList<String> elementsTexts = new ArrayList<>();
        for (SelenideElement element : elementsCollection) {
            elementsTexts.add(element.getText());
        }
        for (int i = 0; i < elementsTexts.size(); i++) {
            int matches = 0;
            String currentText = elementsTexts.get(0);
            for (String tmpText : elementsTexts) {
                if (tmpText.equals(currentText)) {
                    matches += 1;
                }
                if (matches > 1) {
                    Assertions.fail("Язык " + currentText + " повторяется в списке более 1 раза");
                }
            }
        }
        Assertions.assertTrue(true);
    }
}
