package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Método para buscar endereços de um cliente
    List<Endereco> findByCliente(Cliente cliente);

    // Método para buscar endereços de um cliente com base no estado
    List<Endereco> findByClienteAndEstado(Cliente cliente, String estado);

    // Método para buscar endereços pelo estado
    List<Endereco> findByEstado(String estado);

    // Método para buscar endereços pelo estado e pelo cliente
    List<Endereco> findByEstadoAndCliente(String estado, Cliente cliente);

    // Métodos para buscar endereços com base nos parâmetros solicitados
    List<Endereco> findByClienteNomeContainingIgnoreCase(String nome);

    List<Endereco> findByClienteRazaoSocialContainingIgnoreCase(String razaoSocial);

    List<Endereco> findByClienteEmail(String email);

    List<Endereco> findByTelefone(String telefone);

    // Método para buscar endereços pelo nome do cliente e o CEP
    List<Endereco> findByClienteNomeContainingIgnoreCaseAndCep(String nome, String cep);

    // Método para buscar endereços pelo nome do cliente e o telefone
    List<Endereco> findByClienteNomeContainingIgnoreCaseAndTelefone(String nome, String telefone);

    // Método para buscar endereços pelo CEP
    Optional<Endereco> findByCep(String cep);
}
