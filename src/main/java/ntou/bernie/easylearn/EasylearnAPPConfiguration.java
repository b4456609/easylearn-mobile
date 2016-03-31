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
    private String appName;
    @Valid
    @NotNull
    private String packServiceHost;
    @Valid
    @NotNull
    private String userServiceHost;


    /**
     * @return the appName
     */
    @JsonProperty
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    @JsonProperty
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @JsonProperty
	public String getPackServiceHost() {
		return packServiceHost;
	}

    @JsonProperty
	public void setPackServiceHost(String packServiceHost) {
		this.packServiceHost = packServiceHost;
	}

    @JsonProperty
	public String getUserServiceHost() {
		return userServiceHost;
	}

    @JsonProperty
	public void setUserServiceHost(String userServiceHost) {
		this.userServiceHost = userServiceHost;
	}

}
