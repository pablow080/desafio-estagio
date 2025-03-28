package org.desafioestagio.backend.controller;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import org.desafioestagio.backend.exception.ClienteJaCadastradoException;
import org.desafioestagio.backend.exception.ClienteNaoEncontradoException;
import org.desafioestagio.backend.model.Cliente;
import org.desafioestagio.backend.model.ErrorResponse;
import org.desafioestagio.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public String clientesPage(Model model) {
        return "clientes"; // Renderiza clientes.html
    }

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid Cliente cliente) {
        try {
            Cliente savedCliente = clienteService.salvar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
        } catch (ClienteJaCadastradoException e) {
            ErrorResponse errorResponse = new ErrorResponse("Cliente já cadastrado", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Cliente>> listarTodos(Pageable pageable) {
        Page<Cliente> clientes = clienteService.listarTodos(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.buscarPorId(id);
            return ResponseEntity.ok(cliente);
        } catch (ClienteNaoEncontradoException e) {
            ErrorResponse errorResponse = new ErrorResponse("Cliente não encontrado", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (ClienteNaoEncontradoException e) {
            ErrorResponse errorResponse = new ErrorResponse("Cliente não encontrado", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}
