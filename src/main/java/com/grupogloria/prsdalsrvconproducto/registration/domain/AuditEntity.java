package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@MappedSuperclass
public class AuditEntity implements Serializable {

    @Column(name = "fec_creacion", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp fecCreacion;

    @Column(name = "fec_actualizacion", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp fecActualizacion;

    @Column(name = "usu_creacion", nullable = false)
    private String usuCreacion;

    @Column(name = "usu_actualizacion", nullable = false)
    private String usuActualizacion;

    @Column(name = "equipo_creacion", nullable = false)
    private String equipoCreacion;

    @Column(name = "equipo_actualizacion", nullable = false)
    private String equipoActualizacion;
}
