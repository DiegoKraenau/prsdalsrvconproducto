package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.PlanRequirementMaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "Plan Requirement Material API")
public class PlanRequirementMaterialController {

    @Autowired
    private PlanRequirementMaterialService planRequirementMaterialService;

    @LogMethodCall
    @PostMapping("/plan-requirement-material")
    public CustomResponse<PlanRequirementMaterialEntity> registerPlanRequirementMaterial(
            @Valid @RequestBody RequestPlanRequirementMaterialDto dto,
            HttpServletRequest request, HttpServletResponse response)
            throws SqlException, Exception {
        PlanRequirementMaterialEntity planRequirementMaterialRegistered;
        try {
            planRequirementMaterialRegistered = planRequirementMaterialService.registerPlanRequirementMaterial(dto);
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
                "Registro de Plan Requerimiento Material",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                planRequirementMaterialRegistered);
    }

    @LogMethodCall
    @GetMapping("/plan-requirement-material/find-all")
    public CustomResponse<List<ResponsePlanRequirementMaterialDto>> getPlanRequirementMaterials(
            HttpServletRequest request, HttpServletResponse response)
            throws SqlException, Exception {
        List<ResponsePlanRequirementMaterialDto> planRequirementMaterials;
        try {
            planRequirementMaterials = planRequirementMaterialService.getAllPlanRequirementMaterials();
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
                "Listado de Plan Requerimiento Material",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                planRequirementMaterials);
    }

    @LogMethodCall
    @GetMapping("/plan-requirement-material/find-by-filters")
    public CustomResponse<List<ResponsePlanRequirementMaterialDto>> getPlanRequirementMaterialsByFilters(
            @RequestParam(name = "fechaInicio") String startDate,
            @RequestParam(name = "fechaFin") String endDate,
            HttpServletRequest request, HttpServletResponse response)
            throws SqlException, Exception {
        List<ResponsePlanRequirementMaterialDto> planRequirementMaterials;
        try {
            planRequirementMaterials = planRequirementMaterialService.getAllPlanRequirementMaterialsByFilters(startDate,
                    endDate);
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
                "Listado de Plan Requerimiento Material con filtros",
                request.getHeader(GlobalConstants.ID_TRANSACTION),
                Util.getDate(),
                planRequirementMaterials);
    }

}
