package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class EditPlanRequirementMaterialDto extends EditAuditDto {

    @NotNull
    private Long material;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp fecha;

    @NotNull
    private String centro;

    @NotNull
    private String unidadMedida;

    private Integer cantidad;

    private Boolean flgAnulado;
}
