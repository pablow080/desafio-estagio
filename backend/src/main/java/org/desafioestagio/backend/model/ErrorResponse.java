package org.desafioestagio.backend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    // Getters e Setters
    private String message;
    private String details;
    private int statusCode;

    // Construtor
    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.statusCode = 400;
    }

}
