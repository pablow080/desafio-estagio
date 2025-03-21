package org.desafioestagio.backend.controller;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks

        // Instanciando o cliente
        cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("123.456.789-00");
        cliente.setNome("João da Silva");
        cliente.setRg("12.345.678-9");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        cliente.setEmail("joao.silva@email.com");
        cliente.setAtivo(true);

        // Instanciando um endereço
        Endereco enderecoFisico = new Endereco();
        enderecoFisico.setLogradouro("Rua das Flores");
        enderecoFisico.setNumero("123");
        enderecoFisico.setCep("12345-678");
        enderecoFisico.setBairro("Jardim das Palmeiras");
        enderecoFisico.setTelefone("987654321");
        enderecoFisico.setCidade("São Paulo");
        enderecoFisico.setEstado("SP");
        enderecoFisico.setEnderecoPrincipal(true);
        enderecoFisico.setComplemento("Apartamento 45");
        enderecoFisico.setCliente(cliente);

        // Adicionando o endereço ao cliente
        Set<Endereco> enderecos = new HashSet<>();
        enderecos.add(enderecoFisico);
        cliente.setEnderecos(enderecos);
    }

    @Test
    void listarTodos_DeveRetornarPaginaDeClientes() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cliente> pageMock = mock(Page.class);
        when(clienteService.listarTodos(pageable)).thenReturn(pageMock);  // Mockando a resposta do serviço

        // Act
        ResponseEntity<Page<Cliente>> response = clienteController.listarTodos(pageable);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());  // Verificando se o status é 200 OK
        verify(clienteService, times(1)).listarTodos(pageable);  // Verifica se o método foi chamado uma vez
    }
}
