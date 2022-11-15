package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.CenterService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseCenterDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Center API")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @LogMethodCall
    @GetMapping("/center/find-all")
    public CustomResponse<List<ResponseCenterDto>> getCenters(HttpServletRequest request)
            throws SqlException, Exception {
        List<ResponseCenterDto> centers;
        try {
            centers = centerService.getAllCenters();
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
                "Listado de Centros",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                centers);
    }
}
