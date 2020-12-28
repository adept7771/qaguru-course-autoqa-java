package project;

import helpers.ServiceRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.steps.AuthorisationSteps;
import project.steps.CartSteps;
import project.steps.NavigationSteps;
import project.steps.SearchSteps;

public class OzonTests extends ServiceRunner {

    final String owner = "Dmitry Potapov";
    final String url = "https://ozon.ru/";

    @Test
    @DisplayName("Проверить переход в категорию смартфоны")
    @Feature("Issues")
    @Story("Навигация по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkSmartphonesCatalogueTest() {
        navigationSteps.openStartPage();
        navigationSteps.openCatalogue();
        navigationSteps.goToSmartphones();
        navigationSteps.checkH1Smartphones();
    }

    @Test
    @DisplayName("Поиск выдает хотя бы 1 совпадение искомого запроса")
    @Feature("Issues")
    @Story("Поиск по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void searchTextTest() {
        navigationSteps.openStartPage();
        String phoneName = "iPhone";
        searchSteps.searchInSearchInput(phoneName);
        searchSteps.checkSearchResultsForOneMatch(phoneName);
    }

    @Test
    @DisplayName("При добавлении товара - корзина становится равна 1")
    @Feature("Issues")
    @Story("Работа корзины")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkCartIconTest() {
        navigationSteps.openStartPage();
        cartSteps.addFirstItemToCart();
        cartSteps.checkCartIcon("1");
    }

    final NavigationSteps navigationSteps = new NavigationSteps();
    final SearchSteps searchSteps = new SearchSteps();
    final CartSteps cartSteps = new CartSteps();
    final AuthorisationSteps authorisationSteps = new AuthorisationSteps();

    @Test
    @DisplayName("Все города в выпадающем списке уникальны")
    @Feature("Issues")
    @Story("Навигация по магазину")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void checkAllCitiesIsUnique() {
        navigationSteps.openStartPage();
        navigationSteps.citiesChangeMenuEnter();
        navigationSteps.citiesMenuUniqueCheck();
    }

    @Test
    @DisplayName("Только авторизированный пользователь может ввести промокод")
    @Feature("Issues")
    @Story("Работа промокодов")
    @Link(url = url, name = "Проверка функциональности магазина")
    @Owner(owner)
    @Severity(SeverityLevel.CRITICAL)
    void onlyAuthorisedUsersUsePromoTest() {
        navigationSteps.openStartPage();
        navigationSteps.enterPromoCode("testCode");
        authorisationSteps.needAuthorisationNotification();
    }
}
