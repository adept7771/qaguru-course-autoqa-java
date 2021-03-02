package properties;

import com.codeborne.selenide.Browsers;
import helpers.SelenoidInstance;

public class Props {

    public static boolean runViaSelenoid = true; // run tests in selenoid
    public static SelenoidInstance selenoidInstance = SelenoidInstance.qaGuruSchool;
    // ^ address of selenoid machine
    public static boolean selenoidEnableVideo = true;
    public static boolean selenoidEnableVNC = true;

    public static boolean startMaximized = false; // start browser maximised
    public static int selenideWaitTimeout = 6000; // time to wait elements by Selenide
    public static String browser = Browsers.CHROME; // Browser to start tests with
}
