package com.softtek.dto;


import com.softtek.modelo.Lugar;
import com.softtek.modelo.Producto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LugarDTO {

    private int idLugar;
    @NotEmpty
    @Size(min = 3, max=70)
    private String nombre;
    @NotEmpty
    @Size(min = 3, max=600)
    private String descripcion;

    public Lugar castLugar(){
        Lugar l1 = new Lugar();
        l1.setIdLugar(idLugar);
        l1.setNombre(nombre);
        l1.setDescripcion(descripcion);
        return l1;
    }

    public LugarDTO castLugarADto(Lugar l){
        idLugar = l.getIdLugar();
        nombre = l.getNombre();
        descripcion = l.getDescripcion();
        return this;
    }
}
