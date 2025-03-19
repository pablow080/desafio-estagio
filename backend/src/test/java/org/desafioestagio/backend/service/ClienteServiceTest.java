package org.desafioestagio.backend.service;

import org.desafioestagio.backend.BackendApplication;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSalvarCliente() {
        // Cria um cliente
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.Fisica);
        cliente.setCpfCnpj("12345678901");
        cliente.setNome("João Silva");
        cliente.setEmail("joao@email.com");
        cliente.setAtivo(true);

        // Salva o cliente
        Cliente salvo = clienteService.salvar(cliente);

        // Verifica se o cliente foi salvo corretamente
        assertNotNull(salvo.getId(), "O ID do cliente não pode ser nulo após a persistência.");

        // Busca o cliente pelo ID
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteService.buscarPorId(salvo.getId()));

        // Verifica se o cliente foi encontrado
        assertTrue(clienteEncontrado.isPresent(), "Cliente não encontrado após ser salvo.");
    }

    @Test
    public void testBuscarCliente() {
        // Cria e salva um cliente no banco de dados
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.Fisica);
        cliente.setCpfCnpj("12345678901");
        cliente.setNome("João Silva");
        cliente.setEmail("joao@email.com");
        cliente.setAtivo(true);
        clienteRepository.save(cliente);

        // Busca o cliente pelo ID
        Optional<Cliente> clienteEncontrado = Optional.ofNullable(clienteService.buscarPorId(cliente.getId()));

        // Verifica se o cliente foi encontrado
        assertTrue(clienteEncontrado.isPresent(), "Cliente não encontrado com o ID correto.");
    }

    @Test
    public void testSalvarClienteComCpfCnpjDuplicado() {
        // Cria e salva o primeiro cliente
        Cliente cliente1 = new Cliente();
        cliente1.setTipoPessoa(TipoPessoa.Fisica);
        cliente1.setCpfCnpj("12345678901");
        cliente1.setNome("João Silva");
        cliente1.setEmail("joao@email.com");
        cliente1.setAtivo(true);
        clienteService.salvar(cliente1);

        // Cria o segundo cliente com o mesmo CPF
        Cliente cliente2 = new Cliente();
        cliente2.setTipoPessoa(TipoPessoa.Fisica);
        cliente2.setCpfCnpj("12345678901");
        cliente2.setNome("Maria Oliveira");
        cliente2.setEmail("maria@email.com");
        cliente2.setAtivo(true);

        // Verifica se a exceção é lançada ao tentar salvar o cliente com CPF duplicado
        assertThrows(ClienteJaCadastradoException.class, () -> {
            clienteService.salvar(cliente2);
        }, "Deveria lançar exceção ao tentar salvar um cliente com CPF/CNPJ duplicado.");
    }
}
