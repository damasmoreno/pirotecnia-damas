package com.example.apipirotecnia.repository;

import com.example.apipirotecnia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }