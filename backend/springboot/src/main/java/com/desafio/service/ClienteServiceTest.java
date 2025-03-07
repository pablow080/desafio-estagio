package com.desafio.service;

public class ClienteServiceTest {

    @Test
    public void testCriarClienteComCPFDuplicado() {
        Cliente cliente = new Cliente();
        cliente.setCpfCnpj("12345678900");
        assertThrows(RuntimeException.class, () -> clienteService.salvar(cliente));
    }
}