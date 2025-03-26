package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpfCnpj(String cpfCnpj);
    Cliente findByCpfCnpj(String cpfCnpj);

    // Método para buscar clientes pelo nome (usando parte do nome)
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    // Método para buscar clientes pela razão social
    List<Cliente> findByRazaoSocialContainingIgnoreCase(String razaoSocial);

    // Método para buscar clientes pelo email
    List<Cliente> findByEmail(String email);

    // Método para buscar clientes pelo nome e pela inscrição estadual
    List<Cliente> findByNomeContainingIgnoreCaseAndInscricaoEstadual(String nome, String inscricaoEstadual);
}
