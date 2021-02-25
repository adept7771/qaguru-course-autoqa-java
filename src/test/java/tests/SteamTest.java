package tests;

import helpers.TestConfigurator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testSteps.AuthorisationSteps;
import testSteps.CartSteps;
import testSteps.NavigationSteps;
import testSteps.SearchSteps;

public class SteamTest extends TestConfigurator {

    final String owner = "Dmitry Potapov";
    final String url = "https://store.steampowered.com/";
    final String projectName = "Steam";
    final NavigationSteps navigationSteps = new NavigationSteps();
    final SearchSteps searchSteps = new SearchSteps();
    final CartSteps cartSteps = new CartSteps();
    final AuthorisationSteps authorisationSteps = new AuthorisationSteps();

    @Test
    @DisplayName("Проверить, что стим может быть установлен")
    @Feature("Navigation feature")
    @Story("Navigation links story")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void steamCanBeInstalledTest() {
        navigationSteps.openStartPage();
        navigationSteps.installSteam();
        navigationSteps.checkSteamCanBeInstalled();
    }

    @Test
    @DisplayName("Поиск выдает хотя бы 1 совпадение искомого запроса")
    @Feature("Search feature")
    @Story("Navigation links story")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void searchGameTest() {
        navigationSteps.openStartPage();
        String gameName = "Bannerlord";
        searchSteps.searchInSearchInput(gameName);
        searchSteps.checkSearchResultsForOneMatch(gameName);
    }

    @Test
    @DisplayName("Все языки в выпадающем списке уникальны")
    @Feature("Navigation feature")
    @Story("Personalisation story")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkAllLanguagesIsUniqueTest() {
        navigationSteps.openStartPage();
        navigationSteps.languagesMenuEnter();
        navigationSteps.languageMenuUniqueCheck();
    }

    @Test
    @DisplayName("Счетчик товаров в корзине изменяется")
    @Feature("Cart feature")
    @Story("Buying story")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkCartCounterTest() {
        navigationSteps.openStartPage();
        String gameName = "Bannerlord";
        searchSteps.searchInSearchInput(gameName);
        cartSteps.addFirstItemToCart();
        cartSteps.checkCartIcon("1");
    }

    @Test
    @DisplayName("Вход с не корректными регистрационными данными")
    @Feature("Login feature")
    @Story("Login from web story")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void incorrectSignInTest() {
        navigationSteps.openStartPage();
        authorisationSteps.goToLoginPage();
        authorisationSteps.loginWithData("wrong_login", "wrong_password");
        authorisationSteps.checkWrongCredsNotification();
    }

    @Test
    @DisplayName("Тест, который всегда падает")
    @Feature("Always fall feature")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void alwaysFallTest() {
        Assertions.fail("Этот тест всегда падает и должен просто существовать в отчете.");
    }
}
