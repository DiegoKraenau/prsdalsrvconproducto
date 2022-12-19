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
public class RequestEditPlanRequirementMaterialDto extends RequestEditAuditDto {

    private Integer cantidad;

    private Boolean flgAnulado;
}
