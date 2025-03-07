package com.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    // Supondo que você tenha um repositório de Endereco
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco salvar(Endereco endereco) {
        if (enderecoRepository.existsByCep(endereco.getCep())) {
            throw new RuntimeException("CEP já cadastrado!");
        }
        return enderecoRepository.save(endereco);
    }
}
