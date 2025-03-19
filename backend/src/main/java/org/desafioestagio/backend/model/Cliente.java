package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoPessoa; // Fisica ou Juridica

    @Column(unique = true, nullable = false)
    private String cpfCnpj;
    private String nome;
    private String rg;
    private String dataNascimento;
    private String razaoSocial;
    private String inscricaoEstadual;
    private String dataCriacao;
    private String email;
    private boolean ativo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getInscricaoEstadual() { return inscricaoEstadual; }
    public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }
    public String getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(String dataCriacao) { this.dataCriacao = dataCriacao; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
}
