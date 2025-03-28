package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.desafioestagio.backend.validations.ValidacaoUtil;
import org.desafioestagio.backend.validations.Validate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    // Interfaces para validação condicional
    public interface PessoaFisicaGroup {}
    public interface PessoaJuridicaGroup {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Tipo de Pessoa é obrigatório")
    private TipoPessoa tipoPessoa;

    @Column(unique = true, nullable = false)
    @NotNull(message = "CPF/CNPJ é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "Documento inválido (11 ou 14 dígitos)")
    private String cpfCnpj;

    @NotNull(message = "Nome é obrigatório", groups = PessoaFisicaGroup.class)
    @Size(min = 3, max = 100, groups = PessoaFisicaGroup.class)
    private String nome;

    @NotNull(message = "RG é obrigatório", groups = PessoaFisicaGroup.class)
    @Pattern(regexp = "\\d{7,12}", message = "RG inválido (7 a 12 dígitos)", groups = PessoaFisicaGroup.class)
    private String rg;

    @NotNull(message = "Data de Nascimento é obrigatória", groups = PessoaFisicaGroup.class)
    @Past(message = "Data deve ser no passado", groups = PessoaFisicaGroup.class)
    private LocalDate dataNascimento;

    @NotNull(message = "Razão Social é obrigatória", groups = PessoaJuridicaGroup.class)
    @Size(min = 3, max = 150, groups = PessoaJuridicaGroup.class)
    private String razaoSocial;

    @NotNull(message = "Inscrição Estadual é obrigatória", groups = PessoaJuridicaGroup.class)
    @Pattern(regexp = "\\d{9,14}", message = "IE inválida (9 a 14 dígitos)", groups = PessoaJuridicaGroup.class)
    private String inscricaoEstadual;

    @Column(nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Email(message = "E-mail inválido")
    @NotNull(message = "E-mail é obrigatório")
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    // ========== MÉTODOS ========== //

    @PrePersist
    protected void prePersist() {
        this.dataCriacao = LocalDate.now();
        validarCampos();
        validarDocumento();
    }

    private void validarCampos() {
        if (TipoPessoa.FISICA.equals(tipoPessoa)) {
            Validate.validate(this, PessoaFisicaGroup.class);
        } else {
            Validate.validate(this, PessoaJuridicaGroup.class);
        }
    }

    private void validarDocumento() {
        if (!validaDocumento()) {
            throw new IllegalArgumentException("Documento " + cpfCnpj + " inválido para " + tipoPessoa);
        }
    }

    public String getDocumentoFormatado() {
        return tipoPessoa.formatarDocumento(cpfCnpj);
    }

    public boolean validaDocumento() {
        if (cpfCnpj == null) return false;
        String docLimpo = cpfCnpj.replaceAll("\\D", "");
        return switch (tipoPessoa) {
            case FISICA -> docLimpo.length() == 11 && ValidacaoUtil.isCpfValido(docLimpo);
            case JURIDICA -> docLimpo.length() == 14 && ValidacaoUtil.isCnpjValido(docLimpo);
        };
    }

    public void addEndereco(Endereco endereco) {
        if (endereco == null) return;
        enderecos.add(endereco);
        endereco.setCliente(this);
    }

    public void desativar() {
        this.ativo = false;
        if (this.enderecos != null) {
            this.enderecos.forEach(e -> e.setAtivo(false));
        }
    }

    // ========== CLASSE INTERNA ========== //
    public enum TipoPessoa {
        FISICA("999.999.999-99"),
        JURIDICA("99.999.999/9999-99");

        private final String mascara;

        TipoPessoa(String mascara) {
            this.mascara = mascara;
        }

        public String formatarDocumento(String documento) {
            String docLimpo = documento.replaceAll("\\D", "");
            return switch (this) {
                case FISICA -> docLimpo.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
                case JURIDICA -> docLimpo.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
            };
        }

        public String getMascara() {
            return mascara;
        }
    }
}