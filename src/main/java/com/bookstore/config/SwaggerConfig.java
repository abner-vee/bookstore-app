package com.bookstore.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Bookstore App",
                description = "APIs for Bookstore App",
                version = "1.0",
                contact = @Contact(
                        name = "Vivian",
                        email = "bookstoreapp@gmail.com",
                        url = "https://github.com/abner-vee"
                ),
                license = @License(
                        name = "Bookstore Application",
                        url = "https://github.com/abner-vee"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Bookstore RESTful API Documentation",
                url = "https://github.com/vivian"
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth Description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
