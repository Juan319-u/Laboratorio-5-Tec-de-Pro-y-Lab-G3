package com.turisticas.rutas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pais")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo_alfa2", length = 2)
    private String codigoAlfa2;
}
