package org.desafioestagio.backend.controller;

import jakarta.validation.Valid;
import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente) {
        try {
            Cliente savedCliente = clienteService.salvar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
        } catch (ClienteJaCadastradoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listarTodos(Pageable pageable) {
        // Retorna a página de clientes com o tipo correto
        Page<Cliente> clientes = clienteService.listarTodos(pageable);
        return ResponseEntity.ok(clientes);  // Retorna a resposta com a página de clientes
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
