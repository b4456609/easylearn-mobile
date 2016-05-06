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
    private String responseBody = fixture("user/response.json");

    @Override
    protected PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Connection", "keep-alive");
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");

        return builder.uponReceiving("a request for user")
                .path("/user/sync")
                .method("POST")
                .body(sendBody)
                .headers(headers)
                .matchHeader("Host", ".*")
                .matchHeader("User-Agent", "Jersey.*")
                .matchHeader("Content-Length", ".*")
                .willRespondWith()
                .status(200)
                .body(responseBody).toFragment();
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
        Assert.assertEquals(userClient.syncUser(sendBody).readEntity(String.class), responseBody);
    }
}
