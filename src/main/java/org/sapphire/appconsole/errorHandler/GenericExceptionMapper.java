package org.sapphire.appconsole.errorHandler;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception>{
	private final static Logger LOG = Logger.getLogger(GenericExceptionMapper.class) ;

	public GenericExceptionMapper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response toResponse(Exception ex) {
		// TODO Auto-generated method stub
		Response response = null;
		ErrorHandler errorHandler = new ErrorHandler();
		try {
			setHttpStatus(ex,errorHandler);
			response =  Response.status(errorHandler.getStatus())
					.entity(new ObjectMapper().writeValueAsString(errorHandler))
					.type(MediaType.APPLICATION_JSON)
					.build();
		} catch (Exception e) {
			LOG.error("Unexpected error while trying writig json error object",e);
			e.printStackTrace();
		}
		return response;
	}
	
	//This method for settting appropriate error code and message.
	private void setHttpStatus(Throwable ex, ErrorHandler errorMessage) {
		if(ex instanceof WebApplicationException ) {
			errorMessage.setStatus(((WebApplicationException)ex).getResponse().getStatus());
			errorMessage.setMessage(((WebApplicationException)ex).getLocalizedMessage());
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()); //defaults to internal server error 500
			errorMessage.setMessage("Unexpected Error");
			
		}
	}

}
