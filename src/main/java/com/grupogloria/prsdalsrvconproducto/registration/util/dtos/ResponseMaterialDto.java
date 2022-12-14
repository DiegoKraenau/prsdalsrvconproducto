package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ResponseMaterialDto {

    private String id;

    private String nombreLargo;

    private String nombreCorto;

    private String idMaterialCs;

    private String presentacion;

    private Integer diasVencimiento;

    private String tipoVencimiento;

    private Boolean flgAnulado;

    private ResponseMaterialCategoryDto categoriaMaterial;

    private List<UnitMeasureDto> unidadMedidas;
}
