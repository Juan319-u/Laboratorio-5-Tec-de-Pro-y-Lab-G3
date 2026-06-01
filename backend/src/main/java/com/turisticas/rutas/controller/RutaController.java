package com.turisticas.rutas.controller;

import com.turisticas.rutas.model.Ruta;
import com.turisticas.rutas.repository.RutaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@Tag(name = "ruta-controlador", description = "CRUD de rutas turísticas")
public class RutaController {

    private final RutaRepository repo;

    public RutaController(RutaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todas las rutas")
    public List<Ruta> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener ruta por ID")
    public ResponseEntity<Ruta> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudad/{idCiudad}")
    @Operation(summary = "Listar rutas por ciudad")
    public List<Ruta> porCiudad(@PathVariable Long idCiudad) {
        return repo.findByCiudadId(idCiudad);
    }

    @PostMapping("/")
    @Operation(summary = "Crear nueva ruta")
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(ruta));
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar ruta existente")
    public ResponseEntity<Ruta> actualizar(@RequestBody Ruta ruta) {
        if (ruta.getId() == null || !repo.existsById(ruta.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(ruta));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ruta por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
