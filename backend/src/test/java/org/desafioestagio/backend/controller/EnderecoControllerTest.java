package org.desafioestagio.backend.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCriarEndereco() throws Exception {
        String enderecoJson = "{\"logradouro\":\"Rua das Flores\"," +
                "\"numero\":\"123\"," +
                "\"cep\":\"12345-678\"," +
                "\"bairro\":\"Centro\"," +
                "\"cidade\":\"São Paulo\"," +
                "\"estado\":\"SP\"," +
                "\"enderecoPrincipal\":true," +
                "\"cliente\":{\"id\":1}}";

        mockMvc.perform(post("/enderecos")
                        .contentType(APPLICATION_JSON)
                        .content(enderecoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.logradouro").value("Rua das Flores"))
                .andExpect(jsonPath("$.numero").value("123"))
                .andExpect(jsonPath("$.cep").value("12345-678"))
                .andExpect(jsonPath("$.bairro").value("Centro"))
                .andExpect(jsonPath("$.cidade").value("São Paulo"))
                .andExpect(jsonPath("$.estado").value("SP"));
    }

    @Test
    public void testListarEnderecos() throws Exception {
        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].logradouro").exists()); // Verifica se ao menos um endereço foi retornado
    }
}
