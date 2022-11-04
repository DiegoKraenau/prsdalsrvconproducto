package com.grupogloria.prsdalsrvconproducto.registration.aop.logging;

public class ElkPayload {
	
	private String HostIp;
	private String MessageBody;
	private String StatusCode;
	private String ErrorStackTrace;	
	private String ApiMethodName;
	private String ApiClassName; 	
	private Long ApiResponseTime;	
	private Long TransactionId;		
	

	public ElkPayload() {	   
	  }
	
	public ElkPayload(String MessageBody, String StatusCode, String ErrorStackTrace) {	   
		super();
	    this.MessageBody = MessageBody;
	    this.StatusCode = StatusCode;
	    this.ErrorStackTrace = ErrorStackTrace;
	  }
	
	public String getErrorStackTrace() {
		return ErrorStackTrace;
	}

	
	public Long getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Long transactionId) {
		TransactionId = transactionId;
	}
	public void setErrorStackTrace(String errorStackTrace) {
		ErrorStackTrace = errorStackTrace;
	}
	
	public String getApiMethodName() {
		return ApiMethodName;
	}
	public void setApiMethodName(String apiMethodName) {
		ApiMethodName = apiMethodName;
	}
	public Long getApiResponseTime() {
		return ApiResponseTime;
	}
	public void setApiResponseTime(Long apiResponseTime) {
		ApiResponseTime = apiResponseTime;
	}
	
	
	public String getApiClassName() {
		return ApiClassName;
	}
	public void setApiClassName(String apiClassName) {
		ApiClassName = apiClassName;
	}
	public String getHostIp() {
		return HostIp;
	}
	public void setHostIp(String hostIp) {
		HostIp = hostIp;
	}
	
	public String getMessageBody() {
		return MessageBody;
	}
	public void setMessageBody(String messageBody) {
		MessageBody = messageBody;
	}
	public String getStatusCode() {
		return StatusCode;
	}
	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}
	
}
