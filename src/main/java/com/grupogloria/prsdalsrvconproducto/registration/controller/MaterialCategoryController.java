package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialCategoryEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialCategoryService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Material Category API")
public class MaterialCategoryController {

    @Autowired
    private MaterialCategoryService materialCategoryService;

    @LogMethodCall
    @GetMapping("/material-category/find-all")
    public CustomResponse<List<MaterialCategoryEntity>> getCategories(HttpServletRequest request)
            throws SqlException, Exception {
        List<MaterialCategoryEntity> categories;
        try {
            categories = materialCategoryService.getAllCategories();
        } catch (Exception e) {
            return new CustomResponse<>(
                    GlobalConstants.INTERNAL_ERROR,
                    Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                    Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR, e.getMessage()),
                    request.getHeader(GlobalConstants.ID_TRANSACTION),
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Listado de categorias",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                categories);
    }

}