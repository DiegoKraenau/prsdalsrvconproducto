package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;

public interface MaterialService {
    List<MaterialEntity> getAllMaterials() throws SqlException, Exception;
}
