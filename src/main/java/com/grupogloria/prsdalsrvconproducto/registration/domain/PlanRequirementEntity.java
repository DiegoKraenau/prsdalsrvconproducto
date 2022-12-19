package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "plan_requerimiento")
@JsonInclude(Include.NON_EMPTY)
public class PlanRequirementEntity extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_centro")
    private Long idPlanCentro;

    @ManyToOne
    @JoinColumn(name = "id_centro", nullable = false)
    @JsonManagedReference
    private CenterEntity center;

    @Column(name = "fecha", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp fecha;

    @Column(name = "cantidad", nullable = true)
    private String descripcion;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean flgAnulado;

}
