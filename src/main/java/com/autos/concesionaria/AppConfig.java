package com.autos.concesionaria;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Value("${profile}")
    private String profile;

    @Bean
    public String logProfileToUse() {
        LOGGER.info("Application profile active -> " + profile);
        return profile;
    }
}
