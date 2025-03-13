package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.dao.ClienteDAO;
import org.desafioestagio.javabackend.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    // Listar todos os clientes
    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    // Salvar ou atualizar um cliente
    public Cliente salvarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        return clienteDAO.salvarCliente(cliente); // Chama o DAO para salvar o cliente
    }

    // Buscar cliente por ID
    public Cliente buscarClientePorId(Long id) {
        Optional<Cliente> cliente = clienteDAO.buscarClientePorId(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID " + id);
        }
    }

    // Excluir cliente por ID
    public void excluirCliente(Long id) {
        Optional<Cliente> clienteExistente = clienteDAO.buscarClientePorId(id);
        if (clienteExistente.isEmpty()) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado com o ID " + id);
        }
        clienteDAO.excluirCliente(id);
    }

    // Método de validação de CPF (implementado)
    public boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    // Exceção personalizada para cliente não encontrado
    public static class ClienteNaoEncontradoException extends RuntimeException {
        public ClienteNaoEncontradoException(String message) {
            super(message);
        }
    }
}
