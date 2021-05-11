package com.prime.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class LoginDTO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String email, senha;
}
