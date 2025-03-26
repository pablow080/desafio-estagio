package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(unique = true, nullable = false)
    @NotNull(message = "CPF ou CNPJ é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF ou CNPJ inválido. Por favor, insira um CPF (11 dígitos) ou CNPJ (14 dígitos).")
    private String cpfCnpj;

    @NotNull(message = "Nome é obrigatório para Pessoa Física")
    @Size(min = 3, max = 100)
    private String nome;

    private String rg;
    private LocalDate dataNascimento;

    private String razaoSocial;
    private String inscricaoEstadual;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Email(message = "E-mail inválido")
    @NotNull(message = "E-mail é obrigatório")
    private String email;

    private boolean ativo = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.dataCriacao = LocalDate.now(); // Setando dataCriacao para todos os tipos de cliente
        validarCampos(); // Chamando a validação
    }

    private void validarCampos() {
        if (this.tipoPessoa == TipoPessoa.FISICA) {
            if (this.rg == null || this.dataNascimento == null) {
                throw new IllegalArgumentException("RG e Data de Nascimento são obrigatórios para Pessoa Física");
            }
        } else if (this.tipoPessoa == TipoPessoa.JURIDICA) {
            if (this.razaoSocial == null || this.inscricaoEstadual == null) {
                throw new IllegalArgumentException("Razão Social e Inscrição Estadual são obrigatórios para Pessoa Jurídica");
            }
        }
    }
}
