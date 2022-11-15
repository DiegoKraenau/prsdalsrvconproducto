package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "material")
@JsonInclude(Include.NON_EMPTY)
public class MaterialEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Long id;

    @Column(name = "nombre_largo", nullable = false)
    private String largeName;

    @Column(name = "nombre_corto", nullable = false)
    private String shortName;

    @Column(name = "presentacion", nullable = false)
    private String presentation;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean canceledFlag;

    @Column(name = "dias_vencimiento", nullable = false)
    private Integer expiredDays;

    @Column(name = "tipo_vencimiento", nullable = false)
    private String maturityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_catmaterial")
    private MaterialCategoryEntity materialCategory;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CenterMaterialEntity> centerMaterials;

}
