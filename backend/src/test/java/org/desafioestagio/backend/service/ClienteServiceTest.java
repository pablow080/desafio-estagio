package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    public void testSalvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa("Física");
        cliente.setCpfCnpj("12345678901");
        cliente.setNome("João Silva");
        cliente.setEmail("joao@email.com");
        cliente.setAtivo(true);

        Cliente salvo = clienteService.salvar(cliente);
        Assertions.assertNotNull(salvo.getId());
    }

    @Test
    public void testBuscarCliente() {
        Optional<Cliente> cliente = clienteService.buscarPorId(1L);
        Assertions.assertTrue(cliente.isPresent());
    }
}
