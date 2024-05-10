package com.softtek.modelo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    @Column(length = 9,nullable = false, unique = true)
    private String nombreProducto;
    @Column(length = 60, nullable = false)
    private double precioUnitario;
    @Column(length = 60)
    private int unidadesStock;
    @Column(length = 60)
    private int categoria;

}