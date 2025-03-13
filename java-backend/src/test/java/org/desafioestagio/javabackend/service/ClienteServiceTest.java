package org.desafioestagio.javabackend.test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.desafioestagio.javabackend.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.Test
    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente("John Doe", "12345678901");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.save(cliente);

        assertNotNull(savedCliente);
        assertEquals(cliente.getNome(), savedCliente.getNome());
    }

    @Test
    public void testValidarCpf() {
        assertTrue(clienteService.validarCpf("12345678901"));
        assertFalse(clienteService.validarCpf("00000000000"));
    }
}
