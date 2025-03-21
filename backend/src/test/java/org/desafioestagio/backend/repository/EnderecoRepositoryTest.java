package org.desafioestagio.backend.repository;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        enderecoRepository.deleteAll(); // Limpar endereços antes
        clienteRepository.deleteAll(); // Limpar clientes antes

        cliente = new Cliente(); // Usar o atributo de classe
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("123.456.789-00");
        cliente.setNome("João da Silva");
        cliente.setRg("12.345.678-9");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        cliente.setEmail("joao.silva@email.com");
        cliente.setAtivo(true);
        cliente = clienteRepository.save(cliente); // Salvar cliente antes do endereço

        endereco = new Endereco(); // Usar o atributo de classe
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setCep("12345-678");
        endereco.setBairro("Jardim das Palmeiras");
        endereco.setTelefone("987654321");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP"); // Certificar que está correto
        endereco.setEnderecoPrincipal(true);
        endereco.setComplemento("Apartamento 45");
        endereco.setCliente(cliente); // Associar cliente ao endereço

        endereco = enderecoRepository.save(endereco); // Salvar endereço
    }



    @Test
    void testFindByCliente() {
        List<Endereco> enderecos = enderecoRepository.findByCliente(cliente);
        assertFalse(enderecos.isEmpty());
        assertEquals(cliente.getId(), enderecos.get(0).getCliente().getId());
    }

    @Test
    void testFindByEstado() {
        List<Endereco> enderecos = enderecoRepository.findByEstado("SP"); // Corrigido
        assertFalse(enderecos.isEmpty());
    }

    @Test
    void testFindByClienteAndEstado() {
        List<Endereco> enderecos = enderecoRepository.findByClienteAndEstado(cliente, "SP"); // Corrigido
        assertFalse(enderecos.isEmpty());
    }
}
