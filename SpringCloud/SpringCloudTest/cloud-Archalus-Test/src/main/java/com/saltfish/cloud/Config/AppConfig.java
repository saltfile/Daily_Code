package com.saltfish.cloud.Config;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.URLConfigurationSource;
import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URL;

@Configuration
public class AppConfig {
    @Bean
    public AbstractConfiguration addApplicationPropertiesSource() {
        URL configPropertyURL = null;
        try {


            configPropertyURL = (new ClassPathResource("config.properties")).getURL();

        PolledConfigurationSource source = new URLConfigurationSource(configPropertyURL);
        return new DynamicConfiguration(source, new FixedDelayPollingScheduler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
