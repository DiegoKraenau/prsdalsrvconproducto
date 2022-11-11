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

    private Long productId;

    private String productName;

    private String productFamily;

    private Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp date;

    private String centerId;

    private String centerName;
}
