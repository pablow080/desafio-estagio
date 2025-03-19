package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente {

    // Getters e Setters automáticos (se estiver usando Lombok, isso é suficiente)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPessoa tipoPessoa;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @Pattern(regexp = "^\\d{11}$|^\\d{14}$", message = "CPF/CNPJ inválido")
    @Column(unique = true, nullable = false)
    private String cpfCnpj;

    private String nome;
    private String rg;

    private String razaoSocial;
    private String inscricaoEstadual;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean ativo = true;

    // Relacionamento OneToOne com Endereco
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    // Construtores
    public Cliente() {
    }

    public Cliente(TipoPessoa tipoPessoa, String cpfCnpj, String nome, String email) {
        this.tipoPessoa = tipoPessoa;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.email = email;
    }

    public Cliente(TipoPessoa tipoPessoa, String cpfCnpj, String nome, String rg, LocalDate dataNascimento,
                   String razaoSocial, String inscricaoEstadual, String email) {
        this.tipoPessoa = tipoPessoa;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.rg = rg;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
        this.email = email;
    }

    // Validação condicional para nome e razão social
    @AssertTrue(message = "Nome deve ser preenchido para Pessoa Física, e Razão Social para Pessoa Jurídica")
    public boolean isValid() {
        if (tipoPessoa == TipoPessoa.FISICA) {
            return nome != null && !nome.isEmpty();
        } else if (tipoPessoa == TipoPessoa.JURIDICA) {
            return razaoSocial != null && !razaoSocial.isEmpty();
        }
        return false;
    }

    // Método para garantir que a data de criação seja configurada antes da persistência
    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDate.now();
        }
    }

    public void addEndereco(Endereco endereco) {
        if (endereco != null) {
            this.endereco = endereco;
        }
    }
}
