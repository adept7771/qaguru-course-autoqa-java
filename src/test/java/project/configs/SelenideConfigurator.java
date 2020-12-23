package project.configs;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class SelenideConfigurator {

    @BeforeAll
    static void setup() {
        System.out.println("This is setup method");
        SelenideConfig selenideConfig = ConfigFactory.newInstance().create(SelenideConfig.class);

        Configuration.browser = selenideConfig.browserName();
        Configuration.browserVersion = selenideConfig.browserVersion();
        Configuration.baseUrl = selenideConfig.baseUrl();

        if (selenideConfig.selenideRemote()) {
            Configuration.remote = selenideConfig.webDriverRemoteURL();
        }

    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
