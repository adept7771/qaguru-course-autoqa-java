package properties;

import helpers.additionalClasses.Browser;
import helpers.additionalClasses.SelenoidInstance;

public class Props {

    public static boolean runViaSelenoid = true; // run tests in selenoid
    public static SelenoidInstance selenoidInstance = SelenoidInstance.qaGuruSchool;
    // ^ address of selenoid machine
    public static boolean selenoidEnableVideo = true;
    public static boolean selenoidEnableVNC = true;

    public static boolean startMaximized = false; // start browser maximised
    public static int selenideWaitTimeout = 6000; // time to wait elements by Selenide
    public static Browser browser = Browser.FIREFOX; // Browser to start tests with
}
