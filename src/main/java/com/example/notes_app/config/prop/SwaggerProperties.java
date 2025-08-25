package com.example.notes_app.config.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.swagger")
@Data
public class SwaggerProperties {
    private String title;
    private String desc;
    private String version;
}
