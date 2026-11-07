package br.edu.ifsp.prw3.prw3_2026_1_api.usuario;

import jakarta.validation.constraints.NotBlank;

// DTO: login e senha que chegam no corpo do POST /login
public record DadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        String senha) {

}
