package br.edu.ifsp.prw3.prw3_2026_1_api.controller;

import br.edu.ifsp.prw3.prw3_2026_1_api.usuario.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // quem dispara a autenticação é o AuthenticationManager do Spring
    // (ele usa o AutenticacaoService e o BCrypt por baixo)
    private final AuthenticationManager manager;

    public AutenticacaoController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<Void> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {

        // junta login e senha num objeto que o Spring entende e manda autenticar
        // (se a senha estiver errada, o próprio Spring lança exceção e devolve erro)
        var dadosLogin = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        manager.authenticate(dadosLogin);

        return ResponseEntity.ok().build();
    }
}
