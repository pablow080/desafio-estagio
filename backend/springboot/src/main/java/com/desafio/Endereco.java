@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String telefone;
    private String cidade;
    private String estado;
    private boolean enderecoPrincipal;
    private String complemento;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}