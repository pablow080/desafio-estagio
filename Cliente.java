import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo de Pessoa é obrigatório")
    @Pattern(regexp = "Física|Jurídica", message = "Tipo inválido")
    private String tipoPessoa;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @Pattern(regexp = "\\d{11}|\\d{14}", message = "CPF/CNPJ inválido")
    private String cpfCnpj;

    @NotBlank(message = "Nome é obrigatório para pessoa física")
    private String nome;

    private String rg;
    private Date dataNascimento;

    @NotBlank(message = "Razão Social é obrigatória para pessoa jurídica")
    private String razaoSocial;

    private String inscricaoEstadual;
    private Date dataCriacao;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    private boolean ativo = true;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos;
}