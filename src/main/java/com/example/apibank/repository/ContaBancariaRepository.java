package com.example.apibank.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apibank.model.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    Optional<ContaBancaria> findByContaCorrente(String contaCorrente);

}

