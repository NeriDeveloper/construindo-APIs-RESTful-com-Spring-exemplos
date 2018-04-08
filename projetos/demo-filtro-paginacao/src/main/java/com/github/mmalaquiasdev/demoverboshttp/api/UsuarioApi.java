package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Documento;
import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

    @PostConstruct
    private void criarMassaDados() { servico.carregarMassaDados(); }

    @Autowired
    private UsuarioServico servico;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> pesquisar(@RequestParam(defaultValue = "id") String ordenacao,
                                       @RequestParam(defaultValue = "ASC") Sort.Direction direcao,
                                       @RequestParam(defaultValue = "") String nome,
                                       @RequestParam(defaultValue = "") String email){
       return servico.pesquisar(ordenacao, direcao);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Page<Usuario> pesquisar(Pageable pageable) {
//        return servico.pesquisar(pageable);
//    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario pesquisarPorId(@PathVariable Long id) {
        return servico.pesquisarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario) {
        return servico.salvar(usuario);
    }

    @PostMapping("/{id}/documentos")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Documento processarDados() {
        return Documento.builder().url("href:/usuarios/421524/documentos/2130040").build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return servico.atualizar(usuario);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        servico.deletar(id);
    }

    @PostMapping("erro")
    public Usuario erro(@RequestBody Usuario usuario){
        usuario.getEmail().concat("1");
        return usuario; }
}
