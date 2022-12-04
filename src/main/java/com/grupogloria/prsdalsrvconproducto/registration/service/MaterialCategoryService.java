package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialCategoryDto;

public interface MaterialCategoryService {
    List<ResponseMaterialCategoryDto> getAllCategories(HeaderRequest headers) throws Exception;
}
