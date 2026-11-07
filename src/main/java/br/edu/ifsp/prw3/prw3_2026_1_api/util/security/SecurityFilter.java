package br.edu.ifsp.prw3.prw3_2026_1_api.util.security;

import br.edu.ifsp.prw3.prw3_2026_1_api.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private PW3TokenService pw3tokenservice;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");

        // O /login chega sem token, então só tenta validar quando ele foi enviado
        if (authorizationHeader != null) {
            authorizationHeader = authorizationHeader.replace("Bearer ", "");

            var subject = pw3tokenservice.getSubject(authorizationHeader);
            var usuario = repository.findByLogin(subject);

            // O JWT já foi validado
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities());

            // Informa ao Spring quem está autenticado nesta requisição
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continua o processamento, inclusive quando a requisição não trouxe token
        filterChain.doFilter(request, response);
    }
}
