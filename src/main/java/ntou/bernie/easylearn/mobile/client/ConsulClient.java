package ntou.bernie.easylearn.mobile.client;

import com.google.common.base.Optional;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;

/**
 * Created by bernie on 2016/5/20.
 */
public class ConsulClient {
    private String host;

    public String getHost() {
        return host;
    }

    public void getServiceHost(String hostname){
        Consul consul = Consul.builder().withUrl("http://140.121.101.164:8500").build();
        KeyValueClient kvClient = consul.keyValueClient();
        kvClient.putValue("testHost", "140.121.101.162");
        Optional<String> testHost = kvClient.getValueAsString(hostname);
        if(testHost.isPresent())
            host = testHost.get();
        else
            host = "140.121.101.164";
    }
}
