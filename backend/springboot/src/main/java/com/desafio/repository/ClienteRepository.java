package com.example.clientes.repository;

import com.example.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Verifica se CPF/CNPJ já existe
    boolean existsByCpfCnpj(String cpfCnpj);
}