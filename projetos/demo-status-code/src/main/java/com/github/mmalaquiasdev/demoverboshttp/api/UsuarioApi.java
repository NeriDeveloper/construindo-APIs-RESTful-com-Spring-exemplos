package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Documento;
import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.servico.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

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

    @Autowired
    private UsuarioServico servico;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> pesquisar(){
       return servico.pesquisar();
    }

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

    //    @PostMapping
//    public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
//        HttpHeaders headers = new HttpHeaders();
//        Usuario usuarioNovo = servico.salvar(usuario);
//        headers.setLocation(URI.create("http://localhost:8080/usuarios/"+usuarioNovo.getId()));
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

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
