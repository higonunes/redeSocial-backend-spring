package com.prime.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prime.domain.Curtida;
import com.prime.domain.Post;
import com.prime.domain.SystemUser;
import com.prime.security.UserSS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {

    private Long id;

    @NotEmpty
    @Column(length = 1000)
    private String legenda;

    @NotEmpty
    private String imagem;

    @JsonFormat(pattern = "dd/MM/yyy HH:mm")
    private Date data;

    private SystemUser dono;

    @JsonIgnore
    private List<Curtida> curtidas = new ArrayList<>();

    @JsonIgnore
    private UserSS userSS;

    public PostDTO(Post post, UserSS userSS) {
        this.id = post.getId();
        this.legenda = post.getLegenda();
        this.imagem = post.getImagem();
        this.data = post.getData();
        this.dono = post.getDono();
        this.curtidas = post.getCurtidas();
        this.userSS = userSS;
    }

    public String getImagem() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/feed/imagem/" + imagem;
    }
    public int getNumCurtidas() {
        return curtidas.size();
    }

    public boolean getCurtiuPost() {
        return curtidas.stream().map(x -> x.getDono().getId() == userSS.getId()).reduce(false, (a, b) -> a || b);
    }
}
