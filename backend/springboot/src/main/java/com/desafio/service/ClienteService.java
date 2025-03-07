package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    // Supondo que você tenha um repositório de Cliente
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())) {
            throw new RuntimeException("CPF/CNPJ já cadastrado!");
        }
        return clienteRepository.save(cliente);
    }
}
