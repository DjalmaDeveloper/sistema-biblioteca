package com.library.sistema_biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String usuario;
    private String nome;
    private String email;
    private String perfil;

    public AuthResponse(String token, Long id, String usuario, String nome, String email, String perfil) {
        this.token = token;
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }
}
