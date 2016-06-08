package ntou.bernie.easylearn.mobile.resource;

import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import ntou.bernie.easylearn.mobile.client.UserClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.dropwizard.testing.FixtureHelpers.fixture;

/**
 * Created by bernie on 2016/5/6.
 */
public class ComsumerTest extends ConsumerPactTest {

    private String sendBody = fixture("user/user.json");

    @Override
    protected PactFragment createFragment(PactDslWithProvider builder) {
        return builder.uponReceiving("a request for user")
                .path("/user/sync")
                .method("POST")
                .body(sendBody, "application/json")
                .willRespondWith()
                .status(200)
                .body(sendBody, "application/json").toFragment();
    }

    @Override
    protected String providerName() {
        return "easylearn-user";
    }

    @Override
    protected String consumerName() {
        return "easylearn-mobile";
    }

    @Override
    protected void runTest(String url) throws IOException {
        UserClient userClient = new UserClient(new JerseyClientBuilder().build(), url);
        Assert.assertEquals(userClient.syncUser(sendBody).readEntity(String.class), sendBody);
    }
}
