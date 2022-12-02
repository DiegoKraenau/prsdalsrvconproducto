package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialCategoryEntity;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialCategoryRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialCategoryService;
import com.grupogloria.prsdalsrvconproducto.registration.util.ManageError;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialCategoryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaterialCategoryServiceImpl implements MaterialCategoryService {

    @Autowired
    MaterialCategoryRepository materialCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ResponseMaterialCategoryDto> getAllCategories() throws Exception {
        try {
            List<MaterialCategoryEntity> categories = materialCategoryRepository.findAll();

            return categories
                    .stream()
                    .map(category -> modelMapper.map(category, ResponseMaterialCategoryDto.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            // log.error(GlobalConstants.ERROR + " - " +
            // Thread.currentThread().getStackTrace()[1].getMethodName(),
            // ManageError.builder().idTransaccion(headers.getIdTransaccion()));
            throw new Exception("Error de data. Por favor probar mas tarde.");
        }

    }
}
