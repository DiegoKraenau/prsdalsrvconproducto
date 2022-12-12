package com.grupogloria.prsdalsrvconproducto.registration.repository;

import org.springframework.stereotype.Repository;

import com.grupogloria.prsdalsrvconproducto.registration.domain.LineMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.LineMaterialId;

@Repository
public interface LineMaterialRepository extends BaseRepository<LineMaterialEntity, LineMaterialId> {

}
