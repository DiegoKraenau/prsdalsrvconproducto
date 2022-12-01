package com.grupogloria.prsdalsrvconproducto.registration.aop.custom;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.util.CustomResponse;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;

@Component
@Aspect
public class ValidateHeadersImpl {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Around("@annotation(ValidateHeaders)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

        Map<String, String> mapper = Util.getAllHeaders(request);
        Collection<String> keys = mapper.keySet();
        String message = "Se necesitan los headers obligatorios: " + String.join(",", GlobalConstants.mandatoryHeaders);

        for (String key : GlobalConstants.mandatoryHeaders) {
            if (!keys.contains(key.toLowerCase())) {
                response.setStatus(GlobalConstants.FORBIDDEN_ERROR);
                return new CustomResponse<>(
                        GlobalConstants.FORBIDDEN_ERROR,
                        Util.getStatusCode(GlobalConstants.FORBIDDEN_ERROR),
                        message,
                        Util.getAllHeaders(request),
                        Util.getDate());
            }
        }

        return joinPoint.proceed();
    }
}
