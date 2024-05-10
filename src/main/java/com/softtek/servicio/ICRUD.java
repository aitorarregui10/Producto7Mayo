package com.softtek.servicio;

import java.util.List;

public interface ICRUD <T,ID> {
    T crear (T t);
    T modificar (T t);
    void eliminar (ID id);
    T consultaUno(ID id);
    List<T> consultarTodos();
}
