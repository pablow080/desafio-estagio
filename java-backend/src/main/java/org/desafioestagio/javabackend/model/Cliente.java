package org.desafioestagio.javabackend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

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
    private LocalDate dataNascimento;

    private String razaoSocial;
    private String inscricaoEstadual;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao = LocalDate.now();  // Já inicializado diretamente

    @Email(message = "E-mail inválido")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();  // Evita null pointer exception

    // Construtores
    public Cliente() {}

    public Cliente(TipoPessoa tipoPessoa, String cpfCnpj, String nome, String email) {
        this.tipoPessoa = tipoPessoa;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = LocalDate.now();
        this.ativo = true;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoPessoa getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(TipoPessoa tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }

    public LocalDate getDataCriacao() { return dataCriacao; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.setCliente(this);
    }

    public void removeEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
        endereco.setCliente(null);
    }
}
