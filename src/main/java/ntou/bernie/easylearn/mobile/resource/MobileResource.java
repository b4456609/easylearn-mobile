package ntou.bernie.easylearn.mobile.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ntou.bernie.easylearn.mobile.client.PackClient;
import ntou.bernie.easylearn.mobile.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by bernie on 2016/2/19.
 */
@Path("/sync")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MobileResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MobileResource.class);
    private final PackClient packClient;
    private final UserClient userClient;
    
    public MobileResource(PackClient packClient, UserClient userClient) {
		super();
		this.packClient = packClient;
		this.userClient = userClient;
	}

	@POST
    public Response sync(String syncJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.debug("sync Json content " + syncJson);

            //sync user
            Response userResp = userClient.syncUser(syncJson);
            LOGGER.debug("Finish user sync " + userResp.toString());

            //sync pack
            JsonNode jsonNode = objectMapper.readTree(syncJson);
            String packsjson = jsonNode.get("pack").toString();
            LOGGER.debug(packsjson);
            Response packResp = packClient.syncPacks(packsjson);
            LOGGER.debug("Finish pack sync " + packResp.toString());

            //if sync not ok return server error
            if (userResp.getStatus() != 200 || packResp.getStatus() != 200)
                return Response.serverError().build();

            //get response user json node
            String userJson = userResp.readEntity(String.class);
            LOGGER.debug(userJson);

            ObjectNode respNode = (ObjectNode) objectMapper.readTree(userJson);
            String userId = respNode.get("user").get("id").textValue();
            Response packsResp = packClient.getUserPacks(userId);
            JsonNode packsNode = objectMapper.readTree(packsResp.readEntity(String.class));

            respNode.set("pack", packsNode);

            LOGGER.debug(respNode.toString());

            return Response.ok(respNode.toString()).build();

        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }

    }
}
