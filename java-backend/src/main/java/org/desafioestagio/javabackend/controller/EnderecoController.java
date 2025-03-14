package org.desafioestagio.javabackend.controller;

import jakarta.validation.Valid;
import org.desafioestagio.javabackend.model.Endereco;
import org.desafioestagio.javabackend.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    // Buscar todos os endereços
    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodosEnderecos() {
        List<Endereco> enderecos = enderecoService.listarTodos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    // Buscar um endereço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarEndereco(@PathVariable Long id) {
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar um novo endereço
    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@Valid @RequestBody Endereco endereco) {
        Endereco enderecoSalvo = enderecoService.salvar(endereco);
        return ResponseEntity.created(URI.create("/enderecos/" + enderecoSalvo.getId())).body(enderecoSalvo);
    }

    // Atualizar um endereço existente
    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
        return ResponseEntity.ok(enderecoService.atualizar(id, endereco));
    }

    // Excluir um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long id) {
        enderecoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
