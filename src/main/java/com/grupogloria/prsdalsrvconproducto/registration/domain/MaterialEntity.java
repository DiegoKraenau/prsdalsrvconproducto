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
    @Column(name = "id_material", nullable = false)
    private String id;

    @Column(name = "nombre_largo", nullable = false)
    private String nombreLargo;

    @Column(name = "nombre_corto", nullable = false)
    private String nombreCorto;

    @Column(name = "presentacion", nullable = false)
    private String presentacion;

    @Column(name = "dias_vencimiento", nullable = false)
    private Integer diasVencimiento;

    @Column(name = "tipo_vencimiento", nullable = false)
    private String tipoVencimiento;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean flgAnulado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_catmaterial")
    private MaterialCategoryEntity categoriaMaterial;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CenterMaterialEntity> centroMateriales;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<AlternativeUnitEntity> unidadMedidas;

    // TODO: Falta hacer relacion con linea

}
