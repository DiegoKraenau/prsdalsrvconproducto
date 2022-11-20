package com.grupogloria.prsdalsrvconproducto.registration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.AlternativeUnitId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidad_alternativo")
@JsonInclude(Include.NON_EMPTY)
@IdClass(AlternativeUnitId.class)
public class AlternativeUnitEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    @JsonManagedReference
    private MaterialEntity material;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_unidad_medida", nullable = false)
    @JsonManagedReference
    private UnitMeasureEntity unidadMedida;

    @Column(name = "denominador", nullable = false)
    private Long denominador;

    @Column(name = "contador", nullable = false)
    private Long Contador;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean flgAnulado;
}
