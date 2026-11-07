package br.edu.ifsp.prw3.prw3_2026_1_api.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // busca o usuário pelo login (o Spring monta a consulta pelo nome do método)
    UserDetails findByLogin(String login);
}
