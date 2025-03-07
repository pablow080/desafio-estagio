package com.desafio.model;

import java.util.Date;
import java.util.List;

@Entity
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo de Pessoa é obrigatório")
    private String tipoPessoa; // Física ou Jurídica

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF deve ter 11 dígitos ou CNPJ 14 dígitos")
    private String cpfCnpj;

    private String nome; // Para pessoa física
    private String rg;
    private Date dataNascimento;

    private String razaoSocial; // Para pessoa jurídica
    private String inscricaoEstadual;
    private Date dataCriacao;

    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    private boolean ativo = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;

    // Getters e Setters
    // (Implementar manualmente ou usar Lombok @Data)
}