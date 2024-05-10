package com.softtek.repo;

import com.softtek.modelo.Lugar;
import com.softtek.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IProductoRepo extends IGenericoRepositorio<Producto, Integer>  {
}