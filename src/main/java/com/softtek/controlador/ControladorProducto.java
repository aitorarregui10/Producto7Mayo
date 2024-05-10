package com.softtek.controlador;


import com.softtek.dto.ProductoDTO;
import com.softtek.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.softtek.modelo.Producto;
import com.softtek.servicio.IProductoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorProducto {
    @Autowired
    private IProductoServicio servicio;
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> consultarTodos() {
        List<Producto> productos = servicio.consultarTodos();
        List<ProductoDTO> productosDto = new ArrayList<>();
        for (Producto elemento:
                productos) {
            ProductoDTO pDto = new ProductoDTO();
            productosDto.add(pDto.castProductoADto(elemento));
        }
        return new ResponseEntity<>(productosDto,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> consultaUno(@PathVariable(name="id") Integer id) {
        Producto p1 = servicio.consultaUno(id);
        if(p1 == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO CON EL ID :" + id);
        }
        return new ResponseEntity<>((new ProductoDTO()).castProductoADto(p1),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO p) {
        Producto p1 = p.castProducto();
        p1 = servicio.crear(p1);
        return new ResponseEntity<>(p.castProductoADto(p1), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") Integer id) {
        Producto p1 = servicio.consultaUno(id);
        if (p1 == null){
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO CON EL ID :" + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> modificar(@Valid @RequestBody ProductoDTO p) {

        Producto p1 = servicio.consultaUno(p.getProduct_id());
        if (p1 == null){
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO " + p.getProduct_id());
        }
        p1 = servicio.modificar(p.castProducto());
        return new ResponseEntity<>(p.castProductoADto(p1), HttpStatus.OK);
    }
}
