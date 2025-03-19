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
        String enderecoJson = "{\"logradouro\":\"Rua das Flores\",\"numero\":\"123\",\"cep\":\"12345-678\",\"cidade\":\"São Paulo\",\"estado\":\"SP\"}";

        mockMvc.perform(post("/enderecos")
                        .contentType(APPLICATION_JSON)
                        .content(enderecoJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarEnderecos() throws Exception {
        mockMvc.perform(get("/enderecos"))
                .andExpect(status().isOk());
    }
}