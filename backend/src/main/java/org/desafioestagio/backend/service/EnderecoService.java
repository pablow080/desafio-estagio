package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        try {
            // Validações
            if (!validarCep(endereco.getCep())) {
                throw new IllegalArgumentException("CEP inválido");
            }
            if (!validarTelefone(endereco.getTelefone())) {
                throw new IllegalArgumentException("Telefone inválido");
            }

            return enderecoRepository.save(endereco);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar endereço: " + e.getMessage(), e);
        }
    }

    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }

    // Validações
    private boolean validarCep(String cep) {
        return cep != null && cep.matches("\\d{5}-\\d{3}");
    }

    private boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}");
    }
}
