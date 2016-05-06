package ntou.bernie.easylearn.mobile.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by berniefan on 2016/3/30.
 */
public class UserClient {
    private final Client client;
    private final String hostname;

    public UserClient(Client client, String hostname) {
        this.client = client;
        this.hostname = hostname;
    }

    public Response syncUser(String json) {
        return client.target(hostname).path("user/sync").request().accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }
}
