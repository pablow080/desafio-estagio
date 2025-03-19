package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLogradouro("Rua A");
        endereco.setNumero("123");
        endereco.setCep("12345-678");
        endereco.setTelefone("(11) 98765-4321");
        endereco.setCliente(cliente);
    }

    @Test
    void testSalvarEndereco() {
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        // Chamada do método correto
        Endereco salvo = enderecoService.salvar(endereco);

        assertNotNull(salvo);
        assertEquals("Rua A", salvo.getLogradouro());
    }

    @Test
    void testBuscarEnderecoPorId() {
        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Endereco encontrado = enderecoService.buscarPorId(1L).orElse(null); // Atualizado para refletir método do serviço
        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void testExcluirEndereco() {
        doNothing().when(enderecoRepository).deleteById(1L);
        assertDoesNotThrow(() -> enderecoService.excluir(1L));
        verify(enderecoRepository, times(1)).deleteById(1L);
    }

    // Testando exceção para CEP inválido
    @Test
    void testSalvarEnderecoComCepInvalido() {
        endereco.setCep("1234-567"); // CEP inválido
        assertThrows(IllegalArgumentException.class, () -> enderecoService.salvar(endereco));
    }

    // Testando exceção para Telefone inválido
    @Test
    void testSalvarEnderecoComTelefoneInvalido() {
        endereco.setTelefone("987654321"); // Telefone inválido
        assertThrows(IllegalArgumentException.class, () -> enderecoService.salvar(endereco));
    }
}