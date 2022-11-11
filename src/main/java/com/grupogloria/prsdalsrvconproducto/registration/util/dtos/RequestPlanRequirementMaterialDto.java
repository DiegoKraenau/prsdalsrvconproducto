package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestPlanRequirementMaterialDto extends RequestAuditDto {

    @NotNull
    private Long materialId;

    @NotNull
    private Integer amount;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp date;

    @NotNull
    private String centerId;

}
