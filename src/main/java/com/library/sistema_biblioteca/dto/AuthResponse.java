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
    private String username;
    private String nome;
    private String email;
    private String role;

    public AuthResponse(String token, Long id, String username, String nome, String email, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.role = role;
    }
}
