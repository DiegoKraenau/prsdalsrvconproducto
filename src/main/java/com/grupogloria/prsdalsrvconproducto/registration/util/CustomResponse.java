package com.grupogloria.prsdalsrvconproducto.registration.util;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int codigoRespuesta;
    private int status;
    private String descripcionRespuesta;
    private String idTransaccion;
    private String fechaTermino;
    private T data;

    public CustomResponse(int codigoRespuesta, int status, String descripcionRespuesta, String idTransaccion,
            String fechaTermino, T data) {
        this.codigoRespuesta = codigoRespuesta;
        this.status = status;
        this.descripcionRespuesta = descripcionRespuesta;
        this.idTransaccion = idTransaccion;
        this.fechaTermino = fechaTermino;
        this.data = data;
    }

    public CustomResponse(int codigoRespuesta, int status, String descripcionRespuesta, String idTransaccion,
            String fechaTermino) {
        this.codigoRespuesta = codigoRespuesta;
        this.status = status;
        this.descripcionRespuesta = descripcionRespuesta;
        this.idTransaccion = idTransaccion;
        this.fechaTermino = fechaTermino;
    }
}
