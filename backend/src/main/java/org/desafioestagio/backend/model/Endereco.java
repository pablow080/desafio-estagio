package org.desafioestagio.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
@Setter
@Getter
@Entity
@Table(name = "endereco")
public class Endereco {
    // Getter e Setter para o campo id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Getter e Setter para o campo logradouro
    @NotBlank(message = "Logradouro é obrigatório")
    @Column(nullable = false)
    private String logradouro;

    // Getter e Setter para o campo numero
    @NotBlank(message = "Número é obrigatório")
    @Column(nullable = false)
    private String numero;

    // Getter e Setter para o campo cep
    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
    @Column(nullable = false)
    private String cep;

    // Getter e Setter para o campo bairro
    private String bairro;

    // Getter e Setter para o campo telefone
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Column(nullable = false)
    private String telefone;

    // Getter e Setter para o campo cidade
    private String cidade;

    // Getter e Setter para o campo estado
    private String estado;

    // Getter e Setter para o campo enderecoPrincipal
    @Column(nullable = false)
    private boolean enderecoPrincipal = false;

    // Getter e Setter para o campo complemento
    private String complemento;

    // Getter e Setter para o campo cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Construtor padrão (necessário para o Hibernate)
    public Endereco() {}

    // Se precisar de lógica adicional, adicione-a aqui (por exemplo, verificação ou manipulação de dados antes de persistir).
}
