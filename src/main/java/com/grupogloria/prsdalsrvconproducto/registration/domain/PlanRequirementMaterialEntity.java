package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plan_requerimiento_material")
@JsonInclude(Include.NON_EMPTY)
@IdClass(PlanRequirementMaterialId.class)
public class PlanRequirementMaterialEntity extends AuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    private MaterialEntity material;

    @Id
    @Column(name = "fecha", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp date;

    @Column(name = "anio", nullable = false)
    private Integer year;

    @Column(name = "cod_mes", nullable = false)
    private String month;

    @Column(name = "cantidad", nullable = false)
    private Integer amount;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean canceledFlag;
}
