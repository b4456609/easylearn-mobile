/**
 *
 */
package ntou.bernie.easylearn;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import ntou.bernie.easylearn.mobile.client.PackClient;
import ntou.bernie.easylearn.mobile.client.UserClient;
import ntou.bernie.easylearn.mobile.resource.MobileResource;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import java.util.EnumSet;

/**
 * @author bernie
 */
public class EasylearnMobileAPP extends Application<EasylearnAPPConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EasylearnMobileAPP.class);

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new EasylearnMobileAPP().run(args);

    }

    @Override
    public void run(EasylearnAPPConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getAppName());
        environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        
        final Client client = new JerseyClientBuilder().build();
        final Client client1 = new JerseyClientBuilder().build();

        PackClient packClient = new PackClient(client, configuration.getPackServiceHost());
        UserClient userClient = new UserClient(client1, configuration.getUserServiceHost());

       MobileResource mobileResource = new MobileResource(packClient, userClient);
        environment.jersey().register(mobileResource);
    }

}
