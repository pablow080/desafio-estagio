package org.desafioestagio.javabackend.controller;

import org.desafioestagio.javabackend.model.Cliente;
import org.desafioestagio.javabackend.model.Endereco;
import org.desafioestagio.javabackend.service.ClienteService;
import org.desafioestagio.javabackend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    // Endpoint para listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.listarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Endpoint para buscar cliente por id
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para salvar cliente
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvar(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    // Endpoint para atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
        if (clienteExistente.isPresent()) {
            cliente.setId(id);
            Cliente clienteAtualizado = clienteService.salvar(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint para excluir cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
        if (clienteExistente.isPresent()) {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint para adicionar um endereço a um cliente
    @PostMapping("/{clienteId}/enderecos")
    public ResponseEntity<Endereco> adicionarEndereco(@PathVariable Long clienteId, @RequestBody Endereco endereco) {
        Optional<Cliente> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isPresent()) {
            endereco.setCliente(cliente.get());
            Endereco enderecoSalvo = enderecoService.salvar(endereco);  // Chama o método correto
            return new ResponseEntity<>(enderecoSalvo, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
