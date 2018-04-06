package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.dominio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

    @Autowired
    private UsuarioRepositorio repositorio;

    @GetMapping
    public Iterable<Usuario> pesquisar(){
       return repositorio.findAll();
    }

    @GetMapping("{id}")
    public Usuario pesquisarPorId(@PathVariable Long id) {
        Usuario usuarioVazio = Usuario.builder().build();
        return repositorio
                .findById(id)
                .orElse(usuarioVazio);
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return repositorio.save(usuario);
    }

    @PutMapping("{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return repositorio.save(usuario);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
