package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialCategoryEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;

public interface MaterialCategoryService {
    List<MaterialCategoryEntity> getAllCategories() throws SqlException, Exception;
}
