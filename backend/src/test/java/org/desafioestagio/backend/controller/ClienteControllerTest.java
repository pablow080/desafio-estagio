package org.desafioestagio.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCriarCliente() throws Exception {
        String clienteJson = "{\"tipoPessoa\":\"Fisica\"," +
                "\"cpfCnpj\":\"12345678901\"," +
                "\"nome\":\"Maria Silva\"," +
                "\"email\":\"maria@email.com\"," +
                "\"ativo\":true," +
                "\"razaoSocial\":\"Maria Silva\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isCreated()) // Verifica se o status HTTP é 201 CREATED
                .andExpect(jsonPath("$.id").exists()); // Verifica se o ID do cliente foi retornado
    }

    @Test
    public void testListarClientes() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk());
    }
}
