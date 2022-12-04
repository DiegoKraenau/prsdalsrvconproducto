package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
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

    @Autowired
    ObjectMapper objMapper;

    @Override
    public List<ResponseMaterialCategoryDto> getAllCategories(HeaderRequest headers) throws Exception {
        try {
            log.info(
                    GlobalConstants.HEADER + " - " + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                            + headers.getIdTransaccion() + ": {}",
                    objMapper.writeValueAsString(headers));
            List<MaterialCategoryEntity> categories = materialCategoryRepository.findAll();

            List<ResponseMaterialCategoryDto> response = categories
                    .stream()
                    .map(category -> modelMapper.map(category, ResponseMaterialCategoryDto.class))
                    .collect(Collectors.toList());

            log.info(
                    GlobalConstants.RESPONSE + " - " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                            " - " + headers.getIdTransaccion() + ": {}",
                    objMapper.writeValueAsString(response));
            return response;

        } catch (Exception e) {
            log.error(
                    GlobalConstants.ERROR + " - " + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                            + headers.getIdTransaccion() + ": {}",
                    objMapper.writeValueAsString(ManageError
                            .builder()
                            .idTransaccion(headers.getIdTransaccion())
                            .message(e.getMessage())
                            .build()));

            throw new Exception("Error de data. Por favor probar mas tarde.");
        }

    }
}
