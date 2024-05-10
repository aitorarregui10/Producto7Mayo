package com.softtek.servicio;

import com.softtek.modelo.Lugar;
import com.softtek.modelo.Producto;
import com.softtek.repo.IGenericoRepositorio;
import com.softtek.repo.ILugarRepo;
import com.softtek.repo.IProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class ProductoServicio extends CRUDImpl<Producto, Integer> implements IProductoServicio{
        @Autowired
        private IProductoRepo repo;
        @Override
        protected IGenericoRepositorio<Producto, Integer> getRepo(){return repo;}
    }