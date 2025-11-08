package com.library.sistema_biblioteca.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidade que representa um autor no sistema")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do autor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    @Schema(description = "Nome completo do autor", example = "Machado de Assis", required = true)
    private String nome;

    @Schema(description = "Nacionalidade do autor", example = "Brasileiro")
    private String nacionalidade;

    @Column(name = "data_nascimento")
    @Schema(description = "Data de nascimento do autor", example = "1839-06-21")
    private LocalDate dataNascimento;

    @Column(columnDefinition = "TEXT")
    @Schema(description = "Biografia do autor", example = "Joaquim Maria Machado de Assis foi um escritor brasileiro...")
    private String biografia;
}
