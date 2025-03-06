@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoPessoa; // Física ou Jurídica
    private String cpfCnpj; // Física ou Jurídica
    private String nome; // Física
    private String rg; // Física
    private LocalDate dataNascimento; // Física
    private String razaoSocial; // Jurídica
    private String inscricaoEstadual; // Jurídica
    private LocalDate dataCriacao; // Jurídica
    private String email;
    private boolean ativo;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
}