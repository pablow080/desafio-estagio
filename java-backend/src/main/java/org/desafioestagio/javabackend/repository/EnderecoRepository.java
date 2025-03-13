package org.desafioestagio.javabackend.repository;

import org.desafioestagio.javabackend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Buscar endereços por cliente
    List<Endereco> findByClienteId(Long clienteId);

    // Buscar endereço principal de um cliente
    Optional<Endereco> findByClienteIdAndEnderecoPrincipalTrue(Long clienteId);

    // Buscar endereços por cidade
    List<Endereco> findByCidade(String cidade);

    // Buscar endereços por estado
    List<Endereco> findByEstado(String estado);

    // Usando JPQL para encontrar o endereço principal de um cliente
    @Query("SELECT e FROM Endereco e WHERE e.cliente.id = :clienteId AND e.enderecoPrincipal = true")
    Optional<Endereco> findEnderecoPrincipalByClienteId(Long clienteId);
}
