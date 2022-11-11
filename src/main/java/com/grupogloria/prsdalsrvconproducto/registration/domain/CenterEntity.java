package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "center")
@JsonInclude(Include.NON_EMPTY)
public class CenterEntity extends AuditEntity {

    @Id
    @Column(name = "id_centro", nullable = false)
    @Length(max = 4)
    private String idCenter;

    @Column(name = "centro", nullable = false)
    @Length(max = 200)
    private String center;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean canceledFlag;

    @Column(name = "id_pais", nullable = false)
    @Length(max = 4)
    private String idCountry;

    @Column(name = "id_departamento", nullable = false)
    private Integer idDepartment;

    @Column(name = "latitud", nullable = false)
    @Length(max = 50)
    private String latitude;

    @Column(name = "longitud", nullable = false)
    @Length(max = 50)
    private String longitude;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CenterMaterialEntity> centerMaterials;

}
