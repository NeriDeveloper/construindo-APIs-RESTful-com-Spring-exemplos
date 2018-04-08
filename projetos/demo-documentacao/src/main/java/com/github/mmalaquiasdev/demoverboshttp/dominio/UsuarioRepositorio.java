package com.github.mmalaquiasdev.demoverboshttp.dominio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>, PagingAndSortingRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNome(String nome);
    Optional<List<Usuario>> findByNomeStartingWith(String nome);
    Optional<List<Usuario>> findByNomeAndEmail(String nome, String email);
}
