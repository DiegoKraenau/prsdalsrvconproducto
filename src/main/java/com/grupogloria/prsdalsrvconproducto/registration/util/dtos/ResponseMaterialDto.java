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

    private String nombreLargo;

    private String nombreCorto;

    private String presentacion;

    private Boolean flgAnulado;

    private Integer diasVencimiento;

    private String tipoVencimiento;

    private ResponseMaterialCategoryDto categoriaMaterial;
}
