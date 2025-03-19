package org.desafioestagio.backend.service;

import org.desafioestagio.backend.BackendApplication;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = BackendApplication.class)
@ActiveProfiles("test")
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService enderecoService;

    @Test
    public void testSalvarEndereco() {
        // Cria o cliente
        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpfCnpj("12345678900");
        cliente.setTipoPessoa(TipoPessoa.Fisica);
        cliente.setAtivo(true);
        // Salve o cliente se necessário ou configure um mock

        // Cria o endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setCep("12345-678");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        endereco.setCliente(cliente); // Associação com o cliente

        // Salva o endereço
        Endereco salvo = enderecoService.salvar(endereco);

        // Verifica se o ID foi gerado
        Assertions.assertNotNull(salvo.getId());

        // Verifica se os dados salvos estão corretos
        Assertions.assertEquals("Rua das Flores", salvo.getLogradouro());
        Assertions.assertEquals("123", salvo.getNumero());
        Assertions.assertEquals("12345-678", salvo.getCep());
        Assertions.assertEquals("São Paulo", salvo.getCidade());
        Assertions.assertEquals("SP", salvo.getEstado());
    }

    @Test
    public void testExcluirEndereco() {
        // Cria o endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setCep("12345-678");
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");

        // Salva o endereço
        Endereco salvo = enderecoService.salvar(endereco);

        // Exclui o endereço
        enderecoService.excluir(salvo.getId());

        // Verifica se o endereço foi excluído
        Assertions.assertTrue(enderecoService.buscarPorId(salvo.getId()).isEmpty(), "Endereço não foi excluído");
    }
}
