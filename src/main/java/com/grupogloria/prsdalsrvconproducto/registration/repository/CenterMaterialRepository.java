package com.grupogloria.prsdalsrvconproducto.registration.repository;

import org.springframework.stereotype.Repository;

import com.grupogloria.prsdalsrvconproducto.registration.domain.CenterMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.CenterMaterialId;

@Repository
public interface CenterMaterialRepository extends BaseRepository<CenterMaterialEntity, CenterMaterialId> {

}
