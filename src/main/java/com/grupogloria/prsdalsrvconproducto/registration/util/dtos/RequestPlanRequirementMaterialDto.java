package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RequestPlanRequirementMaterialDto extends RequestAuditDto {

    @NotNull
    private String materialId;

    @NotNull
    private Integer cantidad;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp fecha;

    @NotNull
    private String centroId;

    @NotNull
    private String unidadMedidaId;

}
