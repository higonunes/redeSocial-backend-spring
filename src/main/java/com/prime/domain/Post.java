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
    @Column(length = 1000)
    private String legenda;

    @NotEmpty
    private String imagem;

    @JsonFormat(pattern = "dd/MM/yyy HH:mm")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "dono_id")
    private SystemUser dono;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Curtida> curtidas = new ArrayList<>();

    public Post(long l, String s, String s1, Date date, SystemUser systemUser) {
        this.id = l;
        this.legenda = s;
        this.data = date;
        this.dono = systemUser;
        this.curtidas = new ArrayList<>();
    }

    @PrePersist
    public void setData() {
        data = new Date();
    }

}
