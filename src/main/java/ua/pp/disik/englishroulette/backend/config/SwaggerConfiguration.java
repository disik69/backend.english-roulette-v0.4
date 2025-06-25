package ua.pp.disik.englishroulette.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
  @Bean
  public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
                .info(
                        new Info()
                                .title("Title")
                                .description("Description")
                                .version("Version")
                                .license(new License().name("License version").url("License url"))
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("External documentation description")
                                .url("External documentation url")
                );
  }
}
