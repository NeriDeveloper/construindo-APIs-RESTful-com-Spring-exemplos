package com.github.mmalaquiasdev.demoverboshttp.dominio;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id @GeneratedValue
    Long id;
    @NotNull @NotBlank
    String nome;
    @NotNull @NotBlank
    String senha;
    @NotNull @NotBlank
    String email;
}
