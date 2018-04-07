package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.dominio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/usuarios")
class UsuarioApi {

    @Autowired
    private UsuarioRepositorio repositorio;

    @PostConstruct
    private void criarMassaDados() {
        Usuario malaquias = Usuario.builder()
                .nome("Malaquias")
                .senha("123456")
                .email("mateusmalaquiasdev@outlook.com")
                .build();

        Usuario java = Usuario.builder()
                .nome("Java")
                .senha("8a1248")
                .email("java@t.com")
                .build();

        salvar(malaquias);
        salvar(java);
    }

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
        usuario.setId(id);
        return repositorio.save(usuario);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
