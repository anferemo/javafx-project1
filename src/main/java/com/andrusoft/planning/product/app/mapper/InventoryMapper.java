/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andrusoft.planning.product.app.mapper;

import org.apache.ibatis.annotations.Update;

/**
 *
 * @author andrusoft
 */
public interface InventoryMapper {
    @Update({
        "create table if not exists inventory_prueba(fila double precision not null, fecha_cargue Date not null, referencia double precision, producto varchar(128) not null, tamano_lote double precision, existencia double precision, inv_minimo double precision, expor double precision, ace double precision)"
    })
    void createTable();
}
