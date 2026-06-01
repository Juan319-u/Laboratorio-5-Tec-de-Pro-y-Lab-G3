package com.turisticas.rutas.controller;

import com.turisticas.rutas.model.Tipo;
import com.turisticas.rutas.repository.TipoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@Tag(name = "tipo-controlador", description = "CRUD de tipos de ruta")
public class TipoController {

    private final TipoRepository repo;

    public TipoController(TipoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todos los tipos")
    public List<Tipo> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tipo por ID")
    public ResponseEntity<Tipo> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Crear nuevo tipo")
    public ResponseEntity<Tipo> crear(@RequestBody Tipo tipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(tipo));
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar tipo existente")
    public ResponseEntity<Tipo> actualizar(@RequestBody Tipo tipo) {
        if (tipo.getId() == null || !repo.existsById(tipo.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(tipo));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
