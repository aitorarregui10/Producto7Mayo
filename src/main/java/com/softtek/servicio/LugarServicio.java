package com.softtek.servicio;

import com.softtek.modelo.Lugar;
import com.softtek.repo.IGenericoRepositorio;
import com.softtek.repo.ILugarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public  class LugarServicio extends CRUDImpl<Lugar, Integer> implements ILugarServicio{
    @Autowired
    private ILugarRepo repo;
    @Override
    protected IGenericoRepositorio<Lugar, Integer> getRepo(){return repo;}
}
