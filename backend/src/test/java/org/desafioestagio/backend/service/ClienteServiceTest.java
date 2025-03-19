package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void testSalvarCliente() {
        // Criar um novo cliente
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();
        cliente.setTipoPessoa(TipoPessoa.JURIDICA);
        endereco.setTelefone("123456789");
        cliente.setEndereco(endereco);
        cliente.setCpfCnpj("12345678000195");
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setRazaoSocial("Razão Social Teste");  // Preencher razaoSocial
        cliente.setAtivo(true);

        // Salvar o cliente
        Cliente clienteSalvo = clienteService.salvar(cliente);

        // Verificar se o cliente foi salvo
        assertNotNull(clienteSalvo);
        assertEquals(cliente.getCpfCnpj(), clienteSalvo.getCpfCnpj());
        assertEquals(cliente.getNome(), clienteSalvo.getNome());
    }

    @Test
    void testSalvarClienteComCpfInvalido() {
        // Criar cliente com CPF inválido
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("11111111111");  // CPF inválido
        cliente.setNome("Cliente Invalido");
        cliente.setEmail("cliente@teste.com");

        // Verificar exceção
        assertThrows(IllegalArgumentException.class, () -> clienteService.salvar(cliente));
    }

    @Test
    void testExcluirCliente() {
        // Criar e salvar um cliente
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("12345678901");
        cliente.setNome("Cliente Para Exclusao");
        cliente.setEmail("cliente@teste.com");

        cliente = clienteService.salvar(cliente);
        Long clienteId = cliente.getId();

        // Excluir cliente
        clienteService.excluir(clienteId);
        Optional<Cliente> clienteDeletado = clienteService.buscarPorId(clienteId);

        // Verificar se o cliente foi excluído
        assertFalse(clienteDeletado.isPresent());
    }
}
