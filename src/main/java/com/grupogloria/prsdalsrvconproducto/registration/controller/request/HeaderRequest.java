package com.grupogloria.prsdalsrvconproducto.registration.controller.request;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeaderRequest {
    private String pais;
    private String empresa;
    private String division;
    private String idTransaccion;
    private String aplicacion;
    private String usuarioAplicacion;
    private Date fechaEjecucion;

    public HeaderRequest(String pais, String empresa, String division, String idTransaccion, String aplicacion, String usuarioAplicacion, Date fechaEjecucion) {
        this.pais = pais;
        this.empresa = empresa;
        this.division = division;
        this.idTransaccion = idTransaccion;
        this.aplicacion = aplicacion;
        this.usuarioAplicacion = usuarioAplicacion;
        this.fechaEjecucion = fechaEjecucion;
    }
    
    
}
