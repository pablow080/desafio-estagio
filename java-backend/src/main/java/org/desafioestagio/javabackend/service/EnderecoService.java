package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.model.Endereco;
import org.desafioestagio.javabackend.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    // Injeção de dependência via construtor
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    // Listar todos os endereços
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    // Buscar endereço por ID
    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    // Salvar um novo endereço
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    // Excluir endereço por ID
    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }

    // Atualizar endereço existente
    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {
        return enderecoRepository.findById(id)
                .map(enderecoExistente -> {
                    enderecoExistente.setLogradouro(enderecoAtualizado.getLogradouro());
                    enderecoExistente.setNumero(enderecoAtualizado.getNumero());
                    enderecoExistente.setCep(enderecoAtualizado.getCep());
                    enderecoExistente.setBairro(enderecoAtualizado.getBairro());
                    enderecoExistente.setCidade(enderecoAtualizado.getCidade());
                    enderecoExistente.setEstado(enderecoAtualizado.getEstado());
                    return enderecoRepository.save(enderecoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
    }
}
