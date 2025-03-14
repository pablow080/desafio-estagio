package org.desafioestagio.javabackend.repository;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByTipoPessoa(TipoPessoa tipoPessoa);
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    List<Cliente> findByNomeContainingOrCpfCnpjContaining(String nome, String cpfCnpj);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByAtivo(boolean ativo);

    @Query("SELECT c FROM Cliente c JOIN c.enderecos e WHERE e.enderecoPrincipal = true")
    List<Cliente> findClientesComEnderecoPrincipal();
}
