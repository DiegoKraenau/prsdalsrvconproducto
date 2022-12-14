package com.grupogloria.prsdalsrvconproducto.registration.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;

@Repository
public interface PlanRequirementMaterialRepository
                extends BaseRepository<PlanRequirementMaterialEntity, Long> {

        @Query("SELECT u FROM PlanRequirementMaterialEntity u WHERE u.fecha BETWEEN to_timestamp(:fechaInicio, 'yyyy-MM-DD') AND to_timestamp(:fechaFin, 'yyyy-MM-DD') AND u.centro.idCentro = :centro")
        List<PlanRequirementMaterialEntity> findAllByFilters(@Param("fechaInicio") String fechaInicio,
                        @Param("fechaFin") String fechaFin, @Param("centro") String centroId);

        @Query("SELECT u FROM PlanRequirementMaterialEntity u WHERE u.fecha = to_timestamp(:fecha, 'yyyy-MM-DD') AND u.material.id = :materialId AND u.centro.idCentro = :centroId AND u.unidadMedida.idUnidadMedida = :unidadMedidaId")
        Optional<PlanRequirementMaterialEntity> findOneByFilter(@Param("materialId") String material,
                        @Param("fecha") Timestamp fecha, @Param("centroId") String centro,
                        @Param("unidadMedidaId") String unidadMedida);
}
