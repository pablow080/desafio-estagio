package org.desafioestagio.backend.service;

import org.desafioestagio.backend.exception.EnderecoNaoEncontradoException;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Método para buscar por ID do endereço
    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public List<Endereco> buscarPorNomeCliente(String nome) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCase(nome);
    }

    public List<Endereco> buscarPorRazaoSocialCliente(String razaoSocial) {
        return enderecoRepository.findByClienteRazaoSocialContainingIgnoreCase(razaoSocial);
    }

    public List<Endereco> buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    public List<Endereco> buscarPorEmailCliente(String email) {
        return enderecoRepository.findByClienteEmail(email);
    }

    public List<Endereco> buscarPorTelefone(String telefone) {
        return enderecoRepository.findByTelefone(telefone);
    }

    public List<Endereco> buscarPorNomeECEP(String nome, String cep) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCaseAndCep(nome, cep);
    }

    public List<Endereco> buscarPorNomeETelefone(String nome, String telefone) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCaseAndTelefone(nome, telefone);
    }

    public List<Endereco> buscarPorEstado(String estado) {
        return enderecoRepository.findByEstado(estado);
    }

    public List<Endereco> buscarPorCliente(Cliente cliente) {
        return enderecoRepository.findByCliente(cliente);
    }

    public List<Endereco> buscarPorClienteEEstado(Cliente cliente, String estado) {
        return enderecoRepository.findByClienteAndEstado(cliente, estado);
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void excluirEndereco(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
        } else {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id);
        }
    }
}
