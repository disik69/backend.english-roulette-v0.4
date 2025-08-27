package ua.pp.disik.englishroulette.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    private static final String SECURITY_SCHEME_KEY = "apiKey";
    private static final List<String> PUBLIC_PATHS = List.of(
            "/signin"
    );

    // Unused configuration are an example.
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("English Roulette Backend")
//                                .description("Description")
                                .version("0.4")
//                                .license(new License().name("License version").url("License url"))
                )
//                .externalDocs(
//                        new ExternalDocumentation()
//                                .description("External documentation description")
//                                .url("External documentation url")
//                )
                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_KEY,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name(SecurityConfiguration.SECURITY_HEADER_NAME)
                        )
//                        .addParameters(
//                                "token",
//                                new QueryParameter()
//                                        .schema(new StringSchema())
//                                        .name("token")
//                                        .required(false)
//                        )

                )
//                .security(
//                        List.of(
//                                new SecurityRequirement().addList(SECURITY_SCHEME_KEY)
//                        )
//                )
                ;
    }

    @Bean
    OpenApiCustomizer openApiCustomizer() {
        return openApi -> openApi.getPaths().entrySet().stream()
                .filter(entry -> ! PUBLIC_PATHS.contains(entry.getKey()))
                .map(entry -> entry.getValue())
                .flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_KEY));

                    // The parameter setting is an example.
//                    operation.addParametersItem(
//                        new Parameter().$ref("#/components/parameters/token")
//                    );
                });
    }
}
