package com.example.notes_app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up JWT (JSON Web Token) related properties and beans.
 * This may include secret keys, token expiration settings, and signing algorithms.
 */
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtConfiguration {
    private String secret;
}
