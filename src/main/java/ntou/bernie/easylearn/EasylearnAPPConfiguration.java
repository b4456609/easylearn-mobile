package ntou.bernie.easylearn;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author bernie Get Configuration from file
 */
public class EasylearnAPPConfiguration extends Configuration {

    @Valid
    @NotNull
    private String host;
    @Valid
    @NotNull
    private String packServicePort;
    @Valid
    @NotNull
    private String userServicePort;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public String getPackServicePort() {
        return packServicePort;
    }

    @JsonProperty
    public void setPackServicePort(String packServicePort) {
        this.packServicePort = packServicePort;
    }

    public String getUserServicePort() {
        return userServicePort;
    }

    public void setUserServicePort(String userServicePort) {
        this.userServicePort = userServicePort;
    }
}
