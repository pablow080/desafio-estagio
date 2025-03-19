package org.desafioestagio.backend.service;

import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com id: " + id));
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
            throw new ClienteJaCadastradoException("CPF/CNPJ já cadastrado");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado para exclusão com id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
