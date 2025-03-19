package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        if (endereco.getCliente() == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo");
        }
        return enderecoRepository.save(endereco);
    }

    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }
}
