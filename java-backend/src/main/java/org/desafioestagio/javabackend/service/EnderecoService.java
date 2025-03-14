package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.Endereco;
import org.desafioestagio.javabackend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Método para listar todos os endereços
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    // Método para buscar endereço por ID
    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    // Método para salvar ou atualizar o endereço
    public Endereco salvar(Endereco endereco) {
        verificarEnderecoPrincipal(endereco, endereco.getCliente());
        return enderecoRepository.save(endereco);
    }

    // Método para atualizar um endereço existente
    public Endereco atualizar(Long id, Endereco endereco) {
        if (!enderecoRepository.existsById(id)) {
            throw new IllegalArgumentException("Endereço não encontrado.");
        }
        endereco.setId(id);
        return salvar(endereco);  // Reutiliza a lógica de salvar
    }

    // Método para excluir um endereço
    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }

    // Método para verificar se já existe um endereço principal
    public void verificarEnderecoPrincipal(Endereco endereco, Cliente cliente) {
        if (endereco.isEnderecoPrincipal()) {
            boolean enderecoPrincipalExistente = cliente.getEnderecos().stream()
                    .anyMatch(Endereco::isEnderecoPrincipal);
            if (enderecoPrincipalExistente) {
                throw new IllegalStateException("Já existe um endereço principal para este cliente.");
            }
        }
    }
}
