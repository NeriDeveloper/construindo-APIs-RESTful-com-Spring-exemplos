package com.github.mmalaquiasdev.demoverboshttp.api;

import com.github.mmalaquiasdev.demoverboshttp.api.modelo.RespostaError;
import com.github.mmalaquiasdev.demoverboshttp.dominio.Documento;
import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.servico.UsuarioServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioApi {

    @PostConstruct
    private void criarMassaDados() { servico.carregarMassaDados(); }

    @Autowired
    private UsuarioServico servico;

    @ApiOperation(value = "Pesquisa uma lista CACHEADA com todos os usuarios disponíveis", response = Usuario.class)
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Cacheable("usuarios")
    public Iterable<Usuario> pesquisar() {
        return servico.pesquisar();
    }

    @ApiOperation(value = "Pesquisa UM usuário", response = Usuario.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Usuário não encontrado", response = RespostaError.class)
            })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario pesquisarPorId(@PathVariable Long id) {
        return servico.pesquisarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "usuarios", allEntries = true)
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
