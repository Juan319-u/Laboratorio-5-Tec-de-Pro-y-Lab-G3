package com.turisticas.rutas.controller;

import com.turisticas.rutas.model.Pais;
import com.turisticas.rutas.repository.PaisRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
@Tag(name = "pais-controlador", description = "CRUD de países")
public class PaisController {

    private final PaisRepository repo;

    public PaisController(PaisRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todos los países")
    public List<Pais> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener país por ID")
    public ResponseEntity<Pais> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Crear nuevo país")
    public ResponseEntity<Pais> crear(@RequestBody Pais pais) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(pais));
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar país existente")
    public ResponseEntity<Pais> actualizar(@RequestBody Pais pais) {
        if (pais.getId() == null || !repo.existsById(pais.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(pais));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar país por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
