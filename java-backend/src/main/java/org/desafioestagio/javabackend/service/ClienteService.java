package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        String cpfCnpj = cliente.getCpfCnpj();  // Supondo que o CPF ou CNPJ está armazenado em getCpfCnpj()

        if (!validarCpfCnpj(cpfCnpj)) {
            throw new IllegalArgumentException("CPF ou CNPJ inválido.");
        }

        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            // Tratar erros de violação de integridade (como dados duplicados)
            throw new RuntimeException("Erro ao salvar cliente: " + e.getMessage(), e);
        }
    }


    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    private boolean validarCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null || cpfCnpj.isEmpty()) {
            return false;
        }

        // Se for um CPF (11 dígitos)
        if (cpfCnpj.length() == 11) {
            return validarCpf(cpfCnpj);
        }

        // Se for um CNPJ (14 dígitos)
        if (cpfCnpj.length() == 14) {
            return validarCnpj(cpfCnpj);
        }

        return false;  // Caso tenha um tamanho diferente de 11 ou 14, é inválido
    }

    boolean validarCpf(String cpf) {
        // Implementação da validação de CPF
        return cpf != null && cpf.matches("^[0-9]{11}$");
    }

    boolean validarCnpj(String cnpj) {
        // Implementação da validação de CNPJ
        return cnpj != null && cnpj.matches("^[0-9]{14}$");
    }

}
