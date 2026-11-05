/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.andrusoft.planning.product.app.mapper;

import com.andrusoft.planning.product.app.model.Lote;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

/**
 *
 * @author andrusoft
 */
public interface LoteMapper {
 
    @Select({
            "select * from lote"
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
}
