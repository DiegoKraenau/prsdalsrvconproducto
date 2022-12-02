package com.grupogloria.prsdalsrvconproducto.registration.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ManageError {
    private String idTransaccion;

    private String message;

}
