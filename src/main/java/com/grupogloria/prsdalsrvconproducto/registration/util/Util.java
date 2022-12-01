package com.grupogloria.prsdalsrvconproducto.registration.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;

public class Util {

    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static int getStatusCode(Integer codigoRespuesta) {
        if (GlobalConstants.successfulRequests.contains(codigoRespuesta))
            return 0;
        return 1;
    }

    public static String getStatusCodeErrorDescription(Integer codigoRespuesta, String description) {
        String res = description;
        if (codigoRespuesta == GlobalConstants.INTERNAL_ERROR) {
            return "INTERNAL ERROR" + " | " + res;
        }

        return "GENERIC ERROR" + " | " + res;
    }

    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static Map<String, String> getAllHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> mapper = new HashMap<>();
        ;

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                mapper.put(name, request.getHeader(name));
            }
        }

        return mapper;
    }
}
