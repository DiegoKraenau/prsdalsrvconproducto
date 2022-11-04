package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialCategoryEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialCategoryService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Poner descripción de la API")
public class MaterialCategoryController {

    @Autowired
    private MaterialCategoryService materialCategoryService;

    @LogMethodCall
    @GetMapping("/material-category/find-all")
    public List<MaterialCategoryEntity> getAllVisitors() throws SqlException, Exception {
        return materialCategoryService.getAllCategories();
    }

}
