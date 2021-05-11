package com.prime.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String legenda;

    @JsonIgnore
    @NotEmpty
    private String imagemNome;


    @JsonFormat(pattern = "dd/MM/yyy HH:mm")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private SystemUser dono;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Curtida> curtidas = new ArrayList<>();

    public int getCurtidas() {
        return curtidas.size();
    }

    @PrePersist
    public void setData() {
        data = new Date();
    }

    public String getImagemURL() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/feed/imagem/" + imagemNome;
    }
}
