package com.softtek.dto;


import com.softtek.modelo.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {
    private int product_id;
    @NotEmpty
    @Size(min = 3, max=70)
    private String nombreProducto;
    @NotNull
    @Min(0)
    @Max(50000)
    private double precioUnitario;
    @NotNull
    @Min(0)
    private int unidadesStock;
    @NotNull
    @Min(0)
    @Max(100)
    private int categoria;

    public Producto castProducto(){
        Producto p1 = new Producto();
        p1.setProduct_id(product_id);
        p1.setNombreProducto(nombreProducto);
        p1.setPrecioUnitario(precioUnitario);
        p1.setUnidadesStock(unidadesStock);
        p1.setCategoria(categoria);
        return p1;
    }

    public ProductoDTO castProductoADto(Producto p){
        product_id = p.getProduct_id();
        nombreProducto = p.getNombreProducto();
        precioUnitario = p.getPrecioUnitario();
        unidadesStock = p.getUnidadesStock();
        categoria = p.getCategoria();
        return this;
    }
}
