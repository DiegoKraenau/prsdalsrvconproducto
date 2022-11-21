package com.grupogloria.prsdalsrvconproducto.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;

@Repository
public interface PlanRequirementMaterialRepository
                extends BaseRepository<PlanRequirementMaterialEntity, PlanRequirementMaterialId> {

        @Query("SELECT u FROM PlanRequirementMaterialEntity u WHERE u.fecha BETWEEN to_timestamp(:fechaInicio, 'yyyy-MM-DD') AND to_timestamp(:fechaFin, 'yyyy-MM-DD') AND u.centro.idCentro = :centro")
        List<PlanRequirementMaterialEntity> findAllByFilters(@Param("fechaInicio") String fechaInicio,
                        @Param("fechaFin") String fechaFin, @Param("centro") String centroId);
}
