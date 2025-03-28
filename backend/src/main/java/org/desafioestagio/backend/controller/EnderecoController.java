package org.desafioestagio.backend.controller;

import org.desafioestagio.backend.model.Endereco;
import org.desafioestagio.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

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
        Optional<Endereco> endereco = Optional.ofNullable(enderecoService.buscarPorId(id));
        return endereco.map(ResponseEntity::ok)  // Usando map no Optional
                .orElseGet(() -> ResponseEntity.notFound().build());  // Usando orElseGet para retornar 404
    }


    // Buscar endereço por nome do cliente
    @GetMapping("/nome/{nome}")
    public List<Endereco> buscarPorNomeCliente(@PathVariable String nome) {
        return enderecoService.buscarPorNomeCliente(nome);
    }

    // Buscar endereço por razão social do cliente (CNPJ)
    @GetMapping("/razao-social/{razaoSocial}")
    public List<Endereco> buscarPorRazaoSocialCliente(@PathVariable String razaoSocial) {
        return enderecoService.buscarPorRazaoSocialCliente(razaoSocial);
    }

    // Buscar endereço por CEP
    @GetMapping("/cep/{cep}")
    public Optional<Endereco> buscarPorCep(@PathVariable String cep) {
        return enderecoService.buscarPorCep(cep);
    }

    // Buscar endereço por email do cliente
    @GetMapping("/email/{email}")
    public List<Endereco> buscarPorEmailCliente(@PathVariable String email) {
        return enderecoService.buscarPorEmailCliente(email);
    }

    // Buscar endereço por telefone do cliente
    @GetMapping("/telefone/{telefone}")
    public List<Endereco> buscarPorTelefone(@PathVariable String telefone) {
        return enderecoService.buscarPorTelefone(telefone);
    }

    // Buscar endereço por nome do cliente e CEP
    @GetMapping("/nome-cep")
    public List<Endereco> buscarPorNomeECEP(@RequestParam String nome, @RequestParam String cep) {
        return enderecoService.buscarPorNomeECEP(nome, cep);
    }

    // Buscar endereço por nome do cliente e telefone
    @GetMapping("/nome-telefone")
    public List<Endereco> buscarPorNomeETelefone(@RequestParam String nome, @RequestParam String telefone) {
        return enderecoService.buscarPorNomeETelefone(nome, telefone);
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
