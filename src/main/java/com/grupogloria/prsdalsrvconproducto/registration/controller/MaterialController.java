package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Material API")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @LogMethodCall
    @GetMapping("/material/find-all")
    public CustomResponse<List<ResponseMaterialDto>> getMaterials(HttpServletResponse response,
            @RequestHeader(value = "idTransaccion") String idTransaccion)
            throws SqlException, Exception {
        List<ResponseMaterialDto> materials;
        try {
            materials = materialService.getAllMaterials();
        } catch (Exception e) {
            response.setStatus(GlobalConstants.INTERNAL_ERROR);
            return new CustomResponse<>(
                    GlobalConstants.INTERNAL_ERROR,
                    Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                    Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR, e.getMessage()),
                    idTransaccion,
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Listado de Material",
                idTransaccion,
                Util.getDate(),
                materials);
    }

    @LogMethodCall
    @GetMapping("/material/{id}")
    public CustomResponse<ResponseMaterialDto> getMaterialById(@PathVariable("id") String id,
            HttpServletResponse response,
            @RequestHeader(value = "idTransaccion") String idTransaccion)
            throws SqlException, Exception {
        ResponseMaterialDto material;
        try {
            material = materialService.getMaterialById(Long.parseLong(id));
        } catch (Exception e) {
            response.setStatus(503);
            return new CustomResponse<>(
                    GlobalConstants.INTERNAL_ERROR,
                    Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                    Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR, e.getMessage()),
                    idTransaccion,
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Material identificado por Id",
                idTransaccion,
                Util.getDate(),
                material);
    }

}
