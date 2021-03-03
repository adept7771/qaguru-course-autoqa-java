package helpers.additionalClasses;

public enum SelenoidInstance {
    localSelenoid("http://localhost:4444/wd/hub/"),
    qaGuruSelenoid("https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/");

    public String val;
    SelenoidInstance(String val) {
        this.val = val;
    }
}
