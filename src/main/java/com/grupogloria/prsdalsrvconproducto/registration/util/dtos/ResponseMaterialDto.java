package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMaterialDto {

    private Long id;

    private String largeName;

    private String shortName;

    private String presentation;

    private Boolean canceledFlag;

    private Integer expiredDays;

    private String maturityType;

    private ResponseMaterialCategoryDto materialCategory;
}
