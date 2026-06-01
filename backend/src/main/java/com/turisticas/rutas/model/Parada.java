package com.turisticas.rutas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parada")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false)
    private Integer orden;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    private Double longitud;
    private Double latitud;

    /** Tiempo de permanencia en minutos */
    private Integer tiempo;

    @Column(length = 500)
    private String descripcion;
}
