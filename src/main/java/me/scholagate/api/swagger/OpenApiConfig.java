package me.scholagate.api.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API ScholaGate",
                version = "1.0",
                description = "API para la app Web y Móvil de ScholaGate",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Jonathan Betancor Perdomo",
                        email = "jonathanbetancorperdomo@gmail.com"
                )
        )
)
public class OpenApiConfig { }
/*

@Tag(name = "Cliente Controller", description = "Controlador de clientes")
@Operation(summary = "Obtener todos los clientes")

*/
