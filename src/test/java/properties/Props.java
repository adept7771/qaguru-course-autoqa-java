package properties;

import helpers.additionalClasses.Browser;
import helpers.additionalClasses.SelenoidInstance;

public class Props {

    public static boolean runViaSelenoid = true; // run tests in selenoid
    public static SelenoidInstance selenoidInstance = SelenoidInstance.localMachine;
    // ^ address of selenoid machine
    public static boolean selenoidEnableVideo = true;
    public static boolean selenoidEnableVNC = true;

    public static boolean startMaximized = true; // start browser maximised
    public static int selenideWaitTimeout = 6000; // time to wait elements by Selenide
    public static Browser browser = Browser.CHROME; // Browser to start tests with
}
