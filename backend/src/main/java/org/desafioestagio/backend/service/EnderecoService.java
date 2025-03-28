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

    /**
     * Lista todos os endereços.
     * @return Lista de endereços.
     */
    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    /**
     * Busca um endereço pelo seu ‘ID’.
     * @param id ‘ID’ do endereço.
     * @return Endereço encontrado.
     */
    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id));
    }

    /**
     * Busca endereços pelo nome do cliente.
     * @param nome Nome do cliente.
     * @return Lista de endereços do cliente.
     */
    public List<Endereco> buscarPorNomeCliente(String nome) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCase(nome);
    }

    /**
     * Busca endereços pelo nome da razão social do cliente.
     * @param razaoSocial Razão social do cliente.
     * @return Lista de endereços do cliente.
     */
    public List<Endereco> buscarPorRazaoSocialCliente(String razaoSocial) {
        return enderecoRepository.findByClienteRazaoSocialContainingIgnoreCase(razaoSocial);
    }

    /**
     * Busca endereços pelo CEP.
     *
     * @param cep CEP do endereço.
     * @return Lista de endereços com o mesmo CEP.
     */
    public Optional<Endereco> buscarPorCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    /**
     * Busca endereços pelo e-mail do cliente.
     * @param email E-mail do cliente.
     * @return Lista de endereços do cliente.
     */
    public List<Endereco> buscarPorEmailCliente(String email) {
        return enderecoRepository.findByClienteEmail(email);
    }

    /**
     * Busca endereços pelo telefone do cliente.
     * @param telefone Telefone do cliente.
     * @return Lista de endereços do cliente.
     */
    public List<Endereco> buscarPorTelefone(String telefone) {
        return enderecoRepository.findByTelefone(telefone);
    }

    /**
     * Busca endereços pelo nome do cliente e CEP.
     * @param nome Nome do cliente.
     * @param cep CEP do endereço.
     * @return Lista de endereços do cliente com o CEP informado.
     */
    public List<Endereco> buscarPorNomeECEP(String nome, String cep) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCaseAndCep(nome, cep);
    }

    /**
     * Busca endereços pelo nome do cliente e telefone.
     * @param nome Nome do cliente.
     * @param telefone Telefone do cliente.
     * @return Lista de endereços do cliente com o telefone informado.
     */
    public List<Endereco> buscarPorNomeETelefone(String nome, String telefone) {
        return enderecoRepository.findByClienteNomeContainingIgnoreCaseAndTelefone(nome, telefone);
    }

    /**
     * Busca endereços pelo estado.
     * @param estado Estado do endereço.
     * @return Lista de endereços no estado informado.
     */
    public List<Endereco> buscarPorEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado não pode ser vazio");
        }
        return enderecoRepository.findByEstado(estado);
    }

    /**
     * Busca endereços pelo cliente.
     * @param cliente Cliente associado ao endereço.
     * @return Lista de endereços do cliente.
     */
    public List<Endereco> buscarPorCliente(Cliente cliente) {
        return enderecoRepository.findByCliente(cliente);
    }

    /**
     * Busca endereços pelo cliente e estado.
     * @param cliente Cliente associado ao endereço.
     * @param estado Estado do endereço.
     * @return Lista de endereços do cliente no estado informado.
     */
    public List<Endereco> buscarPorClienteEEstado(Cliente cliente, String estado) {
        return enderecoRepository.findByClienteAndEstado(cliente, estado);
    }

    /**
     * Salva um endereço no banco de dados.
     * @param endereco Endereço a ser salvo.
     * @return Endereço salvo.
     */
    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    /**
     * Exclui um endereço pelo seu ID.
     * @param id ID do endereço a ser excluído.
     */
    public void excluirEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }
}
