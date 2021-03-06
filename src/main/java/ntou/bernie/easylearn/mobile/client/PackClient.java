package ntou.bernie.easylearn.mobile.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by berniefan on 2016/3/30.
 */
public class PackClient {
    private final Client client;
    private final String hostname;

    public PackClient(Client client, String hostname) {
        this.client = client;
        this.hostname = hostname;
    }

    public Response syncPacks(String json) {
        return client.target(hostname).path("pack/sync").request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
    }

    public Response getUserPacks(String userId) {
        return client.target(hostname).path("pack/user/").path(userId).request().get();
    }

}
