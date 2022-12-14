package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialCategoryDto;

public interface MaterialCategoryService {
    List<ResponseMaterialCategoryDto> getAllCategories() throws SqlException, Exception;
}
