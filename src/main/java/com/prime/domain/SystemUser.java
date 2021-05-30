package com.prime.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prime.enums.Perfil;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SystemUser implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SystemUser(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String hashSenha, imagemNome;

    @JsonIgnore
    @OneToMany(mappedBy = "dono")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dono")
    private List<Curtida> curtidas = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "dono")
    private List<Follow> seguindo = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "seguindo")
    private List<Follow> seguidores = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private final Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getId());
    }

    public Integer getNumeroSeguidores() {
        return seguidores.size();
    }

    public Integer getNumeroSeguindo() {
        return seguindo.size();
    }

    public String getAvatar() {
        return imagemNome == null ? null : ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString() + "/perfil/imagem" + imagemNome;
    }
}
