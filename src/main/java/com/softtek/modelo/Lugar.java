package com.softtek.modelo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Lugar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLugar;
    @Column(length = 60,nullable = false)
    private String nombre;
    @Column(length = 300,nullable = false)
    private String descripcion;

}
