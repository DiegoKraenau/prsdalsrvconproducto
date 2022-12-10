package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ResponseLineDto {

    private Long idLinea;

    private String idCentro;

    private String pstTrbjo;

    private String idSeccionProduccion;

    private String linea;

    private String descripcion;

    private Float limiteSuperior;

    private Float limiteInferior;

    private Integer codificacion;

    private Boolean flg_reproceso;

    private Boolean flgAnulado;

    private Integer timeOutImpresion;
}
