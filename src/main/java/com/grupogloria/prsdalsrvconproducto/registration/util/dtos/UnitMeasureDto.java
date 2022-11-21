package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

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
public class UnitMeasureDto {

    private String idUnidadMedida;

    private String unidadMedida;

    private String unidadMedidaLarga;
}
