package project.configs;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Config.Sources("system:properties")
public class SelenideConfiguratorTest {

    @BeforeAll
    static void setup() {
        System.out.println("This is setup method");
        SelenideConfig selenideConfig = ConfigFactory.newInstance().create(SelenideConfig.class);

        Configuration.browser = selenideConfig.browserName();
        Configuration.browserVersion = selenideConfig.browserVersion();
        Configuration.baseUrl = selenideConfig.baseUrl();

        if (selenideConfig.useSelenoid()) {
            Configuration.remote = selenideConfig.webDriverRemoteURL();
        }

    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
