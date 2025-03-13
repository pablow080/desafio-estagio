package org.desafioestagio.javabackend;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    private String bairro;
    private String telefone;
    private String cidade;
    private String estado;

    private boolean enderecoPrincipal = false;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}