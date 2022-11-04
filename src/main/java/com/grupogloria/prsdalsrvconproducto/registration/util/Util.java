package com.grupogloria.prsdalsrvconproducto.registration.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;

public class Util {

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static int getStatusCode(Integer codigoRespuesta) {
        if (GlobalConstants.successfulRequests.contains(codigoRespuesta))
            return 1;
        return 0;
    }

    public static String getStatusCodeErrorDescription(Integer codigoRespuesta, String description) {
        String res = description;
        if (codigoRespuesta == GlobalConstants.INTERNAL_ERROR) {
            return "INTERNAL ERROR" + " | " + res;
        }

        return "GENERIC ERROR" + " | " + res;
    }
}
