package com.library.sistema_biblioteca.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Requisição para criação de novo usuário")
public class UsuarioCreateRequest {

    @NotBlank(message = "Usuário é obrigatório")
    @Size(min = 3, max = 50, message = "Usuário deve ter entre 3 e 50 caracteres")
    @Schema(description = "Nome de usuário", example = "joao123", required = true)
    private String usuario;

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome completo do usuário", example = "João Silva", required = true)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description = "E-mail do usuário", example = "joao@email.com", required = true)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Schema(description = "Senha do usuário", example = "senha123", required = true)
    private String senha;

    @Schema(description = "Perfil/Role do usuário", example = "USER", allowableValues = {"USER", "ADMIN", "BIBLIOTECARIO"}, defaultValue = "USER")
    private String perfil = "USER";

    @Schema(description = "Status ativo do usuário", example = "true", defaultValue = "true")
    private Boolean ativo = true;
}

