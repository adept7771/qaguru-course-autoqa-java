package project.configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:${env}.properties"
})

public interface SelenideConfig extends Config {

    @DefaultValue("87")
    @Key("browser.version")
    String browserVersion();

    @DefaultValue("chrome")
    @Key("browser.name")
    String browserName();

    @DefaultValue("false")
    @Key("selenide.remote")
    boolean selenideRemote();

    @DefaultValue("https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/")
    @Key("webDriver.remote.url")
    String webDriverRemoteURL();
}
