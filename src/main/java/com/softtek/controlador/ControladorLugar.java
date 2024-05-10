package com.softtek.controlador;


import com.softtek.dto.LugarDTO;
import com.softtek.dto.ProductoDTO;
import com.softtek.excepciones.ExcepcionPersonalizadaNoEncontrado;
import com.softtek.modelo.Lugar;
import com.softtek.modelo.Producto;
import com.softtek.servicio.ILugarServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lugares")
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorLugar {
    @Autowired
    private ILugarServicio servicio;
    @GetMapping
    public ResponseEntity<List<LugarDTO>> consultarTodos() {
        List<Lugar> lugares = servicio.consultarTodos();
        List<LugarDTO> lugarsDto = new ArrayList<>();
        for (Lugar elemento:
                lugares) {
            LugarDTO lDto = new LugarDTO();
            lugarsDto.add(lDto.castLugarADto(elemento));
        }
        return new ResponseEntity<>(lugarsDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LugarDTO> consultaUno(@PathVariable(name="id") Integer id) {
        Lugar l1 = servicio.consultaUno(id);
        if(l1 == null) {
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO CON EL ID :" + id);
        }
        return new ResponseEntity<>((new LugarDTO()).castLugarADto(l1),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LugarDTO> crear(@Valid @RequestBody LugarDTO l) {
        Lugar l1 = l.castLugar();
        l1 = servicio.crear(l1);
        return new ResponseEntity<>(l.castLugarADto(l1), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name="id") Integer id) {
        Lugar l1 = servicio.consultaUno(id);
        if (l1 == null){
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO CON EL ID :" + id);
        }
        servicio.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<LugarDTO> modificar(@Valid @RequestBody LugarDTO l) {

        Lugar l1 = servicio.consultaUno(l.getIdLugar());
        if (l1 == null){
            throw new ExcepcionPersonalizadaNoEncontrado("EMPLEADO NO ENCONTRADO " + l.getIdLugar());
        }
        l1 = servicio.modificar(l.castLugar());
        return new ResponseEntity<>(l.castLugarADto(l1), HttpStatus.OK);
    }
}
