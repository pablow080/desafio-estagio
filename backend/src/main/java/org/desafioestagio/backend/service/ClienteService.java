package org.desafioestagio.backend.service;

import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método para listar clientes com paginação
    public Page<Cliente> listarTodos(Pageable pageable) {
        return clienteRepository.findAll(pageable);  // Usa o pageable para retornar a lista paginada
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
