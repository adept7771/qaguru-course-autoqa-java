package helpers;

import com.codeborne.selenide.Configuration;
import helpers.additionalClasses.Browser;
import helpers.additionalClasses.SelenoidInstance;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.TestsProperties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class TestConfigurator {

    @BeforeEach
    public void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (TestsProperties.runViaSelenoid || "true".equals(System.getProperty("runViaSelenoid"))) {
            if (TestsProperties.selenoidEnableVideo || System.getProperty("selenoidEnableVideo").equals("true")) {
                capabilities.setCapability("enableVideo", true);
                capabilities.setCapability("videoFrameRate", 24);
            }
            if (TestsProperties.selenoidEnableVNC || System.getProperty("selenoidEnableVNC").equals("true")) {
                capabilities.setCapability("enableVNC", true);
            }
            if (TestsProperties.selenoidInstance.equals(SelenoidInstance.localMachine)
                    || SelenoidInstance.localMachine.name().equals(System.getProperty("selenoidInstance"))){

                Configuration.remote = SelenoidInstance.localMachine.val;

            } else if (TestsProperties.selenoidInstance.equals(SelenoidInstance.qaGuruSchool)
                    || SelenoidInstance.qaGuruSchool.name().equals(System.getProperty("selenoidInstance"))) {

                Configuration.remote = SelenoidInstance.qaGuruSchool.val;
            }
        }
        Configuration.browserCapabilities = capabilities;

        if(System.getProperty("browser") != null){
            Configuration.browser = System.getProperty("browser");
        }
        else {
            Configuration.browser = TestsProperties.browser.name();
        }

        if("true".equals(System.getProperty("startMaximized")) || TestsProperties.startMaximized){
            Configuration.startMaximized = TestsProperties.startMaximized;
        }
        if(System.getProperty("selenideWaitTimeout") != null){
            Configuration.timeout = Integer.parseInt(System.getProperty("selenideWaitTimeout"));
        }
        else {
            Configuration.timeout = TestsProperties.selenideWaitTimeout;
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
