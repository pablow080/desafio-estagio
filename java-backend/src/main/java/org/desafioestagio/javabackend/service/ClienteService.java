package org.desafioestagio.javabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Salvar um novo cliente
    public Cliente salvarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        // Exemplo de validação simples
        if (clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Cliente com o ID " + cliente.getId() + " já existe");
        }
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID " + id);
        }
    }

    // Atualizar um cliente existente
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (!clienteExistente.isPresent()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID " + id);
        }
        Cliente cliente = clienteExistente.get();
        cliente.setNome(clienteAtualizado.getNome()); // Exemplo de atualização de campo
        cliente.setEmail(clienteAtualizado.getEmail()); // Atualize os campos conforme necessário
        // Outros campos a atualizar
        return clienteRepository.save(cliente);
    }

    // Excluir cliente por ID
    public void excluirCliente(Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (!clienteExistente.isPresent()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID " + id);
        }
        clienteRepository.deleteById(id);
    }

    // Exemplo de exceção personalizada para cliente não encontrado
    public static class ClienteNaoEncontradoException extends RuntimeException {
        public ClienteNaoEncontradoException(String message) {
            super(message);
}
