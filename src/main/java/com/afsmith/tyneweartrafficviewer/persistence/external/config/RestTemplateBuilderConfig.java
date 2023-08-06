package com.afsmith.tyneweartrafficviewer.persistence.external.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * Configure the default RestTemplate to connect with the Open Data Service. This
 * requires that an account has been created with the data service. The username
 * and password for that account should be set as environment variables.
 */
@Configuration
public class RestTemplateBuilderConfig {
    // The base URL of the Open Data Service.
    @Value("${utmc-open-data-service.rest.template.base-url}")
    private String baseUrl;

    // A valid username for connecting to the Open Data Service.
    @Value("${utmc-open-data-service.rest.username}")
    private String username;

    // A valid password for connecting to the Open Data Service.
    @Value("${utmc-open-data-service.rest.password}")
    private String password;

    /**
     * Configure the RestTemplate builder to use the appropriate HTTP basic headers
     * while using the sensible defaults supplied by the configurer for other
     * settings.
     * @param configurer The configurer providing sensible defaults.
     * @return A customised RestTemplateBuilder.
     */
    @Bean
    public RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
        return configurer.configure(new RestTemplateBuilder())
                         .basicAuthentication(username, password)
                         .uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
    }
}
