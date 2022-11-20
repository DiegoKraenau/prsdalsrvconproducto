package com.grupogloria.prsdalsrvconproducto.registration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "unidad_medida")
@JsonInclude(Include.NON_EMPTY)
public class UnitMeasureEntity extends AuditEntity {

    @Id
    @Column(name = "id_unidad_medida", nullable = false)
    private String idUnidadMedida;

    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida;

    @Column(name = "unidad_medida_larga", nullable = false)
    private String unidadMedidaLarga;

}
