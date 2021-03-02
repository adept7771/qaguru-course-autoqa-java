package helpers;

public enum SelenoidInstance {
    localMachine("http://localhost:4444/wd/hub/"),
    qaGuruSchool("https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/"),
    ;

    public String val;
    SelenoidInstance(String val) {
        this.val = val;
    }
}
