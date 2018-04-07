package com.github.mmalaquiasdev.demoverboshttp.servico;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.dominio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio repositorio;

    public Iterable<Usuario> pesquisar(){
        return repositorio.findAll();
    }

    public Usuario pesquisarPorId(Long id) {
        verificarUsuario(id);
        return repositorio
                .findById(id)
                .get();
    }

    public Usuario salvar(Usuario novoUsuario) {
        Optional<Usuario> usuario = repositorio.findByEmail(novoUsuario.getEmail());
        if(usuario.isPresent()) throw new IllegalArgumentException("Ja existe um usuario com esse endereco de email");
        return repositorio.save(novoUsuario);
    }

    public Usuario atualizar(Usuario usuario) {
        verificarUsuario(usuario.getId());
        return repositorio.save(usuario);
    }

    public void deletar(@PathVariable Long id) {
        verificarUsuario(id);
        repositorio.deleteById(id);
    }

    protected void verificarUsuario(Long id) {
        Optional<Usuario> usuario = repositorio.findById(id);
        if(!usuario.isPresent()) throw new ResourceNotFoundException("usuario nao existe");
    }
}
