package com.example.apibank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibank.model.ContaBancaria;
import com.example.apibank.service.ContaBancariaService;

@RestController
@RequestMapping("/api/contas")
public class ContaBancariaController {

    private final ContaBancariaService service;

    public ContaBancariaController(ContaBancariaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContaBancaria> listarContas() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContaBancaria criarConta(@RequestBody ContaBancaria conta) {
        return service.salvar(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> atualizarConta(@PathVariable Long id, @RequestBody ContaBancaria novaConta) {
        return service.buscarPorId(id)
                .map(contaExistente -> {
                    contaExistente.setNomeCliente(novaConta.getNomeCliente());
                    contaExistente.setContaCorrente(novaConta.getContaCorrente());
                    contaExistente.setSaldo(novaConta.getSaldo());
                    return ResponseEntity.ok(service.salvar(contaExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

