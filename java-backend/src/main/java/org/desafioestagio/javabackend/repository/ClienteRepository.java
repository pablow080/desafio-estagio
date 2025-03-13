package org.desafioestagio.javabackend.repository;

import jakarta.validation.constraints.NotNull;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByTipoPessoa(@NotNull(message = "Tipo de Pessoa é obrigatório") TipoPessoa tipoPessoa);
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
    List<Cliente> findByNome(String nome);
    Optional<Cliente> findByRg(String rg);
    List<Cliente> findByDataNascimento(LocalDate dataNascimento);  // Alterado para LocalDate
    List<Cliente> findByRazaoSocial(String razaoSocial);
    List<Cliente> findByInscricaoEstadual(String inscricaoEstadual);
    List<Cliente> findByDataCriacao(LocalDate dataCriacao);  // Alterado para LocalDate
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByAtivo(boolean ativo);

    @Query("SELECT c FROM Cliente c JOIN c.enderecos e WHERE e.enderecoPrincipal = true")
    List<Cliente> findClientesComEnderecoPrincipal();

    List<Cliente> findByNomeContainingOrCpfCnpjContaining(String nome, String cpfCnpj);
}
