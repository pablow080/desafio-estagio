package org.desafioestagio.backend;

import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.desafioestagio.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criando um cliente manualmente
        Cliente cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("12345678909");  // CPF válido
        cliente.setNome("Cliente Manual");
        cliente.setEmail("cliente@teste.com");

        // Criando um endereço
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Logradouro Exemplo");
        endereco.setNumero("123");
        endereco.setBairro("Bairro Exemplo");
        endereco.setCidade("Cidade Exemplo");
        endereco.setEstado("Estado Exemplo");
        endereco.setCep("12345678");

        // Associando o endereço ao cliente
        cliente.addEndereco(endereco);

        // Salvando o cliente com o endereço
        clienteRepository.save(cliente);
    }
}
