package org.sapphire.appconsole.errorHandler;

/**
 * This is the POJO for the Error message.
 * @author deepak
 */
public class ErrorHandler {

	public ErrorHandler() {}
		// TODO Auto-generated constructor stub
	
		//The error message.
		private String message;
		
		// status code message.
		private int status;

		public ErrorHandler(String errorMessage)
		{
			this.message = errorMessage;
		}
		
		public ErrorHandler(String errorMessage , int status)
		{
			this.message = errorMessage;
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
	}
