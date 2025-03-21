package org.desafioestagio.backend.controller;

import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Validated
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Listar todos os endereços
    @GetMapping
    public List<Endereco> listarTodos() {
        return enderecoService.listarTodos();
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody @Valid Endereco endereco) {
        Endereco salvo = enderecoService.salvar(endereco);
        return ResponseEntity.status(201).body(salvo); // Retorna status 201 e o objeto salvo
    }

    // Excluir um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        enderecoService.excluirEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
