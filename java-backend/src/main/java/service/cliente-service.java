@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        if (cliente.getTipoPessoa().equals("FISICA") && clienteRepository.findByCpf(cliente.getCpf()) != null) {
            throw new ValidationException("CPF já cadastrado!");
        }
        // ... outras validações
        return clienteRepository.save(cliente);
    }
}