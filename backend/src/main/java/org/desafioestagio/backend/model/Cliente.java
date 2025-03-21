package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(unique = true, nullable = false)
    @NotNull
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF ou CNPJ inválido")
    private String cpfCnpj;

    @NotNull
    @Size(min = 3, max = 100)
    private String nome;
    private String rg;
    private LocalDate dataNascimento;
    private String razaoSocial;
    private String inscricaoEstadual;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Email
    private String email;

    private boolean ativo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Endereco> enderecos;

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDate.now();
    }
}
