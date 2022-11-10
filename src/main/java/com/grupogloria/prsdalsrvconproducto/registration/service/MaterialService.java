package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;

public interface MaterialService {
    List<ResponseMaterialDto> getAllMaterials() throws SqlException, Exception;
}
