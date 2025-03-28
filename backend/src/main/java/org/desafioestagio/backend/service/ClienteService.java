package org.desafioestagio.backend.service;

import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Lista todos os clientes com paginação.
     * @param pageable Configuração de paginação.
     * @return Página de clientes.
     */
    public Page<Cliente> listarTodos(Pageable pageable) {
        logger.info("Listando todos os clientes com paginação: {}", pageable);
        return clienteRepository.findAll(pageable);
    }

    /**
     * Busca um cliente pelo seu ID.
     * @param id ID do cliente.
     * @return Cliente encontrado.
     */
    public Cliente buscarPorId(Long id) {
        logger.info("Buscando cliente com ID: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));
    }

    /**
     * Salva um cliente no banco de dados.
     * @param cliente Cliente a ser salvo.
     * @return Cliente salvo.
     */
    public Cliente salvar(Cliente cliente) {
        logger.info("Tentando salvar o cliente: {}", esconderCpfCnpj(cliente.getCpfCnpj()));
        if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
            logger.error("Cliente já cadastrado com CPF/CNPJ: {}", esconderCpfCnpj(cliente.getCpfCnpj()));
            throw new ClienteJaCadastradoException("CPF/CNPJ já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    /**
     * Exclui um cliente pelo seu ID.
     * @param id ID do cliente a ser excluído.
     */
    public void excluir(Long id) {
        logger.info("Tentando excluir cliente com ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            logger.error("Cliente não encontrado para exclusão com ID: {}", id);
            throw new ClienteNaoEncontradoException("Cliente não encontrado para exclusão com id: " + id);
        }
        clienteRepository.deleteById(id);
        logger.info("Cliente com ID {} excluído com sucesso", id);
    }

    /**
     * Obtém um cliente de um serviço externo.
     * @param id ID do cliente no serviço externo.
     * @return Cliente obtido do serviço externo.
     */
    public Cliente getClienteFromExternalService(Long id) {
        String url = "http://external-service/api/clients/" + id;
        try {
            return restTemplate.getForObject(url, Cliente.class);
        } catch (Exception e) {
            logger.error("Erro ao buscar cliente no serviço externo com ID: {}", id, e);
            throw new RuntimeException("Erro ao acessar serviço externo");
        }
    }


    /**
     * Retorna informações simples sobre um cliente.
     * @param id ID do cliente.
     * @return Informações simples sobre o cliente.
     */
    public String getClienteInfo(Long id) {
        Cliente cliente = buscarPorId(id); // Recupera o cliente com o ID
        return "Cliente: " + cliente.getNome() + ", CPF/CNPJ: " + esconderCpfCnpj(cliente.getCpfCnpj());
    }

    /**
     * Esconde parte do CPF/CNPJ para evitar exposição de dados sensíveis nos logs.
     * @param documento CPF ou CNPJ.
     * @return CPF/CNPJ com parte do número ocultado.
     */
    private String esconderCpfCnpj(String documento) {
        if (documento == null) return null;
        return documento.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "***.***.***-$4")
                .replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.***.***.$4-$5");
    }
}
