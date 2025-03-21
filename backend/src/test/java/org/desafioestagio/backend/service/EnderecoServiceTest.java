package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.desafioestagio.backend.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional  // Garante que os dados sejam revertidos após cada teste
class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Endereco endereco;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        // Limpa os dados no banco antes de cada teste
        clienteRepository.deleteAll();
        enderecoRepository.deleteAll();

        // Verificar se o CPF já existe
        Cliente clienteExistente = clienteRepository.findByCpfCnpj("123.456.789-00");
        if (clienteExistente != null) {
            clienteRepository.delete(clienteExistente);
        }

        // Agora, cria o cliente e o endereço
        cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("123.456.789-00");  // CPF único
        cliente.setNome("João da Silva");
        cliente.setRg("12.345.678-9");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        cliente.setEmail("joao.silva@email.com");
        cliente.setAtivo(true);

        cliente = clienteRepository.save(cliente);

        endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setCep("12345-678");
        endereco.setBairro("Jardim das Palmeiras");
        endereco.setTelefone("987654321");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setEnderecoPrincipal(true);
        endereco.setComplemento("Apartamento 45");
        endereco.setCliente(cliente);

        endereco = enderecoRepository.save(endereco);
    }


    @Test
    void testeExcluirEndereco() {
        Long enderecoId = endereco.getId();
        assertNotNull(enderecoRepository.findById(enderecoId));

        // Excluir o endereço
        enderecoService.excluirEndereco(enderecoId);

        // Verificar se o endereço foi excluído
        assertFalse(enderecoRepository.existsById(enderecoId), "Endereço não foi excluído com sucesso.");
    }
}
