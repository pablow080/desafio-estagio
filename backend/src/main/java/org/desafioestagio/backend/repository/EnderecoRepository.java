package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    // Consulta personalizada para buscar endereço por CEP
    Endereco findByCep(String cep);

    // Consulta personalizada para buscar endereço por telefone
    Endereco findByTelefone(String telefone);
}
