package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@Entity
@Table(name = "endereco",
        indexes = @Index(columnList = "cliente_id, enderecoPrincipal", unique = true))
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 200, message = "Logradouro deve ter no máximo 200 caracteres")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 20, message = "Número deve ter no máximo 20 caracteres")
    private String numero;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
    private String cep;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro deve ter no máximo 100 caracteres")
    private String bairro;

    @Pattern(regexp = "\\d{10,11}", message = "Telefone inválido (10 ou 11 dígitos)")
    @Column(length = 11)
    private String telefone;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    private String cidade;

    @NotBlank(message = "UF é obrigatória")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String estado;

    @Column(nullable = false)
    private boolean enderecoPrincipal = false;

    @Size(max = 200, message = "Complemento deve ter no máximo 200 caracteres")
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_endereco_cliente"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;

    // Método auxiliar para formatação
    public String getEnderecoCompleto() {
        return String.format("%s, %s - %s, %s/%s - CEP: %s",
                logradouro, numero, bairro, cidade, estado, cep);
    }

    // Implementação para marcar o endereço como ativo ou inativo
    public void setAtivo(boolean ativo) {
        if (!ativo) {
            this.enderecoPrincipal = false; // Caso seja desativado, marca como não principal
        }
    }

    // Lógica para garantir que só um endereço pode ser marcado como principal por cliente
    public void setEnderecoPrincipal(boolean enderecoPrincipal) {
        if (enderecoPrincipal) {
            // Desmarca todos os outros endereços como principais
            cliente.getEnderecos().forEach(endereco -> endereco.setEnderecoPrincipal(false));
        }
        this.enderecoPrincipal = enderecoPrincipal;
    }
}
