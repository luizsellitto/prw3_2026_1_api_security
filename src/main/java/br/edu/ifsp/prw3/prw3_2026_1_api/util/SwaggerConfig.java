package br.edu.ifsp.prw3.prw3_2026_1_api.util;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

// Só pro Swagger: cria o botão "Authorize", onde a gente cola o token do /login.
// Não muda nada na segurança da API.
@Configuration
@SecurityScheme(name = "bearer-key", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SwaggerConfig {
}
