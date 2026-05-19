package com.biblioteca.gerenciamento.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String mensagem;
    private String path;


    public ErrorResponse(int status, String error, String mensagem, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.mensagem = mensagem;
        this.path = path;
    }
}