package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePlanRequirementMaterialDto {

    private Long productoId;

    private String nombreProducto;

    private String familiaProducto;

    private Integer cantidad;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp fecha;

    private String centroId;

    private String nombreCentro;

    private String unidadMedida;
}
