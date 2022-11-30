package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final Logger logger = LogManager.getLogger(MaterialController.class);

    @LogMethodCall
    @GetMapping("/material/find-all")
    public CustomResponse<List<ResponseMaterialDto>> getMaterials(HttpServletRequest request,
            HttpServletResponse response)
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
                    request.getHeader(GlobalConstants.ID_TRANSACTION),
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Listado de Material",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                materials);
    }

    @LogMethodCall
    @GetMapping("/material/{id}")
    public CustomResponse<ResponseMaterialDto> getMaterialById(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") String id)
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
                    request.getHeader(GlobalConstants.ID_TRANSACTION),
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Material identificado por Id",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                material);
    }

    @LogMethodCall
    @GetMapping("/test")
    public String Test() {
        logger.trace("FALLANDO TRACE");
        logger.warn("FALLANDO WARN");
        logger.error("FALLANDO ERROR");
        return "Hola";
    }

}
