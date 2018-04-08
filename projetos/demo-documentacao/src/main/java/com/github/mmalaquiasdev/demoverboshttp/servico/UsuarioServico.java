package com.github.mmalaquiasdev.demoverboshttp.servico;

import com.github.mmalaquiasdev.demoverboshttp.dominio.Usuario;
import com.github.mmalaquiasdev.demoverboshttp.dominio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio repositorio;

    public Iterable<Usuario> pesquisar(){
        return repositorio.findAll();
    }

    public Iterable<Usuario> pesquisar(String ordenacao, Sort.Direction direcao){
        return repositorio.findAll(new Sort(direcao, ordenacao));
    }

    public Page<Usuario> pesquisar(Pageable pageable){
        return repositorio.findAll(pageable);
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

    public void carregarMassaDados() {
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

        Usuario leandro = Usuario.builder()
                .nome("Leandro Augusto Almada")
                .senha("KGlkSHbLDi")
                .email("leandroaugustoalmada@scalioni.com.br")
                .build();

        Usuario martin = Usuario.builder()
                .nome("Martin Bruno Silva")
                .senha("o19XYyPk04")
                .email("martin_silva@scalioni.com.br")
                .build();

        Usuario giovana = Usuario.builder()
                .nome("Giovana Bianca")
                .senha("o1412XYyPk04")
                .email("gio@scalioni.com.br")
                .build();

        Usuario gabrielly = Usuario.builder()
                .nome("Gabrielly Josefa")
                .senha("olcasknie21y")
                .email("g_josefa@scalioni.com.br")
                .build();

        Usuario mateus = Usuario.builder()
                .nome("Mateus Felipe Alves")
                .senha("malaves121")
                .email("mateus_alves@scalioni.com.br")
                .build();

        Usuario sabrina = Usuario.builder()
                .nome("Sabrina Sara")
                .senha("sara19u42u1")
                .email("sabri@scalioni.com.br")
                .build();

        Usuario theo = Usuario.builder()
                .nome("Theo Cauê Moraes")
                .senha("6CsbjKolqF")
                .email("ttheocauemoraes@infortec.com.br")
                .build();

        Usuario raquel = Usuario.builder()
                .nome("Raquel Analu Isabela")
                .senha("6CsbjKolqF")
                .email("analu@scalioni.com.br")
                .build();

        Usuario fernanda = Usuario.builder()
                .nome("Fernanda Josefa Porto")
                .senha("zaox7320fS")
                .email("fernanda@g.com.br")
                .build();

        Usuario vinicius = Usuario.builder()
                .nome("Vinicius Julio Porto")
                .senha("8a1248")
                .email("vini@t.com")
                .build();

        Usuario eliane = Usuario.builder()
                .nome("Eliane Francisca Marlene ")
                .senha("KGlkSHbLDi")
                .email("elianemarlene@scalioni.com.br")
                .build();

        Usuario alicia = Usuario.builder()
                .nome("Alícia Sueli Giovana da Cunha")
                .senha("o19XYyPk04")
                .email("alicias@willianfernandes.com.br")
                .build();

        Usuario thiago = Usuario.builder()
                .nome("Thiago Gael Kaique da Cunha")
                .senha("o1412XYyPk04")
                .email("thiagocunha@scalioni.com.br")
                .build();

        Usuario clarice = Usuario.builder()
                .nome("Clarice Kamilly Luciana")
                .senha("olcasknie21y")
                .email("clarice_luciana@scalioni.com.br")
                .build();

        Usuario raul = Usuario.builder()
                .nome("Raul Danilo Isaac Caldeira")
                .senha("P36L2Ke0Gy")
                .email("rauldaniloisaaccaldeira-84@shimu.com.br")
                .build();

        Usuario emilly = Usuario.builder()
                .nome("Emilly Lorena Bianca ")
                .senha("P36L2Ke0Gy")
                .email("emillybianca@scalioni.com.br")
                .build();

        Usuario ruan = Usuario.builder()
                .nome("Ruan Augusto Pedro Caldeira")
                .senha("P36L2Ke0Gy")
                .email("rcaldeira@infortec.com.br")
                .build();

        Usuario caleb = Usuario.builder()
                .nome("Caleb Benedito Souza")
                .senha("FQIrxuTpmL")
                .email("calebbeneditosouza-99@desari.com.br")
                .build();

        repositorio.saveAll(Arrays.asList(malaquias, java, leandro, martin, giovana, gabrielly,
                mateus, sabrina, theo, raquel, fernanda, vinicius, eliane, alicia, thiago,
                clarice, raul, emilly, ruan, caleb)
        );
    }
}
