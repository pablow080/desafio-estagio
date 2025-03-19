package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.TipoPessoa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.repository.ClienteRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")  // Usa o perfil de teste
public class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @Transactional  // Garante que os testes não alterem permanentemente o banco de dados
    public void testCriarCliente() {
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.Fisica);
        cliente.setCpfCnpj("12345678901");
        cliente.setNome("Maria Silva");
        cliente.setEmail("maria@email.com");
        cliente.setAtivo(true);

        Cliente salvo = clienteRepository.save(cliente);

        // Verifica se o cliente foi salvo corretamente
        assertNotNull(salvo.getId(), "O ID do cliente não pode ser nulo após a persistência.");
    }
}
