package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Corrigido o retorno para retornar apenas um Optional de Cliente
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        String cpfCnpj = cliente.getCpfCnpj();

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

    // Validação simplificada de CPF e CNPJ
    private boolean validarCpfCnpj(String cpfCnpj) {
        if (!StringUtils.hasText(cpfCnpj)) {
            return false;
        }

        // Remover caracteres não numéricos (ponto, hífen, barra)
        cpfCnpj = cpfCnpj.replaceAll("[^0-9]", "");

        if (cpfCnpj.length() == 11) {  // CPF
            return validarCpf(cpfCnpj);
        } else if (cpfCnpj.length() == 14) {  // CNPJ
            return validarCnpj(cpfCnpj);
        }

        return false;  // Se não for nem CPF nem CNPJ válido
    }

    private boolean validarCpf(String cpf) {
        // Verificação mais robusta para CPF, incluindo a verificação de números repetidos (exemplo: 111.111.111-11)
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {
            return false;  // CPF inválido com números repetidos
        }

        // Lógica simples para verificar CPF (apenas um exemplo, a verificação pode ser mais complexa)
        if (cpf.length() != 11) return false;

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int resto = soma % 11;
        if (resto < 2) {
            if (cpf.charAt(9) != '0') return false;
        } else if (cpf.charAt(9) != (char) ('0' + (11 - resto))) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        resto = soma % 11;
        if (resto < 2) {
            if (cpf.charAt(10) != '0') return false;
        } else if (cpf.charAt(10) != (char) ('0' + (11 - resto))) {
            return false;
        }

        return true;
    }

    private boolean validarCnpj(String cnpj) {
        // Verificação de CNPJ válida (exemplo simples)
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999")) {
            return false;  // CNPJ inválido com números repetidos
        }

        if (cnpj.length() != 14) return false;

        int soma = 0;
        int[] multiplicador = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicador[i];
        }

        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        multiplicador = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * multiplicador[i];
        }

        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        return cnpj.charAt(12) == (char) ('0' + digito1) && cnpj.charAt(13) == (char) ('0' + digito2);
    }
}
