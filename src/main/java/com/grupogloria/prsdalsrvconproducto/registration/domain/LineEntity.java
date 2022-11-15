package com.grupogloria.prsdalsrvconproducto.registration.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

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
@Table(name = "linea")
@JsonInclude(Include.NON_EMPTY)
public class LineEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linea")
    private Long idLinea;

    @Column(name = "id_centro", nullable = false)
    @Length(max = 4)
    private String idCentro;

    @Column(name = "pst_trbjo", nullable = false)
    private String pstTrbjo;

    @Column(name = "id_seccion_produccion", nullable = false)
    private String idSeccionProduccion;

    @Column(name = "linea", nullable = false)
    @Length(max = 80)
    private String linea;

    @Column(name = "descripcion", nullable = false)
    @Length(max = 80)
    private String descripcion;

    @Column(name = "limite_superior", nullable = false)
    private Float limiteSuperior;

    @Column(name = "limite_inferior", nullable = false)
    private Float limiteInferior;

    @Column(name = "codificacion", nullable = false)
    private Integer codificacion;

    @Column(name = "flq_reproceso", nullable = false)
    private Boolean flg_reproceso;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean flgAnulado;

    @Column(name = "time_out_impresion", nullable = false)
    private Integer timeOutImpresion;

}
