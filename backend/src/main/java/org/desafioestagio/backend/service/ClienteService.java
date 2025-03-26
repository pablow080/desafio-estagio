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

    public Page<Cliente> listarTodos(Pageable pageable) {
        logger.info("Listando todos os clientes com paginação: {}", pageable);
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarPorId(Long id) {
        logger.info("Buscando cliente com ID: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Cliente não encontrado com ID: {}", id);
                    return new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id);
                });
    }

    public Cliente salvar(Cliente cliente) {
        logger.info("Tentando salvar o cliente: {}", cliente.getCpfCnpj());
        if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
            logger.error("Cliente já cadastrado com CPF/CNPJ: {}", cliente.getCpfCnpj());
            throw new ClienteJaCadastradoException("CPF/CNPJ já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        logger.info("Tentando excluir cliente com ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            logger.error("Cliente não encontrado para exclusão com ID: {}", id);
            throw new ClienteNaoEncontradoException("Cliente não encontrado para exclusão com id: " + id);
        }
        clienteRepository.deleteById(id);
        logger.info("Cliente com ID {} excluído com sucesso", id);
    }

    public Cliente getClienteFromExternalService(Long id) {
        String url = "http://external-service/api/clients/" + id;
        return restTemplate.getForObject(url, Cliente.class);
    }

    // Método para retornar informações de um cliente de forma simples
    public String getClienteInfo(Long id) {
        Cliente cliente = buscarPorId(id); // Recupera o cliente com o ID
        return "Cliente: " + cliente.getNome() + ", CPF/CNPJ: " + cliente.getCpfCnpj(); // Retorna as informações do cliente
    }
}
