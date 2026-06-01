package com.turisticas.rutas.repository;

import com.turisticas.rutas.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    List<Ruta> findByCiudadId(Long idCiudad);
}
