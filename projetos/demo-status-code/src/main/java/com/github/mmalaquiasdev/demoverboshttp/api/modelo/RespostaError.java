package com.github.mmalaquiasdev.demoverboshttp.api.modelo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RespostaError {
    private Integer status;
    private String msg;
    private String doc;
}
