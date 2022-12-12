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
public class LineMaterialId implements Serializable {

    private String centro;

    private String material;

    private Long linea;

}
