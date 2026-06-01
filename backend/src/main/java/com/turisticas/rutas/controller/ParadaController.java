package com.turisticas.rutas.controller;

import com.turisticas.rutas.model.Parada;
import com.turisticas.rutas.repository.ParadaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paradas")
@Tag(name = "parada-controlador", description = "CRUD de paradas de ruta")
public class ParadaController {

    private final ParadaRepository repo;

    public ParadaController(ParadaRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    @Operation(summary = "Listar todas las paradas")
    public List<Parada> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener parada por ID")
    public ResponseEntity<Parada> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ruta/{idRuta}")
    @Operation(summary = "Listar paradas de una ruta ordenadas por campo orden")
    public List<Parada> porRuta(@PathVariable Long idRuta) {
        return repo.findByRutaIdOrderByOrdenAsc(idRuta);
    }

    @PostMapping("/")
    @Operation(summary = "Crear nueva parada")
    public ResponseEntity<Parada> crear(@RequestBody Parada parada) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(parada));
    }

    @PutMapping("/")
    @Operation(summary = "Actualizar parada existente")
    public ResponseEntity<Parada> actualizar(@RequestBody Parada parada) {
        if (parada.getId() == null || !repo.existsById(parada.getId())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.save(parada));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar parada por ID")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
