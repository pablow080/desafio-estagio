package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpfCnpj(String cpfCnpj);
    Cliente findByCpfCnpj(String cpfCnpj); // Método para buscar cliente pelo CPF/CNPJ
}
