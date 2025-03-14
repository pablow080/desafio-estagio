package org.desafioestagio.javabackend.dao;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClienteDAO {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}
