package org.desafioestagio.javabackend.dao;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteDAO {

    @Autowired
    private ClienteRepository clienteRepository;

    // Salvar ou atualizar um cliente
    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Excluir cliente
    public boolean excluirCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Atualizar um cliente específico
    public Optional<Cliente> atualizarCliente(Long id, Cliente clienteAtualizado) {
        if (clienteRepository.existsById(id)) {
            clienteAtualizado.setId(id);  // Garantir que o ID seja o mesmo
            return Optional.of(clienteRepository.save(clienteAtualizado));
        }
        return Optional.empty();  // Retornar Optional vazio se o cliente não existir
    }
}
