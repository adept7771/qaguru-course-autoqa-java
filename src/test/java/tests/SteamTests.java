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

public class SteamTests extends TestConfigurator {

    final String owner = "Dmitry Potapov";
    final String url = "https://store.steampowered.com/";
    final String projectName = "Steam";
    final NavigationSteps navigationSteps = new NavigationSteps();
    final SearchSteps searchSteps = new SearchSteps();
    final CartSteps cartSteps = new CartSteps();
    final AuthorisationSteps authorisationSteps = new AuthorisationSteps();

    @Test
    @AllureId("1743")
    @DisplayName("Check Steam can be installed")
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
    @AllureId("1744")
    @DisplayName("Search results match one item at least")
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
    @AllureId("1742")
    @DisplayName("All languages is unique")
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
    @AllureId("1741")
    @DisplayName("Items counter in cart is changing")
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
    @AllureId("1739")
    @DisplayName("Enter with incorrect login data")
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
    @AllureId("1740")
    @DisplayName("Test which falls all the time")
    @Feature("Always fall feature")
    @Link(url = url, name = projectName)
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void alwaysFallTest() {
        Assertions.fail("This test will fall all the time and need to exist to demonstrate error");
    }
}
