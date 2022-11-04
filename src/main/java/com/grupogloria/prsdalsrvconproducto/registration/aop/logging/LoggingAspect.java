package com.grupogloria.prsdalsrvconproducto.registration.aop.logging;

import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private final Environment env;

    public LoggingAspect(Environment env) {
        this.env = env;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @Around("springBeanControllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        if (log.isDebugEnabled()) {
            this.setExtraFieldPropagation("audit-enterprise");
            this.setExtraFieldPropagation("audit-user");
            log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (log.isDebugEnabled()) {
                log.debug("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }

    private void setExtraFieldPropagation(String extraField) {
        String auditField1 = ExtraFieldPropagation.get(extraField);
        if (auditField1 == null) {
            Tracing.newBuilder().propagationFactory(
                    ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, extraField)
            );
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest();
        if (StringUtils.isEmpty(auditField1) && request.getHeader(extraField) != null) {
            ExtraFieldPropagation.set(extraField, request.getHeader(extraField));
        }
    }
}
