package br.edu.ifsp.prw3.prw3_2026_1_api.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// O Spring Security chama essa classe na hora do login
// pra buscar o usuário no banco e depois conferir a senha.
@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository repository;

    public AutenticacaoService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = repository.findByLogin(username);

        // se não achar, avisa o Spring (ele transforma isso em login inválido)
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return usuario;
    }
}
