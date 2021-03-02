package helpers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.Props;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

/**
 * All options for launching tests will try to load values from system environments, then from
 * properties file. System variables have a highest priority. If variable is not defined it will be defined
 * from properties file.
 */
public class TestConfigurator {

    @BeforeEach
    public void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (System.getProperty("runViaSelenoid",
                String.valueOf(Props.runViaSelenoid))
                .equals("true")) {

            capabilities.setCapability("enableVideo", Boolean.valueOf(
                    System.getProperty("selenoidEnableVideo",
                            String.valueOf(Props.selenoidEnableVideo))));

            capabilities.setCapability("videoFrameRate", 24);

            capabilities.setCapability("enableVNC", Boolean.valueOf(
                    System.getProperty("selenoidEnableVNC",
                            String.valueOf(Props.selenoidEnableVNC))));

            Configuration.remote = System.getProperty("selenoidInstance", Props.selenoidInstance.val);
        }

        Configuration.browserCapabilities = capabilities;

        Configuration.browser = System.getProperty("browser", Props.browser);

        Configuration.startMaximized = Boolean.parseBoolean(
                System.getProperty("startMaximized", String.valueOf(Props.startMaximized)));

        Configuration.timeout = Long.parseLong(
                System.getProperty("selenideWaitTimeout", String.valueOf(Props.selenideWaitTimeout)));
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
}
