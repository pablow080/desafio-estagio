package org.desafioestagio.javabackend.service;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.TipoPessoa;
import org.desafioestagio.javabackend.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente(TipoPessoa.FISICA, "12345678901", "John Doe", "john.doe@example.com");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente savedCliente = clienteService.salvar(cliente);

        assertNotNull(savedCliente);
        assertEquals(cliente.getNome(), savedCliente.getNome());
        verify(clienteRepository).save(cliente);
    }

    @Test
    public void testValidarCpf() {
        // Teste com CPF válido
        assertTrue(clienteService.validarCpf("12345678901"));  // CPF válido de exemplo (este deve ser um CPF válido real)

        // Teste com CPF inválido (todos zeros, por exemplo)
        assertFalse(clienteService.validarCpf("00000000000"));  // CPF inválido (todos zeros)

        // Teste com CPF válido com formatação
        assertTrue(clienteService.validarCpf("123.456.789-01"));  // CPF válido com formatação

        // Teste com CPF inválido com formatação
        assertFalse(clienteService.validarCpf("111.111.111-11"));  // CPF inválido, todos os números iguais
    }

    @Test
    public void testValidarCnpj() {
        // Teste com CNPJ válido
        assertTrue(clienteService.validarCnpj("12345678000195"));  // CNPJ válido de exemplo (este deve ser um CNPJ válido real)

        // Teste com CNPJ inválido (todos zeros, por exemplo)
        assertFalse(clienteService.validarCnpj("00000000000000"));  // CNPJ inválido (todos zeros)

        // Teste com CNPJ válido com formatação
        assertTrue(clienteService.validarCnpj("12.345.678/0001-95"));  // CNPJ válido com formatação

        // Teste com CNPJ inválido com formatação
        assertFalse(clienteService.validarCnpj("11.111.111/1111-11"));  // CNPJ inválido, todos os números iguais
    }
}
