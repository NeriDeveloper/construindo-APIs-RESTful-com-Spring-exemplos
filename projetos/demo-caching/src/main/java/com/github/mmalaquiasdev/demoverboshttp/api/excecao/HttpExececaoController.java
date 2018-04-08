package com.github.mmalaquiasdev.demoverboshttp.api.excecao;

import com.github.mmalaquiasdev.demoverboshttp.api.modelo.RespostaError;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class HttpExececaoController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaError> requisicaoComCamposInvalidos(MethodArgumentNotValidException ex){
        StringBuilder msg = new StringBuilder();

        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .forEach(fieldError -> msg.append(fieldError.getField())
                        .append(" ")
                        .append(fieldError.getDefaultMessage())
                        .append(", "));

        RespostaError error = RespostaError
                .builder()
                .status(BAD_REQUEST.value())
                .msg(msg.toString())
                .doc("https://api.fcamara.com.br/erros/")
                .build();

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RespostaError> requisicaoComCamposInvalidos(IllegalArgumentException ex){
        RespostaError error = RespostaError
                .builder()
                .status(BAD_REQUEST.value())
                .msg(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RespostaError> erroRecursoNaoEncontrado(ResourceNotFoundException ex){
        RespostaError error = RespostaError
                .builder()
                .status(NOT_FOUND.value())
                .msg(ex.getMessage())
                .doc("https://api.fcamara.com.br/erros/")
                .build();

        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaError> erroInterno(){
        RespostaError error = RespostaError
                .builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .msg("Ops! Aconteceu um erro um interno")
                .build();

        return new ResponseEntity<>(error, INTERNAL_SERVER_ERROR);
    }
}
