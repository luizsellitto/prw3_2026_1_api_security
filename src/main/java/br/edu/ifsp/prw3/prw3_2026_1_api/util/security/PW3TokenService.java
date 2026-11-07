package br.edu.ifsp.prw3.prw3_2026_1_api.util.security;

import br.edu.ifsp.prw3.prw3_2026_1_api.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PW3TokenService {

    // Lê a chave definida no application.properties.
    @Value("${pw3.senha.principal.geracao.token}")
    private String secret;

    public String gerarToken(Usuario usuario) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);

            // O subject guarda o login para identificarmos o usuário depois
            String token = JWT.create()
                    .withIssuer("DISCIPLINA PW3")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);

            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);

            JWTVerifier jwtv = JWT.require(algoritmo)
                    .withIssuer("DISCIPLINA PW3")
                    .build();

            // Só devolve o login se a assinatura, o emissor e a validade conferirem
            return jwtv.verify(tokenJWT).getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        // Duas horas de validade
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
