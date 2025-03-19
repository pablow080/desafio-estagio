package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Consultas personalizadas para buscar clientes
    Cliente findByEmail(String email);
    Cliente findByInscricaoEstadual(String inscricaoEstadual);
    Cliente findByRazaoSocial(String razaoSocial);
    Cliente findByRg(String rg);
    Cliente findByNome(String nome);
    Cliente findByCpfCnpj(String cpfCnpj);

    // Refatorado para pesquisar endereços de forma diferente
    List<Cliente> findByEnderecos(Endereco endereco);
}
