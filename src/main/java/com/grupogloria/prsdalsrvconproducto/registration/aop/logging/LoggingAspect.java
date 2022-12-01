package com.grupogloria.prsdalsrvconproducto.registration.aop.logging;

import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@Aspect
public class LoggingAspect {

    private final Environment env;

    @Autowired
    HttpServletRequest request;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the
        // advices.
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @Around("springBeanControllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        this.setExtraFieldPropagation("audit-enterprise");
        this.setExtraFieldPropagation("audit-user");

        log.info("Request Tracking: {}", createJsonInformation(joinPoint));

        try {
            Object resultObj = joinPoint.proceed();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String response = ow.writeValueAsString(resultObj);
            log.info("Response Tracking: {}", new JSONObject(response));

            return resultObj;
        } catch (IllegalArgumentException | JsonProcessingException e) {
            if (e instanceof JsonProcessingException) {
                log.info("Response Tracking: {}", new JSONObject());
            }
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getName());
            throw e;
        }
    }

    private void setExtraFieldPropagation(String extraField) {
        String auditField1 = ExtraFieldPropagation.get(extraField);
        if (auditField1 == null) {
            Tracing.newBuilder().propagationFactory(
                    ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, extraField));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        if (StringUtils.isEmpty(auditField1) && request.getHeader(extraField) != null) {
            ExtraFieldPropagation.set(extraField, request.getHeader(extraField));
        }
    }

    private JSONObject createJsonInformation(ProceedingJoinPoint joinPoint) {
        JSONObject json = new JSONObject();
        json.put("URL", request.getRequestURL());
        json.put("Method", request.getMethod());
        json.put("Headers", mappingHeaders());
        Object bodyObject = joinPoint.getArgs()[0];
        json = formatBody(json, bodyObject);

        return json;
    }

    private JSONObject mappingHeaders() {
        JSONObject json = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                json.put(name, request.getHeader(name));

            }
        }

        return json;
    }

    public JSONObject formatBody(JSONObject json, Object value) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String bodyResponse = ow.writeValueAsString(value);
            json.put("Body", new JSONObject(bodyResponse));
        } catch (JSONException | JsonProcessingException e) {
            json.put("Body", new JSONObject());
        }

        return json;
    }
}