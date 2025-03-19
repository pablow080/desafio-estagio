package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.controller.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
            throw new RuntimeException("CPF/CNPJ já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}