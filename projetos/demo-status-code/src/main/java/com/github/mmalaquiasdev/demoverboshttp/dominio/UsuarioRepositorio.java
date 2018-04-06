package com.github.mmalaquiasdev.demoverboshttp.dominio;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
