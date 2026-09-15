package com.example.petshop.controller;

import com.example.petshop.model.Animal;
import com.example.petshop.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalRepository repository;

    // 1. Cadastrar um animal
    @PostMapping
    public ResponseEntity<Animal> cadastrar(@RequestBody Animal animal) {
        Animal novoAnimal = repository.save(animal);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
    }

    // 2. Consulta de todos os animais
    @GetMapping
    public ResponseEntity<List<Animal>> listarTodos() {
        List<Animal> animais = repository.findAll();
        return ResponseEntity.ok(animais);
    }

    // 3. Consulta de um animal pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        Optional<Animal> animal = repository.findById(id);
        if (animal.isPresent()) {
            return ResponseEntity.ok(animal.get());
        }
        return ResponseEntity.notFound().build();
    }

    // 4. Alteração de um animal
    @PutMapping("/{id}")
    public ResponseEntity<Animal> atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        animalAtualizado.setId(id);
        Animal animalSalvo = repository.save(animalAtualizado);
        return ResponseEntity.ok(animalSalvo);
    }

    // 5. Exclusão de um animal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}