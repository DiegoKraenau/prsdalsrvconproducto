package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.service.PlanRequirementMaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.EditPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Plan Requirement Material API")
public class PlanRequirementMaterialController {

        @Autowired
        private PlanRequirementMaterialService planRequirementMaterialService;

        @PostMapping("/plan-requirement-material")
        public CustomResponse<PlanRequirementMaterialEntity> registerPlanRequirementMaterial(
                        @Valid @RequestBody RequestPlanRequirementMaterialDto dto,
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(required = false, defaultValue = "PE") String pais,
                        @RequestHeader(required = false, defaultValue = "Gloria") String empresa,
                        @RequestHeader(required = false, defaultValue = "Linea") String division,
                        @RequestHeader(required = false, defaultValue = "01") String idTransaccion,
                        @RequestHeader(required = false, defaultValue = "prueba") String aplicacion,
                        @RequestHeader(required = false, defaultValue = "pruebaUser") String usuarioAplicacion,
                        @RequestHeader(required = false) @DateTimeFormat(pattern = GlobalConstants.SIMPLE_DATE_FORMAT) Date fechaEjecucion)
                        throws Exception {
                HeaderRequest header = new HeaderRequest(pais, empresa, division, idTransaccion, aplicacion,
                                usuarioAplicacion,
                                fechaEjecucion);
                PlanRequirementMaterialEntity planRequirementMaterialRegistered;
                try {
                        planRequirementMaterialRegistered = planRequirementMaterialService
                                        .registerPlanRequirementMaterial(dto, header);
                } catch (Exception e) {
                        response.setStatus(GlobalConstants.INTERNAL_ERROR);
                        return new CustomResponse<>(
                                        GlobalConstants.INTERNAL_ERROR,
                                        Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                                        Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR,
                                                        e.getMessage()),
                                        Util.getAllHeaders(request),
                                        Util.getDate());
                }
                return new CustomResponse<>(
                                GlobalConstants.OK,
                                Util.getStatusCode(GlobalConstants.OK),
                                "Registro de Plan Requerimiento Material",
                                Util.getAllHeaders(request),
                                Util.getDate(),
                                planRequirementMaterialRegistered);
        }

        @GetMapping("/plan-requirement-material/find-all")
        public CustomResponse<List<ResponsePlanRequirementMaterialDto>> getPlanRequirementMaterials(
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(required = false, defaultValue = "PE") String pais,
                        @RequestHeader(required = false, defaultValue = "Gloria") String empresa,
                        @RequestHeader(required = false, defaultValue = "Linea") String division,
                        @RequestHeader(required = false, defaultValue = "01") String idTransaccion,
                        @RequestHeader(required = false, defaultValue = "prueba") String aplicacion,
                        @RequestHeader(required = false, defaultValue = "pruebaUser") String usuarioAplicacion,
                        @RequestHeader(required = false) @DateTimeFormat(pattern = GlobalConstants.SIMPLE_DATE_FORMAT) Date fechaEjecucion)
                        throws Exception {
                HeaderRequest header = new HeaderRequest(pais, empresa, division, idTransaccion, aplicacion,
                                usuarioAplicacion,
                                fechaEjecucion);
                List<ResponsePlanRequirementMaterialDto> planRequirementMaterials;
                try {
                        planRequirementMaterials = planRequirementMaterialService
                                        .getAllPlanRequirementMaterials(header);
                } catch (Exception e) {
                        response.setStatus(GlobalConstants.INTERNAL_ERROR);
                        return new CustomResponse<>(
                                        GlobalConstants.INTERNAL_ERROR,
                                        Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                                        Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR,
                                                        e.getMessage()),
                                        Util.getAllHeaders(request),
                                        Util.getDate());
                }
                return new CustomResponse<>(
                                GlobalConstants.OK,
                                Util.getStatusCode(GlobalConstants.OK),
                                "Listado de Plan Requerimiento Material",
                                Util.getAllHeaders(request),
                                Util.getDate(),
                                planRequirementMaterials);
        }

        @GetMapping("/plan-requirement-material/find-by-filters")
        public CustomResponse<List<ResponsePlanRequirementMaterialDto>> getPlanRequirementMaterialsByFilters(
                        @RequestParam(name = "fechaInicio") String startDate,
                        @RequestParam(name = "fechaFin") String endDate,
                        @RequestParam(name = "centro") String centroId,
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(required = false, defaultValue = "PE") String pais,
                        @RequestHeader(required = false, defaultValue = "Gloria") String empresa,
                        @RequestHeader(required = false, defaultValue = "Linea") String division,
                        @RequestHeader(required = false, defaultValue = "01") String idTransaccion,
                        @RequestHeader(required = false, defaultValue = "prueba") String aplicacion,
                        @RequestHeader(required = false, defaultValue = "pruebaUser") String usuarioAplicacion,
                        @RequestHeader(required = false) @DateTimeFormat(pattern = GlobalConstants.SIMPLE_DATE_FORMAT) Date fechaEjecucion)
                        throws Exception {
                HeaderRequest header = new HeaderRequest(pais, empresa, division, idTransaccion, aplicacion,
                                usuarioAplicacion,
                                fechaEjecucion);
                List<ResponsePlanRequirementMaterialDto> planRequirementMaterials;
                try {
                        planRequirementMaterials = planRequirementMaterialService
                                        .getAllPlanRequirementMaterialsByFilters(startDate,
                                                        endDate, centroId, header);
                } catch (Exception e) {
                        response.setStatus(GlobalConstants.INTERNAL_ERROR);
                        return new CustomResponse<>(
                                        GlobalConstants.INTERNAL_ERROR,
                                        Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                                        Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR,
                                                        e.getMessage()),
                                        Util.getAllHeaders(request),
                                        Util.getDate());
                }
                return new CustomResponse<>(
                                GlobalConstants.OK,
                                Util.getStatusCode(GlobalConstants.OK),
                                "Listado de Plan Requerimiento Material con filtros",
                                Util.getAllHeaders(request),
                                Util.getDate(),
                                planRequirementMaterials);
        }

        @PutMapping("/plan-requirement-material/complex-update")
        public CustomResponse<ResponsePlanRequirementMaterialDto> updatePlanRequirementMaterial(
                        @Valid @RequestBody EditPlanRequirementMaterialDto dto,
                        HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(required = false, defaultValue = "PE") String pais,
                        @RequestHeader(required = false, defaultValue = "Gloria") String empresa,
                        @RequestHeader(required = false, defaultValue = "Linea") String division,
                        @RequestHeader(required = false, defaultValue = "01") String idTransaccion,
                        @RequestHeader(required = false, defaultValue = "prueba") String aplicacion,
                        @RequestHeader(required = false, defaultValue = "pruebaUser") String usuarioAplicacion,
                        @RequestHeader(required = false) @DateTimeFormat(pattern = GlobalConstants.SIMPLE_DATE_FORMAT) Date fechaEjecucion) {
                HeaderRequest header = new HeaderRequest(pais, empresa, division, idTransaccion, aplicacion,
                                usuarioAplicacion,
                                fechaEjecucion);
                ResponsePlanRequirementMaterialDto planRequirementMaterial;
                try {
                        planRequirementMaterial = planRequirementMaterialService.updatePlanRequirementMaterial(dto,
                                        header);
                } catch (Exception e) {
                        response.setStatus(GlobalConstants.INTERNAL_ERROR);
                        return new CustomResponse<>(
                                        GlobalConstants.INTERNAL_ERROR,
                                        Util.getStatusCode(GlobalConstants.INTERNAL_ERROR),
                                        Util.getStatusCodeErrorDescription(GlobalConstants.INTERNAL_ERROR,
                                                        e.getMessage()),
                                        Util.getAllHeaders(request),
                                        Util.getDate());
                }
                return new CustomResponse<>(
                                GlobalConstants.OK,
                                Util.getStatusCode(GlobalConstants.OK),
                                "Plan Requerimiento Material actualizado",
                                Util.getAllHeaders(request),
                                Util.getDate(),
                                planRequirementMaterial);
        }

}
