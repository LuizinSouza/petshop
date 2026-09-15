package com.example.petshop.repository;

import com.example.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    // Herdando de JpaRepository, o Spring já cria automaticamente os métodos:
    // - save() (cadastrar/atualizar)
    // - findAll() (listar todos)
    // - findById() (buscar por id)
    // - deleteById() (excluir por id)
}