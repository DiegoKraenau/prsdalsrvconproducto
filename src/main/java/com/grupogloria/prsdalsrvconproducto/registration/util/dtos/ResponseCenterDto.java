package com.grupogloria.prsdalsrvconproducto.registration.util.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCenterDto {

    private String idCenter;

    private String center;

    private List<ResponseMaterialDto> materials;
}
