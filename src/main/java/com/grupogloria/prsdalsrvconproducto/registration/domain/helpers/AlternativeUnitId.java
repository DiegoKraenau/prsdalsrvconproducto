package com.grupogloria.prsdalsrvconproducto.registration.domain.helpers;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeUnitId implements Serializable {

    private String unidadMedida;

    private Long material;
}
