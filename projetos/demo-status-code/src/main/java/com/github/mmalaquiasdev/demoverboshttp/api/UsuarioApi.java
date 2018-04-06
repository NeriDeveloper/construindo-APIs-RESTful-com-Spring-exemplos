package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

    @Autowired
    private UsuarioServico servico;

    @GetMapping
    public Iterable<Usuario> pesquisar(){
       return servico.pesquisar();
    }

    @GetMapping("{id}")
    public Usuario pesquisarPorId(@PathVariable Long id) {
        return servico.pesquisarPorId(id);
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return servico.salvar(usuario);
    }

    @PutMapping("{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return servico.atualizar(usuario);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        servico.deletar(id);
    }
}
