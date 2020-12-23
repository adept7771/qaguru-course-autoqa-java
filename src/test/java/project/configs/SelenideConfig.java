package project.configs;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:${env}.properties"})
public interface SelenideConfig extends Config {

  @DefaultValue("87")
  @Key("browser.version")
  String browserVersion();

  @DefaultValue("firefox")
  @Key("browserName")
  String browserName();

  @DefaultValue("false")
  @Key("useSelenoid")
  boolean useSelenoid();

  @Key("baseUrl")
  String baseUrl();

  @DefaultValue("https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/")
  @Key("webDriver.remote.url")
  String webDriverRemoteURL();
}
