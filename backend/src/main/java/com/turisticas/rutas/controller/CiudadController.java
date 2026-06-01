package com.turisticas.rutas.controller;

import com.turisticas.rutas.model.Ciudad;
import com.turisticas.rutas.repository.CiudadRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
@Tag(name = "ciudad-controlador", description = "CRUD de ciudades")
public class CiudadController {

    private final CiudadRepository repo;

    public CiudadController(CiudadRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todas las ciudades")
    public List<Ciudad> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ciudad por ID")
    public ResponseEntity<Ciudad> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @Operation(summary = "Crear nueva ciudad")
    public ResponseEntity<Ciudad> crear(@RequestBody Ciudad ciudad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(ciudad));
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar ciudad existente")
    public ResponseEntity<Ciudad> actualizar(@RequestBody Ciudad ciudad) {
        if (ciudad.getId() == null || !repo.existsById(ciudad.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(ciudad));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ciudad por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
