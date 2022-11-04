package com.grupogloria.prsdalsrvconproducto.registration.aop.logging;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkPayload;
import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.JSONUtil;


@Aspect
@Component
public class ElkLogger {	
	
	
	public static <T> ElkPayload prepareELKPayload(T t) {
		
	// Sample logger below
	// ElkLogger.log(Level.ERROR,new ElkPayload("ExceptionOccured","404","error message"),this.getClass().getName(), null);
		
		ElkPayload elkPayload = new ElkPayload();
		try {InetAddress myIP=InetAddress.getLocalHost();
		elkPayload.setHostIp(myIP.getHostAddress());
		} catch (UnknownHostException e) {
		log(Level.ERROR, getStackTrace(e), ElkLogger.class.getName(),e);	
		e.printStackTrace();
		}
		
		if(t instanceof String) {
		elkPayload.setMessageBody(t.toString());
		}
		else	{
			ElkPayload payload = (ElkPayload)t;
			if(null!=payload.getMessageBody()) elkPayload.setMessageBody(payload.getMessageBody().toString());		
		    if(null!=payload.getStatusCode()) elkPayload.setStatusCode(payload.getStatusCode());
		    if(null!=payload.getApiClassName()) elkPayload.setApiClassName(payload.getApiClassName());
		    if(null!=payload.getApiMethodName()) elkPayload.setApiMethodName(payload.getApiMethodName());
		    if(null!=payload.getApiResponseTime()) elkPayload.setApiResponseTime(payload.getApiResponseTime());
		}
		
		return elkPayload;
		}
	
		
	
	@Around("@annotation(com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall)")
	public Object logMethod(ProceedingJoinPoint pjp) throws Throwable {
		long startTime=System.currentTimeMillis();
		Object obj=pjp.proceed();
		long endTime=System.currentTimeMillis();
		ElkPayload elkPayload = new ElkPayload();
		elkPayload.setMessageBody("Method "+pjp.getSignature().getName()+" was invoked and executed in "+(endTime-startTime)+ " ms.");
		elkPayload.setApiMethodName(pjp.getSignature().getName());
		elkPayload.setApiResponseTime(endTime-startTime);
		elkPayload.setApiClassName(pjp.getTarget().getClass().getName());
		log(Level.INFO,elkPayload, pjp.getTarget().getClass().getName(),null);
		return obj;
	}
	
	public static String getStackTrace(final Throwable throwable) {
		final StringWriter sw= new StringWriter();
		final PrintWriter pw=new PrintWriter(sw,true);
		throwable.printStackTrace(pw);
		return sw.getBuffer().toString();
	}
		
	
	public static <T>void log(Level level,T t, String className, Exception e) {			
		  Logger logger = LogManager.getLogger(className);		 
		  if(null==e)
			  logger.log(level,JSONUtil.getJSONForObject(prepareELKPayload(t)));	
		  else
		   logger.log(level,JSONUtil.getJSONForObject(prepareELKPayload(t)),e);		   
		}
	
}
