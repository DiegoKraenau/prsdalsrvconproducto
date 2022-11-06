package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RegisterPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

public interface PlanRequirementMaterialService {

    PlanRequirementMaterialEntity registerPlanRequirementMaterial(RegisterPlanRequirementMaterialDto registerDto)
            throws SqlException, Exception;

    List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterials() throws SqlException, Exception;
}
