package helpers;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.Props;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

/**
 * All options for launching tests will try to load values from system environments, then if will be set from
 * properties file. System variables have a highest priority. If variable is not defined it will be defined
 * from properties file.
 */
public class TestConfigurator {

    @BeforeEach
    public void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (System.getProperty("runViaSelenoid") == null) {
            System.setProperty("runViaSelenoid", String.valueOf(Props.runViaSelenoid));
        }
        if (System.getProperty("selenoidEnableVideo") == null) {
            System.setProperty("selenoidEnableVideo", String.valueOf(Props.selenoidEnableVideo));
        }
        if (System.getProperty("selenoidEnableVNC") == null) {
            System.setProperty("selenoidEnableVNC", String.valueOf(Props.selenoidEnableVNC));
        }
        if (System.getProperty("selenoidInstance") == null) {
            System.setProperty("selenoidInstance", Props.selenoidInstance.val);
        }

        if (System.getProperty("runViaSelenoid").equals("true")) {
            capabilities.setCapability("enableVideo",
                    Boolean.parseBoolean(System.getProperty("selenoidEnableVideo")));
            capabilities.setCapability("videoFrameRate", 24);
            capabilities.setCapability("enableVNC",
                    Boolean.parseBoolean(System.getProperty("selenoidEnableVNC")));
            Configuration.remote = System.getProperty("selenoidInstance");
        }

        Configuration.browserCapabilities = capabilities;

        if (System.getProperty("browser") == null) {
            Configuration.browser = Props.browser.name();
        } else {
            Configuration.browser = System.getProperty("browser");
        }
        if (System.getProperty("startMaximized") == null) {
            Configuration.startMaximized = Props.startMaximized;
        } else {
            Configuration.startMaximized = Boolean.parseBoolean(System.getProperty("startMaximized"));
        }
        if (System.getProperty("selenideWaitTimeout") == null) {
            Configuration.timeout = Props.selenideWaitTimeout;
        } else {
            Configuration.timeout = Integer.parseInt(System.getProperty("selenideWaitTimeout"));
        }

    }

    @AfterEach
    @Step("Attachments")
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
}
