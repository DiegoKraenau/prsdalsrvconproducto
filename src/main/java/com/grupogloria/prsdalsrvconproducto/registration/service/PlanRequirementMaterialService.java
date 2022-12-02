package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.EditPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

public interface PlanRequirementMaterialService {

        PlanRequirementMaterialEntity registerPlanRequirementMaterial(RequestPlanRequirementMaterialDto registerDto)
                        throws Exception;

        List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterials() throws Exception;

        List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterialsByFilters(String fechaInicio,
                        String fechaFin, String centroId)
                        throws Exception;

        ResponsePlanRequirementMaterialDto updatePlanRequirementMaterial(
                        EditPlanRequirementMaterialDto updateDto)
                        throws Exception;
}
