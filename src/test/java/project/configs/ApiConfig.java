package project.configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:api.properties",
        "classpath:sample-token.properties"
})

public interface ApiConfig extends Config {

    @DefaultValue("defaultUrl")
    @Key("base.url")
    String baseUrl();

    @Key("token")
    String token();
}

