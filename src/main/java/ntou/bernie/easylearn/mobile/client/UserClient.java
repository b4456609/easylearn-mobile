package ntou.bernie.easylearn.mobile.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by berniefan on 2016/3/30.
 */
public class UserClient {
    private final ObjectMapper objectMapper;
    private final Client client;
    private final String hostname;

    public UserClient(Client client, String hostname) {
        this.client = client;
        this.hostname = hostname;
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }
}
