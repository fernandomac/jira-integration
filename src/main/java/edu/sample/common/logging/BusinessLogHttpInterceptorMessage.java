package edu.sample.common.logging;

/**
 * Data model for logging HTTP request
 * and parameters from all controllers
 * @author
 *
 */
public class BusinessLogHttpInterceptorMessage {

	/**
	 * HTTP Rest parameter object values;
	 */
	private Object[] requestParams;

	private Object responseParam;

	//Request URL
	private String requestURI;

	//Http Methods (Example: GET, POST, PUT, DELETE)
	private String httpMethod;

	//Total time taken for the Controller to process the request.
	private long processingTime;

	private String exceptionMessage;
	

	public BusinessLogHttpInterceptorMessage() {
	}

	public Object[] getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(Object[] requestParams) {
		this.requestParams = requestParams;
	}

	public Object getResponseParam() {
		return responseParam;
	}

	public void setResponseParam(Object responseParam) {
		this.responseParam = responseParam;
	}


	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public long getProcessingTime() {
		return processingTime;
	}

	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}
