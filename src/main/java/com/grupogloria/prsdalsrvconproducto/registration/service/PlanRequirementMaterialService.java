package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestEditPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

public interface PlanRequirementMaterialService {

        PlanRequirementMaterialEntity registerPlanRequirementMaterial(RequestPlanRequirementMaterialDto registerDto,
                        HeaderRequest headers)
                        throws Exception;

        List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterials(HeaderRequest headers) throws Exception;

        List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterialsByFilters(String fechaInicio,
                        String fechaFin, String centroId, HeaderRequest headers)
                        throws Exception;

        ResponsePlanRequirementMaterialDto updatePlanRequirementMaterial(
                        RequestEditPlanRequirementMaterialDto updateDto, HeaderRequest headers)
                        throws Exception;
}
