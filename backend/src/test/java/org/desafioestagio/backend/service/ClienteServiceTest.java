package org.desafioestagio.backend.service;

import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("123.456.789-00");
        cliente.setNome("João da Silva");
        cliente.setRg("12.345.678-9");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        cliente.setEmail("joao.silva@email.com");
        cliente.setAtivo(true);
    }


    @Test
    void listarTodos_DeveRetornarPaginaDeClientes() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cliente> pageMock = new PageImpl<>(Collections.singletonList(cliente));  // Criando uma página com um cliente mockado // Mockando Page<Cliente>
        when(clienteRepository.findAll(pageable)).thenReturn(pageMock);

        // Act
        Page<Cliente> result = clienteService.listarTodos(pageable);

        // Assert
        assertNotNull(result);
        verify(clienteRepository, times(1)).findAll(pageable); // Verifica se o método foi chamado uma vez
    }

    @Test
    void testSalvarClienteComCpfCnpjJaCadastrado() {
        when(clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())).thenReturn(true);

        ClienteJaCadastradoException exception = assertThrows(ClienteJaCadastradoException.class, () -> {
            clienteService.salvar(cliente);
        });

        assertEquals("CPF/CNPJ já cadastrado", exception.getMessage());
    }

    @Test
    void testSalvarClienteComSucesso() {
        when(clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = clienteService.salvar(cliente);

        assertNotNull(clienteSalvo);
        assertEquals(cliente.getId(), clienteSalvo.getId());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testBuscarClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente clienteEncontrado = clienteService.buscarPorId(1L);

        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getId(), clienteEncontrado.getId());
    }

    @Test
    void testExcluirCliente() {
        when(clienteRepository.existsById(1L)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.excluir(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testExcluirClienteNaoEncontrado() {
        when(clienteRepository.existsById(1L)).thenReturn(false);

        ClienteNaoEncontradoException exception = assertThrows(ClienteNaoEncontradoException.class, () -> {
            clienteService.excluir(1L);
        });

        assertEquals("Cliente não encontrado para exclusão com id: 1", exception.getMessage());
    }
}
