package org.desafioestagio.javabackend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.dao.ClienteDAO;
import org.desafioestagio.javabackend.model.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteDAO clienteDAO; // Mock de ClienteDAO, não ClienteRepository

    @InjectMocks
    private ClienteService clienteService; // ClienteService é a classe que está sendo testada

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    public void testCreateCliente() {
        // Criando um cliente com o tipo correto (usando o enum)
        Cliente cliente = new Cliente(TipoPessoa.FISICA, "12345678901", "John Doe", "john.doe@example.com");

        // Configurando o comportamento do mock
        when(clienteDAO.salvarCliente(cliente)).thenReturn(cliente);

        // Chama o método de serviço
        Cliente savedCliente = clienteService.salvarCliente(cliente); // Usando salvarCliente no lugar de save

        // Validações
        assertNotNull(savedCliente); // Verifica que o cliente não é nulo
        assertEquals(cliente.getNome(), savedCliente.getNome()); // Verifica se o nome é o mesmo
        verify(clienteDAO).salvarCliente(cliente); // Verifica que salvarCliente foi chamado no DAO
    }

    @Test
    public void testValidarCpf() {
        // Teste para validar CPF
        assertTrue(clienteService.validarCpf("12345678901"));
        assertFalse(clienteService.validarCpf("00000000000"));
    }
}
