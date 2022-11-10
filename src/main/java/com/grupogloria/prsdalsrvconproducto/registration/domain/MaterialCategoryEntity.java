package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "categoria_material")
@JsonInclude(Include.NON_EMPTY)
public class MaterialCategoryEntity extends AuditEntity {

    @Id
    @Column(name = "cod_catmaterial", nullable = false)
    @Length(min = 3, max = 3)
    private String codCatMaterial;

    @Column(name = "categoria_material", nullable = false)
    private String materialCategory;

    @Column(name = "flg_anulado", nullable = false)
    private Boolean canceledFlag;

}
