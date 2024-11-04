package com.netcracker.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HazelcastConfig {

    @Bean
    public HazelcastInstance hazelcastInstance() {

        Config config = new Config();
        config.getNetworkConfig()
                .setPublicAddress("127.0.0.1")
                .addOutboundPortDefinition("5701-5720");
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(config);
        Map<Integer, Object> data = hzInstance.getMap("data");
        return hzInstance;
    }

    /*@Bean
    public HazelcastInstance hazelcastInstance() {

        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig
                .addAddress("127.0.0.1")
                .addOutboundPortDefinition("5701-5720");
        HazelcastInstance hzInstance = HazelcastClient.newHazelcastClient(clientConfig);
        Map<Integer, Object> data = hzInstance.getMap("data");
        return hzInstance;
    }*/

}
