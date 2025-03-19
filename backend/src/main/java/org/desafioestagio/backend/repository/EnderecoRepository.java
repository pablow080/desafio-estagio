package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByCliente(Cliente cliente);
    List<Endereco> findByClienteAndEstado(Cliente cliente, String estado);
    List<Endereco> findByEstado(String estado);
    List<Endereco> findByEstadoAndCliente(String estado, Cliente cliente);
}
