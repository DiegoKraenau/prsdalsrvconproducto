package com.grupogloria.prsdalsrvconproducto.registration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.CenterMaterialId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "centro_material")
@JsonInclude(Include.NON_EMPTY)
@IdClass(CenterMaterialId.class)
public class CenterMaterialEntity extends AuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_centro", nullable = false)
    private CenterEntity center;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    private MaterialEntity material;

}
