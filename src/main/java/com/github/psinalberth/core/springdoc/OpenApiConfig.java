package com.github.psinalberth.core.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final var contact =  new Contact()
                .name("Inalberth Pinheiro Santos")
                .email("inalberth07@gmail.com");

        final var info = new Info()
                .title("inventario-api")
                .description("API responsável pelo gerenciamento dos inventários das lojas")
                .version("0.0.1")
                .contact(contact);

        return new OpenAPI()
                .info(info);
    }
}
