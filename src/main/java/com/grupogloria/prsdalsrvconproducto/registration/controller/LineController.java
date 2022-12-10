package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.service.LineService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseLineDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Linea API")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping("/line/{id}")
    public CustomResponse<ResponseLineDto> getCenterById(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") Long id,
            @RequestHeader(required = false, defaultValue = "PE") String pais,
            @RequestHeader(required = false, defaultValue = "Gloria") String empresa,
            @RequestHeader(required = false, defaultValue = "Linea") String division,
            @RequestHeader(required = false, defaultValue = "01") String idTransaccion,
            @RequestHeader(required = false, defaultValue = "prueba") String aplicacion,
            @RequestHeader(required = false, defaultValue = "pruebaUser") String usuarioAplicacion,
            @RequestHeader(required = false) @DateTimeFormat(pattern = GlobalConstants.SIMPLE_DATE_FORMAT) Date fechaEjecucion) {
        HeaderRequest header = new HeaderRequest(pais, empresa, division, idTransaccion, aplicacion, usuarioAplicacion,
                fechaEjecucion);
        ResponseLineDto lineDto;
        try {
            lineDto = lineService.getLineByID(id, header);
        } catch (Exception e) {
            response.setStatus(GlobalConstants.INTERNAL_ERROR);
            return new CustomResponse<>(
                    GlobalConstants.INTERNAL_ERROR,
                    Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                    Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR, e.getMessage()),
                    Util.getAllHeaders(request),
                    Util.getDate());
        }
        return new CustomResponse<>(
                GlobalConstants.OK,
                Util.getStatusCode(GlobalConstants.OK),
                "Centro filtrado por Id",
                Util.getAllHeaders(request),
                Util.getDate(),
                lineDto);
    }
}
