/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andrusoft.planning.product.app.mapper;

import com.andrusoft.planning.product.app.model.Lote;
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
public interface LoteMapper {
    
    @Update({
        "create table if not exists lote(codigo varchar(64) primary key, producto varchar(128) not null, cantidad_programada long, fecha_envase Date, fecha_aprobacion_mb Date, fecha_emision_op Date, cantidad_entregada long, observaciones varchar(128), estado_origen varchar(128))"
    })
    void createTable();
 
    @Select({
            "select * from lote order by fecha_envase desc"
    })
    @Results({
        @Result(column = "codigo", property = "codigo", jdbcType = JdbcType.VARCHAR),
        @Result(column = "producto", property = "producto", jdbcType = JdbcType.VARCHAR),
        @Result(column = "cantidad_programada", property = "cantidadProgramada", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "fecha_envase", property = "fechaEnvase", jdbcType = JdbcType.DATE),
        @Result(column = "fecha_aprobacion_mb", property = "fechaAprobacionMB", jdbcType = JdbcType.DATE),
        @Result(column = "fecha_emision_op", property = "fechaEmisionOP", jdbcType = JdbcType.DATE),
        @Result(column = "cantidad_entregada", property = "cantidadEntregada", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "observaciones", property = "observaciones", jdbcType = JdbcType.VARCHAR),
        @Result(column = "estado_origen", property = "estadoOrigen", jdbcType = JdbcType.VARCHAR),
    })        
    List<Lote> getAllLotes();
    
    
    @Select({
            "select * from lote where producto like #{prodName}"
    })
    @Results({
        @Result(column = "codigo", property = "codigo", jdbcType = JdbcType.VARCHAR),
        @Result(column = "producto", property = "producto", jdbcType = JdbcType.VARCHAR),
        @Result(column = "cantidad_programada", property = "cantidadProgramada", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "fecha_envase", property = "fechaEnvase", jdbcType = JdbcType.DATE),
        @Result(column = "fecha_aprobacion_mb", property = "fechaAprobacionMB", jdbcType = JdbcType.DATE),
        @Result(column = "fecha_emision_op", property = "fechaEmisionOP", jdbcType = JdbcType.DATE),
        @Result(column = "cantidad_entregada", property = "cantidadEntregada", jdbcType = JdbcType.LONGVARCHAR),
        @Result(column = "observaciones", property = "observaciones", jdbcType = JdbcType.VARCHAR),
        @Result(column = "estado_origen", property = "estadoOrigen", jdbcType = JdbcType.VARCHAR),
    })        
    List<Lote> getAllLotesByProductName(String prodName);
    
    @Delete({
        "delete from lote"
    })
    int deleteAll();
    
    @Insert({
        "INSERT INTO lote(codigo, producto, cantidad_programada, fecha_envase, fecha_aprobacion_mb, fecha_emision_op, cantidad_entregada, observaciones, estado_origen) "
                + "values(#{codigo}, #{producto},#{cantidadProgramada}, #{fechaEnvase}, #{fechaAprobacionMB}, #{fechaEmisionOP}, #{cantidadEntregada}, #{observaciones},#{estadoOrigen})"
    })
    int addLote(Lote lote);
}
