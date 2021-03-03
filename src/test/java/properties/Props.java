package properties;

import com.codeborne.selenide.Browsers;
import helpers.additionalClasses.SelenoidInstance;
import helpers.additionalClasses.WebVideoStorage;

public class Props {

    public static String runViaSelenoid = "true"; // run tests in selenoid
    public static SelenoidInstance selenoidInstance = SelenoidInstance.qaGuruSelenoid; // address of selenoid machine
    public static WebVideoStorage webVideoStorage = WebVideoStorage.qaGuruStorage; // video storage for selenoid

    public static String selenoidEnableVideo = "true";
    public static String selenoidEnableVNC = "true";

    public static String startMaximized = "false"; // start browser maximised
    public static String selenideWaitTimeout = "6000"; // time to wait elements by Selenide
    public static String browser = Browsers.CHROME; // Browser to start tests with
}
