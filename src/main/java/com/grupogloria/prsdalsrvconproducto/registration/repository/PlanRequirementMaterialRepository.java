package com.grupogloria.prsdalsrvconproducto.registration.repository;

import org.springframework.stereotype.Repository;

import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;

@Repository
public interface PlanRequirementMaterialRepository
        extends BaseRepository<PlanRequirementMaterialEntity, PlanRequirementMaterialId> {

}
