package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseLineDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;

public interface MaterialService {
    List<ResponseMaterialDto> getAllMaterials(HeaderRequest headers) throws Exception;

    ResponseMaterialDto getMaterialById(String id, HeaderRequest headers) throws Exception;

    List<ResponseLineDto> getLinesByMaterialId(String id, HeaderRequest headers) throws Exception;
}
