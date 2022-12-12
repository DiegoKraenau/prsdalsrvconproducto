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
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.LineMaterialId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "material_linea")
@JsonInclude(Include.NON_EMPTY)
@IdClass(LineMaterialId.class)
public class LineMaterialEntity extends AuditEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_material", nullable = false)
    @JsonManagedReference
    private MaterialEntity material;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_centro", nullable = false)
    @JsonManagedReference
    private CenterEntity centro;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_linea", nullable = false)
    @JsonManagedReference
    private LineEntity linea;

    @Column(name = "presentacion", nullable = false)
    private String presentacion;

    @Column(name = "pst_trab", nullable = false)
    private String pstTrab;

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "desc_version", nullable = false)
    private String descVersion;

    @Column(name = "velocidad", nullable = false)
    private Integer velocidad;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean flgAnulado;

}
