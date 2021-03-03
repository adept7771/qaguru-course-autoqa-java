package helpers.additionalClasses;

public enum WebVideoStorage {
    localStorage("http://localhost:4444/video/"),
    qaGuruStorage("https://selenoid.autotests.cloud/video/");

    public String val;
    WebVideoStorage(String val) {
        this.val = val;
    }
}
