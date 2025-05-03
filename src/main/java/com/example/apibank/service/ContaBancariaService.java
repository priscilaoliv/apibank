package com.example.apibank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.apibank.model.ContaBancaria;
import com.example.apibank.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    private final ContaBancariaRepository repository;

    public ContaBancariaService(ContaBancariaRepository repository) {
        this.repository = repository;
    }

    public ContaBancaria salvar(ContaBancaria conta) {
        Optional<ContaBancaria> existente = repository.findByContaCorrente(conta.getContaCorrente());
        if (existente.isPresent()) {
            throw new RuntimeException("Conta já cadastrada.");
        }
        return repository.save(conta);
    }
    
    
    public List<ContaBancaria> listar() {
        return repository.findAll();
    }

    public Optional<ContaBancaria> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
 
