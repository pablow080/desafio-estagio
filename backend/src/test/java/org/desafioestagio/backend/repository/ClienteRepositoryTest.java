package org.desafioestagio.backend.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.model.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll(); // Limpa os dados antes de cada teste

        cliente = new Cliente();
        cliente.setTipoPessoa(TipoPessoa.FISICA);
        cliente.setCpfCnpj("123.456.789-00");  // CPF
        cliente.setNome("João da Silva");
        cliente.setRg("12.345.678-9");
        cliente.setDataNascimento(LocalDate.of(1985, 5, 20));
        cliente.setEmail("joao.silva@email.com");
        cliente.setAtivo(true);

        cliente = clienteRepository.save(cliente); // Salva no banco antes dos testes
    }

    @Test
    void testExistsByCpfCnpj() {
        boolean exists = clienteRepository.existsByCpfCnpj(cliente.getCpfCnpj());
        assertTrue(exists);
    }

    @Test
    void testFindByCpfCnpj() {
        Cliente clienteEncontrado = clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());
        assertNotNull(clienteEncontrado);
        assertEquals(cliente.getCpfCnpj(), clienteEncontrado.getCpfCnpj());
    }
}
