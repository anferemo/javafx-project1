/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andrusoft.planning.product.app.mapper;

import com.andrusoft.planning.product.app.model.Inventory;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

/**
 *
 * @author andrusoft
 */
public interface InventoryMapper {
    @Update({
        "create table if not exists inventory(fecha_cargue Date not null, referencia double precision not null, producto varchar(128) not null, tamano_lote double precision, existencia double precision, inv_minimo double precision, expor double precision, ace double precision, programacion double precision, indicador_1 double precision, mes double precision)"
    })
    void createTable();
    
    @Update({
        "alter table if exists inventory add primary key(fecha_cargue, referencia)"
    })
    void createPrimaryKey();
    
    @Insert({
        "insert into inventory(fecha_cargue, referencia, producto, tamano_lote, existencia, inv_minimo, expor, ace, programacion, indicador_1, mes)",
        "values (#{fechaCarga}, #{referencia}, #{nombreProducto}, #{tamanoLote}, #{existencia}, #{inventarioMinimo}, #{expor}, #{ace}, #{programacion}, #{indicador1}, #{mes})"
    })
    int addInventoryData(Inventory inventory);
    
    @Delete({
        "delete from inventory"
    })
    int deleteAll();
    
    @Delete({
        "delete from inventory where fecha_cargue = #{fecha}"
    })
    int deleteByDate(LocalDate fecha);
    
    @Select({
        "select * from inventory where fecha_cargue= #{fecha} order by producto asc"
    })
    @Results({
        @Result(column = "fecha_cargue", property = "fechaCarga", jdbcType = JdbcType.DATE),
        @Result(column = "referencia", property = "referencia", jdbcType = JdbcType.DOUBLE),
        @Result(column = "producto", property = "nombreProducto", jdbcType = JdbcType.VARCHAR),
        @Result(column = "tamano_lote", property = "tamanoLote", jdbcType = JdbcType.DOUBLE),
        @Result(column = "existencia", property = "existencia", jdbcType = JdbcType.DOUBLE),
        @Result(column = "inv_minimo", property = "inventarioMinimo", jdbcType = JdbcType.DOUBLE),
        @Result(column = "expor", property = "expor", jdbcType = JdbcType.DOUBLE),
        @Result(column = "ace", property = "ace", jdbcType = JdbcType.DOUBLE),
        @Result(column = "programacion", property = "programacion", jdbcType = JdbcType.DOUBLE),
        @Result(column = "indicador_1", property = "indicador1", jdbcType = JdbcType.DOUBLE),
        @Result(column = "mes", property = "mes", jdbcType = JdbcType.DOUBLE)
    })
    List<Inventory> getInventoryByDate(LocalDate fecha);
    
    @Select({        
        "select * from inventory where producto like #{fecha} order by producto asc, fecha_cargue asc"    
    })
    @Results({
        @Result(column = "fecha_cargue", property = "fechaCarga", jdbcType = JdbcType.DATE),
        @Result(column = "referencia", property = "referencia", jdbcType = JdbcType.DOUBLE),
        @Result(column = "producto", property = "nombreProducto", jdbcType = JdbcType.VARCHAR),
        @Result(column = "tamano_lote", property = "tamanoLote", jdbcType = JdbcType.DOUBLE),
        @Result(column = "existencia", property = "existencia", jdbcType = JdbcType.DOUBLE),
        @Result(column = "inv_minimo", property = "inventarioMinimo", jdbcType = JdbcType.DOUBLE),
        @Result(column = "expor", property = "expor", jdbcType = JdbcType.DOUBLE),
        @Result(column = "ace", property = "ace", jdbcType = JdbcType.DOUBLE),
        @Result(column = "programacion", property = "programacion", jdbcType = JdbcType.DOUBLE),
        @Result(column = "indicador_1", property = "indicador1", jdbcType = JdbcType.DOUBLE),
        @Result(column = "mes", property = "mes", jdbcType = JdbcType.DOUBLE)
    })
    List<Inventory> getInventoryByProduct(String nombreProducto);
}
