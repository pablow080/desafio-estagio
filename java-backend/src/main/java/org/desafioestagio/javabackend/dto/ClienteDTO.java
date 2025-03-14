package org.desafioestagio.javabackend.dto;

import org.desafioestagio.javabackend.model.TipoPessoa;

import java.time.LocalDate;

public class ClienteDTO {

    private Long id;
    private TipoPessoa tipoPessoa;
    private String nome;
    private String email;
    private LocalDate dataCriacao;

    public ClienteDTO(Long id, String nome, String email, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }
}
