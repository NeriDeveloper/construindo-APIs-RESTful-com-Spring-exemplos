package com.github.mmalaquiasdev.demoverboshttp.dominio;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id @GeneratedValue
    Long id;
    String nome;
    String senha;
    String email;
}
