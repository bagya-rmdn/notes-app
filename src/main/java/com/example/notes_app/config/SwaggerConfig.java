package com.example.notes_app.config;


import com.example.notes_app.config.prop.SwaggerProperties;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SwaggerConfig implements OpenApiCustomizer {
    private final SwaggerProperties properties;
    private static final String BEARER_SECURITY_SCHEMA_NAME = "bearerAuth";

    @Override
    public void customise(OpenAPI openApi) {
        openApi.info(new Info().title(properties.getTitle()).description(properties.getDesc())
                        .version(properties.getVersion()).license(
                                new License().name("Apache").url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_SECURITY_SCHEMA_NAME));
    }
}
