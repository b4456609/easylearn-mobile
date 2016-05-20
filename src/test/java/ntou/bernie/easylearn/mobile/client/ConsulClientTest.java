package ntou.bernie.easylearn.mobile.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bernie on 2016/5/20.
 */
public class ConsulClientTest {
    @Test
    public void getServiceHost() throws Exception {
        ConsulClient consulClient = new ConsulClient();
        consulClient.getServiceHost();
        assertEquals("140.121.101.162", consulClient.getHost());
    }

}