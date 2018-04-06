package com.github.mmalaquiasdev.demoverboshttp.dominio;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@Entity
public class Usuario {

    @Setter(value = AccessLevel.PRIVATE)
    @Id @GeneratedValue
    Long id;
    @NotNull @NotBlank
    String nome;
    @NotNull @NotBlank
    String senha;
    @NotNull @NotBlank
    String email;
}
