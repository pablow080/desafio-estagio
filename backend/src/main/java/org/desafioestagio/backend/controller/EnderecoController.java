package org.desafioestagio.backend.controller;

import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    // Listar todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        List<Endereco> enderecos = enderecoService.listarTodos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    // Buscar endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        Optional<Endereco> endereco = enderecoService.buscarPorId(id);
        return endereco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Salvar endereço
    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        try {
            Endereco enderecoSalvo = enderecoService.salvar(endereco);
            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Exemplo de erro de validação
        }
    }

    // Excluir endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        enderecoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
