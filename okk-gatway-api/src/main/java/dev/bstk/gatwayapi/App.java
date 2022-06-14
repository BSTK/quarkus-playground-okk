package dev.bstk.gatwayapi;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
    info = @Info(
        title = "Okk Gateway Api Service",
        description = "Microserviço para obter dados das api's apara seus clientes",
        version = "1.0.0",
        contact = @Contact(
            name = "bstk",
            url = "https://github.com/bstk",
            email = "bsilva.se@gmail.com"
        )
    )
)
@ApplicationPath("/okk-gateway")
public class App extends Application { }
