package com.github.mmalaquiasdev.demoverboshttp.dominio;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("ID único criado autamaticamente pelo banco dados")
    @Id @GeneratedValue
    Long id;
    @ApiModelProperty(value = "Nome do usuario", required = true)
    @NotNull @NotBlank
    String nome;
    @ApiModelProperty(value = "Senha do úsuario", required = true)
    @NotNull @NotBlank
    String senha;
    @ApiModelProperty(value = "Email do usuario", required = true)
    @NotNull @NotBlank
    String email;
}
