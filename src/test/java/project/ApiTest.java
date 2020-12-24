package project;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import project.configs.ApiConfig;
import project.configs.SelenideConfigurator;

public class ApiTest extends SelenideConfigurator {

    ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);

    @Test
    public void getCapitalOfCountryTest() {
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);
        System.out.println("\nBase url for test: " + apiConfig.baseUrl());
        System.out.println("Token: " + apiConfig.token() + "\n");
    }
}
