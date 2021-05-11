package com.prime.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StandardError {
    public static final long serialVersionUID = 1L;

    private Long timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(Long timeStamp, Integer status, String error, String message, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
