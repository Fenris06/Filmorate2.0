package ru.fenris06.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Filmorate 2.0 Api",
                description = "Loyalty System", version = "1.0.0",
                contact = @Contact(
                        name = "Karpushenko Artem",
                        email = "karpushenkoartem@gmail.com",
                        url = "https://github.com/Fenris06"
                )
        )
)
public class OpenApiConfig {
}
