package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkLogger;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialCategoryEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialCategoryRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialCategoryService;
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
    public List<ResponseMaterialCategoryDto> getAllCategories() throws SqlException, Exception {
        try {
            List<MaterialCategoryEntity> categories = materialCategoryRepository.findAll();

            return categories
                    .stream()
                    .map(category -> modelMapper.map(category, ResponseMaterialCategoryDto.class))
                    .collect(Collectors.toList());

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Connection Falied Please Try Later");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Data Issue Please Try Later");
        }
    }

}
