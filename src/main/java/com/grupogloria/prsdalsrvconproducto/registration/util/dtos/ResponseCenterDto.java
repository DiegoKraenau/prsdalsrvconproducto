package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.util.List;

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
public class ResponseCenterDto {

    private String idCentro;

    private String centro;

    private List<ResponseMaterialDto> materiales;
}
