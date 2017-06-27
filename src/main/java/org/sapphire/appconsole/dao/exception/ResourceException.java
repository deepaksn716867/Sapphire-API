package org.sapphire.appconsole.dao.exception;

public abstract class ResourceException extends RuntimeException {

	private String resourceClassName;
	private String resourceId;
	
	private static final long serialVersionUID = 6527257508726000319L;

	public ResourceException() {
	}

	public ResourceException(String message) {
		super(message);
	}

	public ResourceException(String resourceClassName, String message) {
		super(message);
		this.resourceClassName = resourceClassName;
		
	}
	
	public ResourceException(Throwable cause) {
		super(cause);
	}

	public ResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getResourceClassName() {
		return resourceClassName;
	}

	public void setResourceClassName(String resourceClassName) {
		this.resourceClassName = resourceClassName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

}
