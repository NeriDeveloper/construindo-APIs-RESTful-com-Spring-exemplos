package com.github.mmalaquiasdev.demoverboshttp.dominio;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Builder
@Entity
public class Usuario {

    @Setter(value = AccessLevel.PRIVATE)
    @Id @GeneratedValue
    Long id;
    String nome;
    String senha;
    String email;
}
