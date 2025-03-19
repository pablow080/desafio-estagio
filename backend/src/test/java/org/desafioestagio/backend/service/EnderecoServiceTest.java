package org.desafioestagio.backend.service;

import org.desafioestagio.backend.model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class EnderecoServiceTest{
@Autowired
private EnderecoService enderecoService;

@Test
public void testSalvarEndereco() {
    Endereco endereco = new Endereco();
    endereco.setLogradouro("Rua das Flores");
    endereco.setNumero("123");
    endereco.setCep("12345-678");
    endereco.setCidade("São Paulo");
    endereco.setEstado("SP");

    Endereco salvo = enderecoService.salvar(endereco);
    Assertions.assertNotNull(salvo.getId());
}
}
